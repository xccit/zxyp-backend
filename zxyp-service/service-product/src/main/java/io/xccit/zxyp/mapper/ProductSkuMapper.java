package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 商品Sku Mapper
 */
@Mapper
public interface ProductSkuMapper {

    /**
     * 首页商品Sku销量前10
     * @return
     */
    List<ProductSku> listProductSkuBySale();
}
