package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 前台用户验证码收发接口
 */
@Tag(name="前台用户验证码发送接口")
@RestController
@RequestMapping("/api/user/sms")
public class SmsController {

    @Autowired
    private ISmsService smsService;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @Operation(summary = "发送验证码")
    @GetMapping("/sendCode/{phone}")
    public AjaxResult sendSms(@PathVariable String phone){
        smsService.sendSms(phone);
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }
}
