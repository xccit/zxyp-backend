package io.xccit.zxyp.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.system.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 菜单树状接口
     * @return
     */
    @Log(title = "菜单接口:菜单树状接口",businessType = 4)
    @Operation(summary = "菜单树状接口")
    @GetMapping("/nodes")
    public AjaxResult listMenuNodes(){
        List<SysMenu> result = sysMenuService.listMenuNodes();
        return AjaxResult.build(result, ResultCodeEnum.SUCCESS);
    }

    /**
     * 菜单删除
     * @param menuId
     * @return
     */
    @Log(title = "菜单接口:菜单删除",businessType = 2)
    @Operation(summary = "菜单删除",description = "先根据ID查询此菜单下是否包含子菜单,如果有,抛出异常,没有则直接删除")
    @DeleteMapping("/{menuId}")
    public AjaxResult removeMenu(@PathVariable Long menuId){
        sysMenuService.removeMenu(menuId);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 菜单更新
     * @param sysMenu
     * @return
     */
    @Log(title = "菜单接口:菜单更新",businessType = 3)
    @Operation(summary = "菜单更新")
    @PutMapping
    public AjaxResult updateMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.updateMenu(sysMenu);
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 菜单添加
     * @param sysMenu
     * @return
     */
    @Log(title = "菜单接口:菜单添加",businessType = 1)
    @Operation(summary = "菜单添加")
    @PostMapping
    public AjaxResult saveMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.saveMenu(sysMenu);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

}
