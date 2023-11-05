package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 商品Mapper
 */
@Mapper
public interface ProductMapper {

    /**
     * 根据商品ID获取商品信息
     * @param productId
     * @return
     */
    Product getOneByProductID(Long productId);
}
