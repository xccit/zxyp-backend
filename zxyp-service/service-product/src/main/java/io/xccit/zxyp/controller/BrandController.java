package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.product.Brand;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 品牌控制器
 */
@Tag(name = "前台品牌接口")
@RestController
@RequestMapping("/api/product/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 品牌列表
     *
     * @return
     */
    @Operation(summary = "品牌列表")
    @GetMapping("findAll")
    public AjaxResult<List<Brand>> findAll() {
        List<Brand> list = brandService.list();
        return AjaxResult.build(list, ResultCodeEnum.SUCCESS);
    }
}
