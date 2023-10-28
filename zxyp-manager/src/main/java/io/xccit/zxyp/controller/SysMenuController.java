package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 菜单控制层
 */
@Tag(name = "菜单接口")
@RestController
@RequestMapping("/admin/system/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;
    @Operation(summary = "菜单树状接口")
    @GetMapping("/nodes")
    public AjaxResult listMenuNodes(){
        List<SysMenu> result = sysMenuService.listMenuNodes();
        return AjaxResult.build(result, ResultCodeEnum.SUCCESS);
    }

}
