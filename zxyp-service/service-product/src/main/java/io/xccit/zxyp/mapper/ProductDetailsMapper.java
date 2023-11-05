package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 商品详情Mapper
 */
@Mapper
public interface ProductDetailsMapper {

    /**
     * 根据商品ID获取商品详情信息
     * @param productId
     * @return
     */
    ProductDetails getOneByProductID(Long productId);
}
