package io.xccit.zxyp.service.product;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.product.ProductDto;
import io.xccit.zxyp.model.entity.product.Product;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description
 */
public interface IProductService {
    /**
     * 分页列表条件查询
     * @param current
     * @param pageSize
     * @param productDto
     * @return
     */
    PageInfo<Product> listProductPage(Integer current, Integer pageSize, ProductDto productDto);

    /**
     * 商品信息添加
     * @param product
     */
    void save(Product product);
}
