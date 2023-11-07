package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.base.Region;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 地区控制器
 */
@Tag(name = "地区接口")
@RestController
@RequestMapping("/api/user/region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    /**
     * 根据上级地区ID获取地区信息
     * @param parentCode
     * @return
     */
    @Operation(summary = "根据上级地区ID获取地区信息")
    @GetMapping("/findByParentCode/{parentCode}")
    public AjaxResult<List<Region>> getRegionByParent(@PathVariable Long parentCode){
        List<Region> result = regionService.getRegionByParent(parentCode);
        return AjaxResult.build(result, ResultCodeEnum.SUCCESS);
    }
}
