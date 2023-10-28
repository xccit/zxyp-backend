package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 菜单Mapper
 */
@Mapper
public interface SysMenuMapper {
    /**
     * 查询所有菜单
     * @return
     */
    List<SysMenu>  listAll();

    /**
     * 根据ID查询其是否含有子菜单
     * @param menuId
     * @return
     */
    int listChildren(Long menuId);

    /**
     * 删除菜单
     * @param menuId
     */
    void remove(Long menuId);

    /**
     * 添加菜单
     * @param sysMenu
     */
    void save(SysMenu sysMenu);

    /**
     * 更新菜单
     * @param sysMenu
     */
    void update(SysMenu sysMenu);
}
