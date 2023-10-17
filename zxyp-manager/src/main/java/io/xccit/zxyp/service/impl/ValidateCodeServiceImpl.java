package io.xccit.zxyp.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import io.xccit.zxyp.constants.RedisPrefixConstant;
import io.xccit.zxyp.service.ValidateCodeService;
import io.xccit.zxyp.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author CH_ywx
 * @date 2023/10/15
 * @description 验证码服务
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 生成图片验证码
     *
     * @return
     */
    @Override
    public ValidateCodeVo generateValidateCode() {
        //TODO 生成验证码  宽 高 位数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 4);
        String codeValue = circleCaptcha.getCode(); //验证码
        String imageBase64 = circleCaptcha.getImageBase64(); //Base64加密后的图片流文件
        String codeKey = UUID.randomUUID().toString().replaceAll("-", "");
        //TODO 存储进Redis,1分钟有效
        redisTemplate.opsForValue().set(
                RedisPrefixConstant.VALIDATE_CODE_KEY + codeKey,
                codeValue,
                1,
                TimeUnit.MINUTES
        );
        //封装VO
        ValidateCodeVo validateCodeVo = new ValidateCodeVo(codeKey, "data:image/png;BASE64," + imageBase64);
        return validateCodeVo;
    }
}
