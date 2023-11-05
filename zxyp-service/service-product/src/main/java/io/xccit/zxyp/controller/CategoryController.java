package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 分类控制器
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping(value="/api/product/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 获取分类树形数据
     * @return
     */
    @Operation(summary = "获取分类树形数据")
    @GetMapping("findCategoryTree")
    public AjaxResult<List<Category>> findCategoryTree(){
        List<Category> list = categoryService.findCategoryTree();
        return AjaxResult.build(list,  ResultCodeEnum.SUCCESS);
    }
}
