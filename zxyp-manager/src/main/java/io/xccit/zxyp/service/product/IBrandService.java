package io.xccit.zxyp.service.product;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.entity.product.Brand;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 品牌服务层
 */
public interface IBrandService {
    /**
     * 品牌分页列表
     * @param current
     * @param pageSize
     * @return
     */
    PageInfo<Brand> listBrandPage(Integer current, Integer pageSize);

    /**
     * 品牌添加
     * @param brand
     */
    void saveBrand(Brand brand);

    /**
     * 品牌修改
     * @param brand
     */
    void updateBrand(Brand brand);

    /**
     * 品牌删除/批量删除
     * @param ids
     */
    void removeBrand(List<Long> ids);

    /**
     * 品牌列表
     * @return
     */
    List<Brand> list();
}
