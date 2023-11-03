package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.product.ProductSku;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 商品业务层
 */
public interface IProductService {

    /**
     * 首页商品Sku销量前10
     * @return
     */
    List<ProductSku> listProductSkuBySale();
}
