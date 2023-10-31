package io.xccit.zxyp.service.product;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品规格业务层
 */
public interface IProductSpecService {

    /**
     * 商品规格分页列表
     * @return
     */
    PageInfo<ProductSpec> listSpecPage(Integer current,Integer pageSize);

    /**
     * 商品规格删除/批量删除
     * @param ids
     */
    void remove(List<Long> ids);

    /**
     * 商品规格修改
     * @param productSpec
     */
    void update(ProductSpec productSpec);

    /**
     * 商品规格添加
     * @param productSpec
     */
    void save(ProductSpec productSpec);

    /**
     * 商品规格列表
     * @return
     */
    List<ProductSpec> list();
}
