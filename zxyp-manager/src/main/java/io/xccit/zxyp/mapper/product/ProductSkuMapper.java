package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

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
}
