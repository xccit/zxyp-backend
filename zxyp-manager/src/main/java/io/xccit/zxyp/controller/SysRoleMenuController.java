package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/10/29
 * @description 角色菜单控制器
 */
@Tag(name = "角色菜单接口")
@RestController
@RequestMapping("/admin/system/roleMenu")
public class SysRoleMenuController {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 获取所有菜单,根据角色ID获取已分配菜单
     * @param roleId
     * @return
     */
    @Operation(summary = "获取菜单列表",description = "获取所有菜单,根据角色ID获取已分配菜单")
    @GetMapping("/{roleId}")
    public AjaxResult listAll(@PathVariable Long roleId){
        Map<String,Object> result = sysRoleMenuService.listAll(roleId);
        return AjaxResult.build(result, ResultCodeEnum.SUCCESS);
    }
}
