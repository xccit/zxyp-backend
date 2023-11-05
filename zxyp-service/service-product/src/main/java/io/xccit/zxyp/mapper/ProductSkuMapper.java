package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.dto.h5.ProductSkuDto;
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

    /**
     * 商品分页条件查询
     * @param productSkuDto
     * @return
     */
    List<ProductSku> listProductPage(ProductSkuDto productSkuDto);

    /**
     * 获取当前Sku信息
     * @param skuId
     * @return
     */
    ProductSku getOneByID(Long skuId);

    /**
     * 根据商品ID获取Sku列表
     * @param productId
     * @return
     */
    List<ProductSku> listSkuByProductID(Long productId);
}
