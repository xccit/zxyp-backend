package io.xccit.zxyp.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.dto.system.LoginDto;
import io.xccit.zxyp.entity.system.SysUser;
import io.xccit.zxyp.exception.PasswordWrongException;
import io.xccit.zxyp.exception.UserNotExistsException;
import io.xccit.zxyp.mapper.SysUserMapper;
import io.xccit.zxyp.service.ISysUserService;
import io.xccit.zxyp.vo.system.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 用户Service实现类
 */
@Slf4j
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @param loginDto 登录参数
     * @return 登录结果
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        String inputUsername = loginDto.getUserName();
        String inputPassword = loginDto.getPassword();
        log.info(inputUsername+"请求登录,密码:"+inputPassword);
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(inputUsername);
        if (sysUser == null){
            throw new UserNotExistsException(203,"用户名不存在");
        }
        String lockedPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!lockedPassword.equals(sysUser.getPassword())){
            throw new PasswordWrongException(204,"用户名或密码错误");
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue()
                .set("user:login"+token,
                JSON.toJSONString(sysUser),
                7,
                TimeUnit.DAYS); //TODO Token存Redis,7天有效
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }
}
