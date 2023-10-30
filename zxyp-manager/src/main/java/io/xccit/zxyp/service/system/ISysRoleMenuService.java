package io.xccit.zxyp.service.system;

import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/10/29
 * @description
 */
public interface ISysRoleMenuService {

    /**
     * 获取所有菜单,根据角色ID获取已分配菜单
     * @param roleId
     * @return
     */
    Map<String, Object> listAll(Long roleId);
}
