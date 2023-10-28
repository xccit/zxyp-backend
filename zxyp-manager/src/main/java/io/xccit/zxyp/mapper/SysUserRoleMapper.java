package io.xccit.zxyp.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 用户角色关系Mapper
 */
@Mapper
public interface SysUserRoleMapper {
    /**
     * 根据用户ID删除之前分配的角色
     * @param userId
     */
    void removeRolesByUserID(Long userId);

    /**
     * 重新分配角色
     * @param userId
     * @param roleId
     */
    void assignRole(Long userId, Long roleId);

    /**
     * 根据用户ID查询拥有的角色ID
     * @param userId
     * @return
     */
    List<Long> listRoleByUserID(Long userId);
}
