package io.xccit.zxyp.controller.product;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
     * 分页列表条件查询
     * @param current
     * @param pageSize
     * @param productDto 商品搜索条件
     * @return
     */
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
    @Operation(summary = "商品信息添加")
    @PostMapping
    public AjaxResult save(@RequestBody Product product){
        productService.save(product);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
