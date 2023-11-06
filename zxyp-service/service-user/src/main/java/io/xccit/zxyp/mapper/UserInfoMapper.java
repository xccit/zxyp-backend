package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 前台用户Mapper
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    UserInfo getUserByUserName(String username);

    /**
     * 保存用户信息(注册)
     * @param userInfo
     */
    void save(UserInfo userInfo);
}
