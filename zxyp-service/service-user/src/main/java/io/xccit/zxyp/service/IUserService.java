package io.xccit.zxyp.service;

import io.xccit.zxyp.model.dto.h5.UserRegisterDto;

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
}
