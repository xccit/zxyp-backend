package io.xccit.zxyp.controller.product;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.product.Brand;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.product.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 品牌控制器
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    /**
     * 品牌分页列表
     * @param current
     * @param pageSize
     * @return
     */
    @Operation(summary = "品牌分页列表")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult listBrandPage(@PathVariable Integer current,
                                    @PathVariable Integer pageSize){
        PageInfo<Brand> pageInfo = brandService.listBrandPage(current,pageSize);
        return AjaxResult.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 品牌添加
     * @param brand
     * @return
     */
    @Operation(summary = "品牌添加")
    @PostMapping
    public AjaxResult saveBrand(@RequestBody Brand brand){
        brandService.saveBrand(brand);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 品牌修改
     * @param brand
     * @return
     */
    @Operation(summary = "品牌修改")
    @PutMapping
    public AjaxResult updateBrand(@RequestBody Brand brand){
        brandService.updateBrand(brand);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 品牌修改
     * @param ids
     * @return
     */
    @Operation(summary = "品牌删除/批量删除")
    @DeleteMapping("/{ids}")
    public AjaxResult removeBrand(@PathVariable List<Long> ids){
        brandService.removeBrand(ids);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

}
