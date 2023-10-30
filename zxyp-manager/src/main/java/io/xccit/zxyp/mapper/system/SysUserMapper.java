package io.xccit.zxyp.mapper.system;

import io.xccit.zxyp.model.dto.system.SysUserDto;
import io.xccit.zxyp.model.entity.system.SysMenu;
import io.xccit.zxyp.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 用户Mapper
 */
@Mapper
public interface SysUserMapper {
    SysUser selectUserInfoByUserName(String inputUsername);

    List<SysUser> listUserPage(SysUserDto sysUserDto);

    void remove(List<Long> userIds);

    void update(SysUser sysUser);

    void save(SysUser sysUser);

    /**
     * 根据用户ID查询用户拥有的菜单信息
     * @param userId
     * @return
     */
    List<SysMenu> listMenusByUserID(Long userId);
}
