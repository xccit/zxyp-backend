package io.xccit.zxyp.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.model.entity.system.SysRole;
import io.xccit.zxyp.service.ISysRoleService;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * 分页查询角色信息
     * @param current 当前页
     * @param pageSize 每页条数
     * @param sysRoleDto 角色条件查询参数接收
     * @return
     */
    @Operation(summary = "分页条件查询")
    @GetMapping("/list/{current}/{pageSize}")
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
    @Operation(summary = "添加角色")
    @PostMapping("/save")
    public AjaxResult saveRole(@RequestBody SysRole sysRole){
        sysRoleService.saveRole(sysRole);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据ID删除角色/批量删除
     * @param roleIds
     * @return
     */
    @Operation(summary = "根据ID删除/批量删除")
    @DeleteMapping()
    public AjaxResult removeRoleById(@RequestBody List<Long> roleIds){
        sysRoleService.removeByIds(roleIds);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改角色")
    @PutMapping
    public AjaxResult updateRole(@RequestBody SysRole sysRole){
        sysRoleService.updateRole(sysRole);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
