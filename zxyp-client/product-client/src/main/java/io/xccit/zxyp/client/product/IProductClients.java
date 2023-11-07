package io.xccit.zxyp.client.product;

import io.xccit.zxyp.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 商品远程调用接口
 */
@FeignClient(value = "service-product")
public interface IProductClients {

    /**
     * 加入购物车前需要先获取Sku信息,根据skuID获取Sku信息
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/getBySkuId/{skuId}")
    ProductSku getProductSkuByID(@PathVariable Long skuId);
}
