package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.dto.product.CategoryBrandDto;
import io.xccit.zxyp.model.entity.product.Brand;
import io.xccit.zxyp.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 品牌分类mapper
 */
@Mapper
public interface CategoryBrandMapper {
    /**
     * 品牌分类分页条件列表
     * @param categoryBrandDto
     * @return
     */
    List<CategoryBrand> listCategoryBrandPage(CategoryBrandDto categoryBrandDto);

    /**
     * 品牌分类添加
     * @param categoryBrand
     */
    void save(CategoryBrand categoryBrand);

    /**
     * 品牌分类修改
     * @param categoryBrand
     */
    void update(CategoryBrand categoryBrand);

    /**
     * 品牌分类删除
     * @param ids
     */
    void remove(List<Long> ids);

    /**
     * 根据分类ID查询品牌数据
     * @param categoryId
     * @return
     */
    List<Brand> listBrandByCategoryID(Long categoryId);
}
