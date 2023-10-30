package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.SysMenuMapper;
import io.xccit.zxyp.mapper.SysRoleMenuMapper;
import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.service.ISysMenuService;
import io.xccit.zxyp.service.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/10/29
 * @description 角色菜单服务层
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 获取所有菜单,根据角色ID获取已分配菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> listAll(Long roleId) {
        Map<String,Object> result = new HashMap<>();
        //获取菜单树形结构
        List<SysMenu> allMenus = sysMenuService.listMenuNodes();
        //根据角色ID获取已分配菜单
        List<Long> rolesMenus =  sysRoleMenuMapper.listMenuByRoleID(roleId);
        result.put("allMenus",allMenus);
        result.put("rolesMenus",rolesMenus);
        return result;
    }
}
