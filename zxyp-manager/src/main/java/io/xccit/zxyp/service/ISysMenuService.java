package io.xccit.zxyp.service;

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
}
