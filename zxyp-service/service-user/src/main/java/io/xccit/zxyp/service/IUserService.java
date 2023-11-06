package io.xccit.zxyp.service;

import io.xccit.zxyp.model.dto.h5.UserLoginDto;
import io.xccit.zxyp.model.dto.h5.UserRegisterDto;
import io.xccit.zxyp.model.entity.user.UserInfo;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 前台用户业务层
 */
public interface IUserService {

    /**
     * 用户注册
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    String login(UserLoginDto userLoginDto);

    /**
     * 获取当前登录用户信息
     * @param token
     * @return
     */
    UserInfo getCurrentUser(String token);
}
