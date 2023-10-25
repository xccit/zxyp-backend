package io.xccit.zxyp.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.model.entity.system.SysRole;
import io.xccit.zxyp.service.ISysRoleService;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/list/{current}/{pageSize}")
    public AjaxResult listRolePage(@PathVariable Integer current,
                                   @PathVariable Integer pageSize,
                                   @RequestBody SysRoleDto sysRoleDto){
        PageInfo<SysRole> rolePage = sysRoleService.listRolePage(sysRoleDto,current,pageSize);
        return AjaxResult.build(rolePage, ResultCodeEnum.SUCCESS);
    }
}
