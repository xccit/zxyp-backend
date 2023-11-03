package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.h5.IndexVo;
import io.xccit.zxyp.service.ICategoryService;
import io.xccit.zxyp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description
 */
@CrossOrigin
@Tag(name = "前台首页接口")
@RestController
@RequestMapping("/api/product/index")
public class IndexController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;

    /**
     * 首页数据展示接口
     * @return
     */
    @Operation(summary = "首页数据获取")
    @GetMapping
    public AjaxResult indexData(){
        IndexVo indexVo = new IndexVo();
        //TODO 首页一级分类
        List<Category> categoryList = categoryService.listOneCategory();
        //TODO 首页商品Sku销量前10
        List<ProductSku> productSkuList = productService.listProductSkuBySale();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return AjaxResult.build(indexVo, ResultCodeEnum.SUCCESS);
    }

}
