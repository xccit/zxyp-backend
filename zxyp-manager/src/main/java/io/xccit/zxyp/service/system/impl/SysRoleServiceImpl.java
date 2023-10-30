package io.xccit.zxyp.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.mapper.system.SysRoleMenuMapper;
import io.xccit.zxyp.mapper.system.SysUserRoleMapper;
import io.xccit.zxyp.model.dto.system.AssignMenuDto;
import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.mapper.system.SysRoleMapper;
import io.xccit.zxyp.model.entity.system.SysRole;
import io.xccit.zxyp.service.system.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/10/20
 * @description 角色业务层
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 分页条件获取角色信息
     *
     * @param sysRoleDto 角色条件参数接收
     * @param current    当前页
     * @param pageSize   每页条数
     * @return
     */
    @Override
    public PageInfo<SysRole> listRolePage(SysRoleDto sysRoleDto, Integer current, Integer pageSize) {
        PageHelper.startPage(current,pageSize);
        List<SysRole> roleList = sysRoleMapper.listRolePage(sysRoleDto);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(roleList);
        return sysRolePageInfo;
    }

    /**
     * 添加角色
     *
     * @param sysRole
     */
    @Override
    public void saveRole(SysRole sysRole) {
        sysRoleMapper.saveRole(sysRole);
    }

    /**
     * 根据ID删除角色/批量删除
     *
     * @param roleIds
     */
    @Override
    public void removeByIds(List<Long> roleIds) {
        sysRoleMapper.removeByIds(roleIds);
    }

    /**
     * 修改角色
     *
     * @param sysRole
     */
    @Override
    public void updateRole(SysRole sysRole) {
        log.info(JSON.toJSONString(sysRole));
        sysRoleMapper.updateRole(sysRole);
    }

    /**
     * 查询所有角色
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> list(Long userId) {
        Map<String,Object> result = new HashMap<>();
        //TODO 查询所有角色
        List<SysRole> roleList = sysRoleMapper.listAll();
        //TODO 根据用户ID查询分配过的角色
        List<Long> userRoleIds = sysUserRoleMapper.listRoleByUserID(userId);
        //TODO 结果封装
        result.put("allRolesList",roleList);
        result.put("sysUserRoles",userRoleIds);
        return result;
    }

    /**
     * 角色菜单分配
     *
     * @param assignMenuDto
     */
    @Override
    public void assignMenu(AssignMenuDto assignMenuDto) {
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        //删除分配过的菜单
        sysRoleMenuMapper.removeMenuByRoleID(assignMenuDto.getRoleId());
        //分配菜单
        if (menuInfo != null && menuInfo.size() > 0){
            sysRoleMenuMapper.assignMenu(assignMenuDto);
        }
    }
}
