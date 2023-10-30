package io.xccit.zxyp.mapper.system;

import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/20
 * @description 角色mapper
 */
@Mapper
public interface SysRoleMapper {

    List<SysRole> listRolePage (SysRoleDto sysRoleDto);

    void saveRole(SysRole sysRole);

    void removeByIds(List<Long> roleIds);

    void updateRole(SysRole sysRole);

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> listAll();
}
