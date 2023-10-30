package io.xccit.zxyp.service.system;

import io.xccit.zxyp.model.entity.system.SysMenu;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description
 */
public interface ISysMenuService {

    /**
     * 菜单树状结构
     * @return
     */
    List<SysMenu> listMenuNodes();

    /**
     * 菜单删除
     * @param menuId
     */
    void removeMenu(Long menuId);

    /**
     * 菜单更新
     * @param sysMenu
     */
    void updateMenu(SysMenu sysMenu);

    /**
     * 菜单添加
     * @param sysMenu
     */
    void saveMenu(SysMenu sysMenu);
}
