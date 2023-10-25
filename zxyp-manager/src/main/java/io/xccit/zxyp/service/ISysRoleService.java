package io.xccit.zxyp.service;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.model.entity.system.SysRole;

import java.util.List;


/**
 * @author CH_ywx
 * @date 2023/10/20
 * @description 角色业务层
 */
public interface ISysRoleService {

    /**
     * 分页条件获取角色信息
     * @param sysRoleDto 角色条件参数接收
     * @param current 当前页
     * @param pageSize 每页条数
     * @return
     */
    PageInfo<SysRole> listRolePage(SysRoleDto sysRoleDto, Integer current, Integer pageSize);
}
