package io.xccit.zxyp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.constants.RedisPrefixConstant;
import io.xccit.zxyp.exception.UserNameExistsException;
import io.xccit.zxyp.model.dto.system.LoginDto;
import io.xccit.zxyp.model.dto.system.SysUserDto;
import io.xccit.zxyp.model.entity.system.SysUser;
import io.xccit.zxyp.exception.PasswordWrongException;
import io.xccit.zxyp.exception.UserNotExistsException;
import io.xccit.zxyp.exception.ValidateCodeErrorException;
import io.xccit.zxyp.mapper.SysUserMapper;
import io.xccit.zxyp.service.ISysUserService;
import io.xccit.zxyp.util.JWTUtils;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.system.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
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
     *
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
        String codeValue = (String) redisTemplate.opsForValue().get(RedisPrefixConstant.VALIDATE_CODE_KEY + codeKey);
        //TODO Redis中的code是否为空 或者 输入的验证码与Redis中的code是否一致(忽略大小写)
        if (StringUtils.isEmpty(codeValue) || !StrUtil.equalsIgnoreCase(codeValue, captcha)) {
            throw new ValidateCodeErrorException(ResultCodeEnum.CODE_VALIDATE_ERROR);
        }
        //TODO 验证码校验通过后,删除Redis中的验证码
        redisTemplate.delete(RedisPrefixConstant.VALIDATE_CODE_KEY + codeKey);
        log.info(inputUsername + "请求登录,密码:" + inputPassword);
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(inputUsername);
        if (sysUser == null) {
            throw new UserNotExistsException(203, "用户名不存在");
        }
        String lockedPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!lockedPassword.equals(sysUser.getPassword())) {
            throw new PasswordWrongException(204, "用户名 或密码错误");
        }
        //TODO 使用jwt生成token
        String token = JWTUtils.createToken(sysUser.getId(), sysUser.getUserName());
        redisTemplate.opsForValue()
                .set(RedisPrefixConstant.LOGIN_USER_PREFIX + token,
                        JSON.toJSONString(sysUser),
                        60,
                        TimeUnit.MINUTES); //TODO Token存Redis,60分钟有效
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
        String userJson = (String) redisTemplate.opsForValue().get(RedisPrefixConstant.LOGIN_USER_PREFIX + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    /**
     * 用户登出
     *
     * @param token
     */
    @Override
    public void logout(String token) {
        //TODO 删除已登录用户的redis缓存
        redisTemplate.delete(RedisPrefixConstant.LOGIN_USER_PREFIX + token);
    }

    /**
     * 用户分页列表查询
     *
     * @param sysUserDto
     * @param current
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SysUser> listUserPage(SysUserDto sysUserDto, Integer current, Integer pageSize) {
        PageHelper.startPage(current, pageSize);
        List<SysUser> userList = sysUserMapper.listUserPage(sysUserDto);
        PageInfo<SysUser> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    /**
     * 用户删除/批量删除
     *
     * @param userIds
     */
    @Override
    public void removeUser(List<Long> userIds) {
        sysUserMapper.remove(userIds);
    }

    /**
     * 用户修改
     *
     * @param sysUser
     */
    @Override
    public void updateUser(SysUser sysUser) {
        String userName = sysUser.getUserName();
        SysUser dbUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbUser != null){
            //TODO 使用userName查询到user后,如果接收到的userId与查询到的一致,代表更新的是自己,可以放行,否则报错
            if (dbUser.getId() != sysUser.getId()){
                throw new UserNameExistsException(205,"用户名已存在,再想一个吧");
            }
        }
        //TODO 密码加密
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        sysUserMapper.update(sysUser);
    }

    /**
     * 用户添加
     *
     * @param sysUser
     */
    @Override
    public void saveUser(SysUser sysUser) {
        String userName = sysUser.getUserName();
        String password = sysUser.getPassword();
        SysUser dbUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbUser != null){
            throw new UserNameExistsException(205,"用户名已存在,再想一个吧");
        }
        //TODO 密码加密
        sysUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        sysUserMapper.save(sysUser);
    }
}
