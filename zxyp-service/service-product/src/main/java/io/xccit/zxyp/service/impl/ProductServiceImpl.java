package io.xccit.zxyp.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.xccit.zxyp.mapper.ProductDetailsMapper;
import io.xccit.zxyp.mapper.ProductMapper;
import io.xccit.zxyp.mapper.ProductSkuMapper;
import io.xccit.zxyp.model.dto.h5.ProductSkuDto;
import io.xccit.zxyp.model.entity.product.Product;
import io.xccit.zxyp.model.entity.product.ProductDetails;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.model.vo.h5.ProductItemVo;
import io.xccit.zxyp.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description
 */
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    /**
     * 首页商品Sku销量前10
     *
     * @return
     */
    @Override
    public List<ProductSku> listProductSkuBySale() {
        return productSkuMapper.listProductSkuBySale();
    }

    /**
     * 商品分页条件查询
     *
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    @Override
    public PageInfo<ProductSku> listProductPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productSkuMapper.listProductPage(productSkuDto);
        PageInfo<ProductSku> productSkuPageInfo = new PageInfo<>(productSkuList);
        return productSkuPageInfo;
    }

    /**
     * 商品详情
     *
     * @param skuId
     * @return
     */
    @Override
    public ProductItemVo details(Long skuId) {
        //当前sku信息
        ProductSku productSku = productSkuMapper.getOneByID(skuId);
        //当前商品信息
        Product product = productMapper.getOneByProductID(productSku.getProductId());
        //同一个商品下面的sku信息列表
        List<ProductSku> productSkuList = productSkuMapper.listSkuByProductID(productSku.getProductId());
        //建立sku规格与skuId对应关系
        Map<String,Object> skuSpecValueMap = new HashMap<>();
        productSkuList.forEach(item -> {
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });
        //商品详情信息
        ProductDetails productDetails = productDetailsMapper.getOneByProductID(productSku.getProductId());

        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        return productItemVo;
    }

    /**
     * 根据SkuID获取商品Sku信息,供购物车远程调用接口
     *
     * @param skuId
     * @return
     */
    @Override
    public ProductSku getProductSkuByID(Long skuId) {
        ProductSku productSku = productSkuMapper.getOneByID(skuId);
        return productSku;
    }
}
