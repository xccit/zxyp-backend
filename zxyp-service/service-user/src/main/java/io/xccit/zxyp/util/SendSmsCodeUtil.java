package io.xccit.zxyp.util;

import io.xccit.zxyp.exception.SmsException;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 短信验证码收发工具
 */
public class SendSmsCodeUtil {

    /**
     * 发送验证码
     *
     * @param phone
     */
    public static void sendCode(String phone, String validateCode) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "fe1092c0a5cb4dc391b624d5ab1f4e5c";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + validateCode);
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SmsException(ResultCodeEnum.SMS_ERROR);
        }
    }
}
