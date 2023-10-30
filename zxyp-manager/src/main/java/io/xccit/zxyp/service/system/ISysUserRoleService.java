package io.xccit.zxyp.service.system;

import io.xccit.zxyp.model.dto.system.AssignRoleDto;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 用户角色关系业务层
 */
public interface ISysUserRoleService {

    /**
     * 用户角色分配
     * @param assignRoleDto
     */
    void assignRole(AssignRoleDto assignRoleDto);
}
