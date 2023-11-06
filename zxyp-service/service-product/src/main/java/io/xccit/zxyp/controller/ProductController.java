package io.xccit.zxyp.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.h5.ProductSkuDto;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.h5.ProductItemVo;
import io.xccit.zxyp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 商品控制器
 */
@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value="/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 商品分页条件查询
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    @Operation(summary = "商品分页条件查询")
    @GetMapping("/{page}/{limit}")
    public AjaxResult<PageInfo<ProductSku>> findByPage(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Integer page,
                                                       @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Integer limit,
                                                       @Parameter(name = "productSkuDto", description = "搜索条件对象", required = false) ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.listProductPage(page, limit, productSkuDto);
        return AjaxResult.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 商品详情
     * @param skuId
     * @return
     */
    @Operation(summary = "商品详情")
    @GetMapping("/item/{skuId}")
    public AjaxResult<ProductItemVo> details(@PathVariable Long skuId){
        ProductItemVo productItemVo = productService.details(skuId);
        return AjaxResult.build(productItemVo , ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据SkuID获取商品Sku信息,供购物车远程调用接口
     * @param skuId
     * @return
     */
    @Operation(summary = "根据SkuID获取商品Sku信息,供购物车远程调用接口")
    @GetMapping("/getBySkuId/{skuId}")
    public ProductSku getProductSkuByID(@PathVariable Long skuId){
        ProductSku productSku = productService.getProductSkuByID(skuId);
        return productSku;
    }
}
