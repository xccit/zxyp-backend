package io.xccit.zxyp.service.system;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.system.LoginDto;
import io.xccit.zxyp.model.dto.system.SysUserDto;
import io.xccit.zxyp.model.entity.system.SysUser;
import io.xccit.zxyp.model.vo.system.LoginVo;
import io.xccit.zxyp.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 用户Service
 */
public interface ISysUserService {
    /**
     * 登录
     * @param loginDto 登录参数
     * @return 登录结果
     */
    LoginVo login(LoginDto loginDto);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    SysUser userInfo(String token);

    /**
     * 用户登出
     * @param token
     */
    void logout(String token);

    /**
     * 用户分页列表查询
     * @param sysUserDto
     * @param current
     * @param pageSize
     * @return
     */
    PageInfo<SysUser> listUserPage(SysUserDto sysUserDto, Integer current, Integer pageSize);

    /**
     * 用户删除
     * @param userIds
     */
    void removeUser(List<Long> userIds);

    /**
     * 用户修改
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 用户添加
     * @param sysUser
     */
    void saveUser(SysUser sysUser);

    /**
     * 用户登录后根据用户ID获取角色信息,根据角色信息获取菜单列表并封装返回
     * @return
     */
    List<SysMenuVo> getMenus();
}
