package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description sku mapper
 */
@Mapper
public interface ProductSkuMapper {

    /**
     * 添加商品Sku信息
     * @param productSku
     */
    void save(ProductSku productSku);

    /**
     * 根据商品ID查询sku信息并封装
     * @param productId
     * @return
     */
    List<ProductSku> getSkuByProductID(Long productId);

    /**
     * 修改商品Sku信息
     * @param productSku
     */
    void update(ProductSku productSku);

    /**
     * 根据商品ID删除Sku信息
     * @param productId
     */
    void removeByProductID(Long productId);
}
