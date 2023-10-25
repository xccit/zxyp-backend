package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 用户Mapper
 */
@Mapper
public interface SysUserMapper {
    SysUser selectUserInfoByUserName(String inputUsername);
}
