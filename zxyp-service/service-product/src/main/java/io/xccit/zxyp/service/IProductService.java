package io.xccit.zxyp.service;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.h5.ProductSkuDto;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.model.vo.h5.ProductItemVo;

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

    /**
     * 商品分页条件查询
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    PageInfo<ProductSku> listProductPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    /**
     * 商品详情
     * @param skuId
     * @return
     */
    ProductItemVo details(Long skuId);
}
