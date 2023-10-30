package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 分类Mapper
 */
@Mapper
public interface CategoryMapper {
    /**
     * 查询第一层数据
     * @param id
     * @return
     */
    List<Category> listCategoryByParentID(Long id);

    /**
     * 使用当前查询到的第一层分类信息的ID做为parentId查询是否有下一层
     * @param id
     * @return
     */
    int getCountByParentID(Long id);
}
