package io.xccit.zxyp.service;

import io.xccit.zxyp.dto.system.LoginDto;
import io.xccit.zxyp.entity.system.SysUser;
import io.xccit.zxyp.vo.system.LoginVo;

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
}
