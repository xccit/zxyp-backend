package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.model.vo.product.CategoryExcelVo;
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

    /**
     * 查询所有分类信息
     * @return
     */
    List<Category> list();

    /**
     * Excel批量添加
     * @param cachedDataList
     * @param <T>
     */
    <T> void batchSave(List<T> cachedDataList);

    /**
     * 根据ID删除
     * @param id
     */
    void remove(Long id);

    /**
     * 修改分类信息
     * @param category
     */
    void update(Category category);
}
