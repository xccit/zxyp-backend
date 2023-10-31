package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品规格Mapper
 */
@Mapper
public interface ProductSpecMapper {

    /**
     * 商品规格分页列表
     * @return
     */
    List<ProductSpec> list();

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
}
