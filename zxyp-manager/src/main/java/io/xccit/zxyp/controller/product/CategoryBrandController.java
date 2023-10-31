package io.xccit.zxyp.controller.product;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.product.CategoryBrandDto;
import io.xccit.zxyp.model.entity.product.CategoryBrand;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.product.ICategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 品牌分类控制器
 */
@Tag(name = "品牌分类接口")
@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private ICategoryBrandService categoryBrandService;

    /**
     * 品牌分类分页条件列表
     * @param current
     * @param pageSize
     * @param categoryBrandDto
     * @return
     */
    @Operation(summary = "品牌分类分页条件列表")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult list(@PathVariable Integer current,
                           @PathVariable Integer pageSize,
                           CategoryBrandDto categoryBrandDto){
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.listCategoryBrandPage(current,pageSize,categoryBrandDto);
        return AjaxResult.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 品牌分类添加
     * @param categoryBrand
     * @return
     */
    @Operation(summary = "品牌分类添加")
    @PostMapping
    public AjaxResult save(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.save(categoryBrand);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 品牌分类修改
     * @param categoryBrand
     * @return
     */
    @Operation(summary = "品牌分类修改")
    @PutMapping
    public AjaxResult update(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.update(categoryBrand);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 品牌分类删除
     * @param ids
     * @return
     */
    @Operation(summary = "品牌分类删除")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids){
        categoryBrandService.remove(ids);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
