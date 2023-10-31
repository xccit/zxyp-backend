package io.xccit.zxyp.controller.product;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.product.ProductSpec;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.product.IProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品规格控制器
 */
@Tag(name = "商品规格接口")
@RestController
@RequestMapping("/admin/product/productSpec")
public class ProductSpecController {

    @Autowired
    private IProductSpecService productSpecService;

    /**
     * 商品规格分页列表
     * @param current
     * @param pageSize
     * @return
     */
    @Operation(summary = "商品规格分页列表")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult listSpecPage(@PathVariable Integer current,
                           @PathVariable Integer pageSize){
        PageInfo<ProductSpec> pageInfo = productSpecService.listSpecPage(current,pageSize);
        return AjaxResult.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品规格添加
     * @param productSpec
     * @return
     */
    @Operation(summary = "商品规格添加")
    @PostMapping
    public AjaxResult save(@RequestBody ProductSpec productSpec){
        productSpecService.save(productSpec);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品规格修改
     * @param productSpec
     * @return
     */
    @Operation(summary = "商品规格修改")
    @PutMapping
    public AjaxResult update(@RequestBody ProductSpec productSpec){
        productSpecService.update(productSpec);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品规格删除/批量删除
     * @param ids
     * @return
     */
    @Operation(summary = "商品规格删除/批量删除")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids){
        productSpecService.remove(ids);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
