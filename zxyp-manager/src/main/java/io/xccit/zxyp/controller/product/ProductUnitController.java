package io.xccit.zxyp.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.model.entity.base.ProductUnit;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.product.IProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品单位控制器
 */
@Tag(name = "商品单位接口")
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private IProductUnitService productUnitService;

    /**
     * 商品单位列表
     * @return
     */
    @Log(title = "商品单位管理:商品单位列表",businessType = 4)
    @Operation(summary = "商品单位列表")
    @GetMapping
    public AjaxResult list(){
        List<ProductUnit> productUnitList = productUnitService.list();
        return AjaxResult.build(productUnitList, ResultCodeEnum.SUCCESS);
    }
}
