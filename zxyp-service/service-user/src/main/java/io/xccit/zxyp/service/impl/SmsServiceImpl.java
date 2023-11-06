package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.service.ISmsService;
import io.xccit.zxyp.util.SendSmsCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 短信验证码业务层
 */
@Slf4j
@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 发送验证码
     *
     * @param phone
     */
    @Override
    public void sendSms(String phone) {
        /**
         * 验证码未过期,直接返回
         */
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.hasText(code)) {
            return;
        }
        /**
         * 验证码过期或不存在,重新发送
         */
        String validateCode = RandomStringUtils.randomNumeric(4);      // 生成验证码,5分钟有效
        redisTemplate.opsForValue().set(phone , validateCode , 5, TimeUnit.MINUTES);
        SendSmsCodeUtil.sendCode(phone,validateCode);
    }
}
