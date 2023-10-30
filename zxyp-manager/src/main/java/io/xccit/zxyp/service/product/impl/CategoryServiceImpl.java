package io.xccit.zxyp.service.product.impl;

import io.xccit.zxyp.mapper.product.CategoryMapper;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.service.product.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
