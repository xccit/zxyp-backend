package io.xccit.zxyp.service.system.impl;

import io.xccit.zxyp.exception.HadChildrenException;
import io.xccit.zxyp.mapper.system.SysMenuMapper;
import io.xccit.zxyp.mapper.system.SysRoleMenuMapper;
import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.system.ISysMenuService;
import io.xccit.zxyp.utils.Menu2TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 菜单业务层
 */
@Service
@Slf4j
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 菜单树状结构
     *
     * @return
     */
    @Override
    public List<SysMenu> listMenuNodes() {
        //TODO 查询所有菜单
        List<SysMenu> allMenu = sysMenuMapper.listAll();
        if (CollectionUtils.isEmpty(allMenu)){
            return new ArrayList<>();
        }
        List<SysMenu> treeResult = Menu2TreeUtil.buildTree(allMenu);
        return treeResult;
    }

    /**
     * 菜单删除
     *
     * @param menuId
     */
    @Override
    public void removeMenu(Long menuId) {
        //根据ID查询其是否含有子菜单
        int count = sysMenuMapper.listChildren(menuId);
        if (count > 0){
            throw new HadChildrenException(ResultCodeEnum.MENU_ERROR);
        }
        //删除
        sysMenuMapper.remove(menuId);
    }

    /**
     * 菜单更新
     *
     * @param sysMenu
     */
    @Override
    public void updateMenu(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    /**
     * 菜单添加
     *
     * @param sysMenu
     */
    @Transactional
    @Override
    public void saveMenu(SysMenu sysMenu) {
        //TODO 添加一个新的菜单
        sysMenuMapper.save(sysMenu);
        //TODO 将新添加到菜单父级设置为半开,否则在为角色分配菜单时新添加的菜单默认也会选中
        updateParentMenuHalf(sysMenu);
    }

    /**
     * 将新添加到菜单父级设置为半开,否则在为角色分配菜单时新添加的菜单默认也会选中
     * @param sysMenu
     */
    private void updateParentMenuHalf(SysMenu sysMenu) {
        Long parentId = sysMenu.getParentId();
        //查询此菜单父级ID对应的菜单信息
        SysMenu parentMenu = sysMenuMapper.getByID(parentId);
        if (parentMenu != null){
            //设置半开
            sysRoleMenuMapper.updateIsHalfByMenuID(parentMenu.getId());
            //递归调用,逐级设置半开
            updateParentMenuHalf(parentMenu);
        }
    }
}
