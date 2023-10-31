package io.xccit.zxyp.service.product;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.product.CategoryBrandDto;
import io.xccit.zxyp.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 品牌分类业务层
 */
public interface ICategoryBrandService {
    /**
     * 品牌分类分页条件列表
     * @param current
     * @param pageSize
     * @param categoryBrandDto
     * @return
     */
    PageInfo<CategoryBrand> listCategoryBrandPage(Integer current, Integer pageSize, CategoryBrandDto categoryBrandDto);

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
}
