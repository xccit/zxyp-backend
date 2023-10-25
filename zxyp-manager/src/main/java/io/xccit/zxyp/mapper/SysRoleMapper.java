package io.xccit.zxyp.mapper;

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
}
