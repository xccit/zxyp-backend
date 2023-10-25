package io.xccit.zxyp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.system.SysRoleDto;
import io.xccit.zxyp.mapper.SysRoleMapper;
import io.xccit.zxyp.model.entity.system.SysRole;
import io.xccit.zxyp.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
