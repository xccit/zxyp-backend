package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description Details Mapper
 */
@Mapper
public interface ProductDetailsMapper {

    /**
     * 添加商品详情信息
     * @param productDetails
     */
    void save(ProductDetails productDetails);
}
