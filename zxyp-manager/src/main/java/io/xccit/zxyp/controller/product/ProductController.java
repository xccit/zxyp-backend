package io.xccit.zxyp.controller.product;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.model.dto.product.ProductDto;
import io.xccit.zxyp.model.entity.product.Product;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品控制器
 */
@Tag(name="商品接口")
@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 根据商品ID获取商品信息
     * @param productId
     * @return
     */
    @Log(title = "商品管理:根据商品ID获取商品信息",businessType = 4)
    @Operation(summary = "根据商品ID获取商品信息")
    @GetMapping("/{productId}")
    public AjaxResult getOne(@PathVariable Long productId){
        Product product = productService.getOne(productId);
        return AjaxResult.build(product,ResultCodeEnum.SUCCESS);
    }

    /**
     * 分页列表条件查询
     * @param current
     * @param pageSize
     * @param productDto 商品搜索条件
     * @return
     */
    @Log(title = "商品管理:商品分页条件列表",businessType = 4)
    @Operation(summary = "商品分页条件列表",description = "根据条件查询,条件可为空")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult listProductPage(@PathVariable Integer current,
                                      @PathVariable Integer pageSize,
                                      ProductDto productDto){
        PageInfo<Product> pageInfo = productService.listProductPage(current,pageSize,productDto);
        return AjaxResult.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品信息添加
     * @param product
     * @return
     */
    @Log(title = "商品管理:商品信息添加",businessType = 1)
    @Operation(summary = "商品信息添加")
    @PostMapping
    public AjaxResult save(@RequestBody Product product){
        productService.save(product);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @Log(title = "商品管理:商品信息修改",businessType = 3)
    @Operation(summary = "商品信息修改")
    @PutMapping
    public AjaxResult update(@RequestBody Product product){
        productService.update(product);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品信息删除
     * @param productId
     * @return
     */
    @Log(title = "商品管理:商品信息删除",businessType = 2)
    @Operation(summary = "商品信息删除")
    @DeleteMapping("/{productId}")
    public AjaxResult remove(@Parameter(name = "productId",description = "商品ID",required = true)
                                 @PathVariable Long productId){
        productService.remove(productId);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品审核
     * @param id
     * @param auditStatus
     * @return
     */
    @Log(title = "商品管理:商品审核",businessType = 0)
    @Operation(summary = "商品审核")
    @GetMapping("/audit/{id}/{auditStatus}")
    public AjaxResult updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.audit(id, auditStatus);
        return AjaxResult.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 商品上下架
     * @param id
     * @param status
     * @return
     */
    @Log(title = "商品管理:商品上下架",businessType = 0)
    @Operation(summary = "商品上下架")
    @GetMapping("/status/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.status(id, status);
        return AjaxResult.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
