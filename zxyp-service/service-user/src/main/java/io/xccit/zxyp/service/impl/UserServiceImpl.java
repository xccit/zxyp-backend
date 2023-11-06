package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.exception.SmsException;
import io.xccit.zxyp.exception.UserNotExistsException;
import io.xccit.zxyp.mapper.UserInfoMapper;
import io.xccit.zxyp.model.dto.h5.UserRegisterDto;
import io.xccit.zxyp.model.entity.user.UserInfo;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 前台用户业务层
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户注册
     *
     * @param userRegisterDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //TODO 获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //TODO 校验参数
        if (StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(code)) {
            throw new UserNotExistsException(ResultCodeEnum.DATA_ERROR);
        }

        //TODO 校验验证码
        String codeValueRedis = redisTemplate.opsForValue().get(username);
        log.info("Redis中的验证码:"+codeValueRedis);
        if (!code.equals(codeValueRedis)) {
            throw new SmsException(ResultCodeEnum.SMS_CODE_ERROR);
        }

        //TODO 根据用户名获取用户信息
        UserInfo userInfo = userInfoMapper.getUserByUserName(username);
        if (null != userInfo) {
            throw new UserNotExistsException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //TODO 保存用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://127.0.0.1:9000/zxyp-bucket/20231028/6fa3d25b3d084b738c1ec1707e356b5577ef70302ce3538cda31547b7f5cd8d7.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=UM9Z2OXLCIKD7RFBK7WW%2F20231106%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20231106T053332Z&X-Amz-Expires=604800&X-Amz-Security-Token=eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhY2Nlc3NLZXkiOiJVTTlaMk9YTENJS0Q3UkZCSzdXVyIsImV4cCI6MTY5OTI5MTkyOCwicGFyZW50IjoibWluaW9hZG1pbiJ9.faoTQQZy-f9Cyi1U2tZqkH088trWccul0NS7Y6IcGeAvho8ScMeYMxzbNvhfe6MriXZJetysyMpCE3YEekEOYg&X-Amz-SignedHeaders=host&versionId=null&X-Amz-Signature=1734021b45cf3fb6a5101b9b72603507835c3216810c11fb5fecce479e76f841");
        userInfoMapper.save(userInfo);
        //TODO 删除Redis中的数据
        redisTemplate.delete(username);
    }
}
