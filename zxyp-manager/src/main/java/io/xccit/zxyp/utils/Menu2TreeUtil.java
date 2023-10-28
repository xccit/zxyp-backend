package io.xccit.zxyp.utils;

import io.xccit.zxyp.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 菜单树构建工具
 * 实现思路:找到第一层菜单,与所有菜单同时传入查找子菜单的方法中,循环查找下级菜单parentId与上级ID相同的菜单,封装到上级菜单的children属性
 * 一级菜单+所有菜单--->二级菜单+所有菜单--->三级菜单+所有菜单...递归查找
 */
public class Menu2TreeUtil {

    /**
     * 树形结构构建
     * @param sysMenus 所有菜单
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenus){
        List<SysMenu> treeMenus = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            //一级菜单
            if (sysMenu.getParentId().longValue() == 0){
                treeMenus.add(selectTree(sysMenu,sysMenus));
            }
        }
        return treeMenus;
    }

    /**
     * 递归查询子菜单
     * @param sysMenu
     * @param sysMenus
     * @return
     */
    private static SysMenu selectTree(SysMenu sysMenu, List<SysMenu> sysMenus) {
        //设置上级菜单子菜单初始化
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenu item : sysMenus) {
            if (item.getParentId().longValue() == sysMenu.getId()){
                //添加下级,将下级当作刚才的上级,继续查找下一级
                sysMenu.getChildren().add(selectTree(item,sysMenus));
            }
        }
        return sysMenu;
    }
}
