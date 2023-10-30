package io.xccit.zxyp.service.product.impl;

import com.alibaba.excel.EasyExcel;
import io.xccit.zxyp.exception.ExcelOperatorException;
import io.xccit.zxyp.listener.ExcelListener;
import io.xccit.zxyp.mapper.product.CategoryMapper;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.product.CategoryExcelVo;
import io.xccit.zxyp.service.product.ICategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Encoder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 分类业务层
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分类信息列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Category> listCategory(Long id) {
        //TODO 查询第一层数据
        List<Category> categoryList = categoryMapper.listCategoryByParentID(id);
        //TODO 第一层数据完毕之后,查询该ID是否是其他数据的parentId,如果是,需要对hasChildren赋值
        categoryList.forEach(category -> {
            int count = categoryMapper.getCountByParentID(category.getId());
            if (count > 0){
                category.setHasChildren(true);
            }else{
                category.setHasChildren(false);
            }
        });
        return categoryList;
    }

    /**
     * 分类数据导出
     *
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        //TODO 查询所有分类信息,封装属性值到CategoryExcelVO
        List<Category> categoryList = categoryMapper.list();
        List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
            BeanUtils.copyProperties(category,categoryExcelVo);
            categoryExcelVoList.add(categoryExcelVo);
        }
        try {
            //TODO 设置文件名及编码信息
            String fileName = URLEncoder.encode("分类信息","UTF-8");//TODO 设置相应头及响应体
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            //TODO 数据写出
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new ExcelOperatorException(ResultCodeEnum.EXCEL_ERROR);
        }
    }

    /**
     * 分类数据导入
     *
     * @param file
     */
    @Override
    public void importData(MultipartFile file) {
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelOperatorException(ResultCodeEnum.EXCEL_ERROR);
        }
    }
}
