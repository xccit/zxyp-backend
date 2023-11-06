package io.xccit.zxyp.service;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description
 */
public interface ISmsService {

    /**
     * 发送验证码
     * @param phone
     */
    void sendSms(String phone);
}
