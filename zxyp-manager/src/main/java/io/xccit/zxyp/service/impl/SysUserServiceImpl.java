package io.xccit.zxyp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.dto.system.LoginDto;
import io.xccit.zxyp.entity.system.SysUser;
import io.xccit.zxyp.exception.PasswordWrongException;
import io.xccit.zxyp.exception.UserNotExistsException;
import io.xccit.zxyp.exception.ValidateCodeErrorException;
import io.xccit.zxyp.mapper.SysUserMapper;
import io.xccit.zxyp.service.ISysUserService;
import io.xccit.zxyp.util.JWTUtils;
import io.xccit.zxyp.vo.common.ResultCodeEnum;
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
        //TODO 验证码校验
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        String codeValue = (String) redisTemplate.opsForValue().get("user:validate" + codeKey);
        //TODO Redis中的code是否为空 或者 输入的验证码与Redis中的code是否一致(忽略大小写)
        if (StringUtils.isEmpty(codeValue) || !StrUtil.equalsIgnoreCase(codeValue,captcha)){
            throw new ValidateCodeErrorException(ResultCodeEnum.CODE_VALIDATE_ERROR);
        }
        //TODO 验证码校验通过后,删除Redis中的验证码
        redisTemplate.delete("user:validate" + codeKey);
        log.info(inputUsername+"请求登录,密码:"+inputPassword);
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(inputUsername);
        if (sysUser == null){
            throw new UserNotExistsException(203,"用户名不存在");
        }
        String lockedPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!lockedPassword.equals(sysUser.getPassword())){
            throw new PasswordWrongException(204,"用户名 或密码错误");
        }
        //TODO 使用jwt生成token
        String token = JWTUtils.createToken(sysUser.getId(),sysUser.getUserName());
        redisTemplate.opsForValue()
                .set("user:login"+token,
                JSON.toJSONString(sysUser),
                7,
                TimeUnit.DAYS); //TODO Token存Redis,7天有效
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * 通过token获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public SysUser userInfo(String token) {
        String userJson = (String) redisTemplate.opsForValue().get("user:login" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }
}
