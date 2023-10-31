package io.xccit.zxyp.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.constants.ProductSkuCodePrefixConstant;
import io.xccit.zxyp.mapper.product.ProductDetailsMapper;
import io.xccit.zxyp.mapper.product.ProductMapper;
import io.xccit.zxyp.mapper.product.ProductSkuMapper;
import io.xccit.zxyp.model.dto.product.ProductDto;
import io.xccit.zxyp.model.entity.product.Product;
import io.xccit.zxyp.model.entity.product.ProductDetails;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.service.product.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品业务层
 */
@Transactional
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    /**
     * 分页列表条件查询
     *
     * @param current
     * @param pageSize
     * @param productDto
     * @return
     */
    @Override
    public PageInfo<Product> listProductPage(Integer current, Integer pageSize, ProductDto productDto) {
        PageHelper.startPage(current,pageSize);
        List<Product> productList = productMapper.list(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    /**
     * 商品信息添加
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        //TODO 添加商品的基本信息
        product.setAuditStatus(0); //审核状态
        product.setStatus(0); //上架状态
        productMapper.save(product);
        //TODO 添加商品Sku信息
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (ProductSku productSku : productSkuList) {
            productSku.setProductId(product.getId());
            //设置SkuCode,保证不重复即可 算法:常量前缀+UUID+ '_' +对应的商品ID
            productSku.setSkuCode(ProductSkuCodePrefixConstant.PRODUCT_SKU_CODE +
                    UUID.randomUUID().toString().replaceAll("-","")+"_"+product.getId());
            //SkuName
            productSku.setSkuName(product.getName()+productSku.getSkuSpec());
            //销量
            productSku.setSaleNum(0);
            //状态
            productSku.setStatus(0);
            productSkuMapper.save(productSku);
        }
        //TODO 添加商品详情信息
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    /**
     * 根据商品ID获取商品信息
     *
     * @param productId
     * @return
     */
    @Override
    public Product getOne(Long productId) {
        //TODO 获取基本商品信息
        Product product = productMapper.getOne(productId);
        //TODO 根据商品ID查询sku信息并封装
        List<ProductSku> productSkuList = productSkuMapper.getSkuByProductID(productId);
        product.setProductSkuList(productSkuList);
        //TODO 根据商品ID查询商品详情信息并封装
        ProductDetails productDetails = productDetailsMapper.getDetailsByProductID(productId);
        String imageUrls = productDetails.getImageUrls();
        product.setDetailsImageUrls(imageUrls);
        return product;
    }

    /**
     * 商品信息修改
     *
     * @param product
     */
    @Override
    public void update(Product product) {
        //TODO 修改商品信息
        productMapper.update(product);
        //TODO 修改商品Sku信息
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.update(productSku);
        });
        //TODO 修改商品详情信息
        String detailsImageUrls = product.getDetailsImageUrls();
        ProductDetails productDetails = productDetailsMapper.getDetailsByProductID(product.getId());
        productDetails.setImageUrls(detailsImageUrls);
        productDetailsMapper.update(productDetails);
    }
}
