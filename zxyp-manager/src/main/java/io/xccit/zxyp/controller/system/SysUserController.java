package io.xccit.zxyp.controller.system;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.model.dto.system.AssignRoleDto;
import io.xccit.zxyp.model.dto.system.SysUserDto;
import io.xccit.zxyp.model.entity.system.SysUser;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.system.ISysUserRoleService;
import io.xccit.zxyp.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/26
 * @description 用户Controller
 */
@Tag(name = "用户接口",description = "用户接口")
@RestController
@RequestMapping("/admin/system/user")
public class SysUserController {

    private final ISysUserService sysUserService;
    private final ISysUserRoleService sysUserRoleService;
    @Autowired
    public SysUserController(ISysUserService sysUserService,ISysUserRoleService sysUserRoleService) {
        this.sysUserService = sysUserService;
        this.sysUserRoleService = sysUserRoleService;
    }

    /**
     * 用户信息分页列表查询
     * @param current
     * @param pageSize
     * @param sysUserDto
     * @return
     */
    @Log(title = "后台用户接口:用户分页列表",businessType = 4)
    @Operation(summary = "用户分页列表",description = "用户分页接口,亦可根据条件查询")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult listUserPage(@PathVariable Integer current,
                                   @PathVariable Integer pageSize,
                                   SysUserDto sysUserDto){
        PageInfo<SysUser> userPageInfo = sysUserService.listUserPage(sysUserDto,current,pageSize);
        return AjaxResult.build(userPageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户添加
     * @param sysUser
     * @return
     */
    @Log(title = "后台用户接口:用户添加",businessType = 1)
    @Operation(summary = "用户添加",description = "用户添加")
    @PostMapping
    public AjaxResult saveUser(@RequestBody SysUser sysUser){
        sysUserService.saveUser(sysUser);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户删除
     * @param userIds
     * @return
     */
    @Log(title = "后台用户接口:用户删除",businessType = 2)
    @Operation(summary = "用户删除/批量删除",description = "根据用户集合列表单删或批量删除")
    @DeleteMapping("/{userIds}")
    public AjaxResult removeUser(@PathVariable List<Long> userIds){
        sysUserService.removeUser(userIds);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户修改
     * @param sysUser
     * @return
     */
    @Log(title = "后台用户接口:用户修改",businessType = 3)
    @Operation(summary = "用户修改",description = "用户修改")
    @PutMapping
    public AjaxResult updateUser(@RequestBody SysUser sysUser){
        sysUserService.updateUser(sysUser);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户角色分配
     * @param assignRoleDto
     * @return
     */
    @Log(title = "后台用户接口:用户角色分配",businessType = 0)
    @Operation(summary = "用户角色分配",description = "用户角色分配")
    @PostMapping("/assign")
    public AjaxResult assignRole(@RequestBody AssignRoleDto assignRoleDto){
        sysUserRoleService.assignRole(assignRoleDto);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
