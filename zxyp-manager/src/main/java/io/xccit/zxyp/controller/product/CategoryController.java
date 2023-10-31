package io.xccit.zxyp.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.product.ICategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 分类控制器
 */
@Tag(name="分类接口")
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 分类信息列表
     * @param id
     * @return
     */
    @Operation(summary = "分类信息列表",description = "前端使用tree结构,并且有懒加载,请求时只需要传入上层结构的ID,封装后返回下层数据即可")
    @GetMapping("/{id}")
    public AjaxResult listCategory(@PathVariable Long id){
        List<Category> categoryList = categoryService.listCategory(id);
        return AjaxResult.build(categoryList, ResultCodeEnum.SUCCESS);
    }

    /**
     * 导出数据
     * @param response
     */
    @Operation(summary = "分类数据导出")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response){
        categoryService.exportData(response);
    }

    /**
     * 分类数据导入
     * @param file
     * @return
     */
    @Operation(summary = "分类数据导入",description = "分类数据导入,读取Excel文件,执行数据添加操作")
    @PostMapping("/import")
    public AjaxResult importData(MultipartFile file){
        categoryService.importData(file);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}