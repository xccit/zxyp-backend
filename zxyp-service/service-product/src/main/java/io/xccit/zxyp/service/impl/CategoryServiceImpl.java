package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.CategoryMapper;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 首页一级分类
     *
     * @return
     */
    @Override
    public List<Category> listOneCategory() {
        return categoryMapper.listOneCategory();
    }

    /**
     * 获取分类树形数据
     *
     * @return
     */
    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryMapper.list();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            //二级分类
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream()
                        .filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue())
                        .collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);
                //三级分类
                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream()
                                .filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
