package io.xccit.zxyp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.mapper.ProductMapper;
import io.xccit.zxyp.mapper.ProductSkuMapper;
import io.xccit.zxyp.model.dto.h5.ProductSkuDto;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
