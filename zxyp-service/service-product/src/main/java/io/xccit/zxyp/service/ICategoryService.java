package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.product.Category;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 分类业务层
 */
public interface ICategoryService {
    /**
     * 首页一级分类
     * @return
     */
    List<Category> listOneCategory();
}
