package io.xccit.zxyp.utils;

import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.model.vo.system.SysMenuVo;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 将菜单数据转换为动态菜单需要的数据格式并返回
 */
public class ToDynamicMenusVOUtil {

    /**
     * 根据菜单列表转换为前端需要的动态菜单格式
     *
     * @param sysMenus
     * @return
     */
    public static List<SysMenuVo> toDynamicMenuVO(List<SysMenu> sysMenus){
        List<SysMenuVo> sysMenuVos = new LinkedList<>();
        for (SysMenu sysMenu : sysMenus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setName(sysMenu.getComponent());
            sysMenuVo.setTitle(sysMenu.getTitle());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)){
                sysMenuVo.setChildren(toDynamicMenuVO(children));
            }
            sysMenuVos.add(sysMenuVo);
        }
        return sysMenuVos;
    }
}
