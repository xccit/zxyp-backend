package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.CategoryMapper;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
