package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.dto.system.AssignMenuDto;
import io.xccit.zxyp.model.entity.system.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/10/29
 * @description 角色菜单mapper
 */
public interface SysRoleMenuMapper {

    /**
     * 根据角色ID获取已分配菜单
     * @param roleId
     * @return
     */
    List<Long> listMenuByRoleID(Long roleId);

    /**
     * 重新分配菜单
     */
    void assignMenu(AssignMenuDto assignMenuDto);

    /**
     * 根据RoleID删除原本分配的菜单
     * @param roleId
     */
    void removeMenuByRoleID(Long roleId);
}
