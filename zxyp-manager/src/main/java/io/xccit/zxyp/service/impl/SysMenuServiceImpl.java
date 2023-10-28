package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.exception.HadChildrenException;
import io.xccit.zxyp.mapper.SysMenuMapper;
import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.ISysMenuService;
import io.xccit.zxyp.utils.Menu2TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Override
    public void saveMenu(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
    }
}
