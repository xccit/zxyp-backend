package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.SysRoleMapper;
import io.xccit.zxyp.mapper.SysUserRoleMapper;
import io.xccit.zxyp.model.dto.system.AssignRoleDto;
import io.xccit.zxyp.model.entity.system.SysRole;
import io.xccit.zxyp.service.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 用户角色分配
     *
     * @param assignRoleDto
     */
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
        Long userId = assignRoleDto.getUserId();
        List<Long> roleIdList = assignRoleDto.getRoleIdList();
        //TODO 根据用户ID删除之前分配的角色
        sysUserRoleMapper.removeRolesByUserID(userId);
        //TODO 重新分配角色
        for (Long roleId : roleIdList) {
            sysUserRoleMapper.assignRole(userId,roleId);
        }
    }
}
