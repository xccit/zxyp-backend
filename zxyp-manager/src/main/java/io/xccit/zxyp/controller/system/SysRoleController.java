package io.xccit.zxyp.controller.system;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.model.dto.system.AssignMenuDto;
import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.model.entity.system.SysRole;
import io.xccit.zxyp.service.system.ISysRoleService;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author CH_ywx
 * @date 2023/10/20
 * @description 角色控制器
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping("/admin/system/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 根据用户ID查询所有角色/分配角色用
     * @return
     */
    @Log(title = "角色接口:查询所有角色",businessType = 4)
    @Operation(summary = "查询所有角色",description = "根据用户ID查询所有角色/分配角色用")
    @GetMapping("/{userId}")
    public AjaxResult list(@PathVariable Long userId){
        Map<String,Object> map = sysRoleService.list(userId);
        return AjaxResult.build(map,ResultCodeEnum.SUCCESS);
    }

    /**
     * 分页查询角色信息
     * @param current 当前页
     * @param pageSize 每页条数
     * @param sysRoleDto 角色条件查询参数接收
     * @return
     */
    @Log(title = "角色接口:分页条件查询",businessType = 4)
    @Operation(summary = "分页条件查询")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult listRolePage(@PathVariable Integer current,
                                   @PathVariable Integer pageSize,
                                   SysRoleDto sysRoleDto){
        PageInfo<SysRole> rolePage = sysRoleService.listRolePage(sysRoleDto,current,pageSize);
        return AjaxResult.build(rolePage, ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @Log(title = "角色接口:添加角色",businessType = 1)
    @Operation(summary = "添加角色")
    @PostMapping
    public AjaxResult saveRole(@RequestBody SysRole sysRole){
        sysRoleService.saveRole(sysRole);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据ID删除角色/批量删除
     * @param roleIds
     * @return
     */
    @Log(title = "角色接口:删除角色",businessType = 2)
    @Operation(summary = "根据ID删除/批量删除")
    @DeleteMapping()
    public AjaxResult removeRoleById(@RequestBody List<Long> roleIds){
        sysRoleService.removeByIds(roleIds);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 角色修改
     * @param sysRole
     * @return
     */
    @Log(title = "角色接口:修改角色",businessType = 3)
    @Operation(summary = "修改角色")
    @PutMapping
    public AjaxResult updateRole(@RequestBody SysRole sysRole){
        sysRoleService.updateRole(sysRole);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 分配菜单
     *
     * @param assignMenuDto
     * @return
     */
    @Log(title = "角色接口:角色分配菜单",businessType = 0)
    @Operation(summary = "角色分配菜单")
    @PostMapping("/assign")
    public AjaxResult assignMenu(@RequestBody AssignMenuDto assignMenuDto){
        sysRoleService.assignMenu(assignMenuDto);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
