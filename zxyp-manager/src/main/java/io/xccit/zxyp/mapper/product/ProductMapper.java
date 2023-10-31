package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.dto.product.ProductDto;
import io.xccit.zxyp.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品Mapper
 */
@Mapper
public interface ProductMapper {

    /**
     * 分页列表条件查询
     * @param productDto 条件
     * @return
     */
    List<Product> list(ProductDto productDto);

    /**
     * 添加商品的基本信息
     * @param product
     */
    void save(Product product);

    /**
     * 根据ID获取基本商品信息
     * @param productId
     * @return
     */
    Product getOne(Long productId);

    /**
     * 修改商品信息
     * @param product
     */
    void update(Product product);
}
