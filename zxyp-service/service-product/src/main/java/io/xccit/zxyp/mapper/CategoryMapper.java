package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 分类Mapper
 */
@Mapper
public interface CategoryMapper {

    /**
     * 首页一级分类
     * @return
     */
    List<Category> listOneCategory();

    /**
     * 所有分类
     * @return
     */
    List<Category> list();
}
