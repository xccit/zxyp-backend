package io.xccit.zxyp.exception.handler;

import io.xccit.zxyp.exception.PasswordWrongException;
import io.xccit.zxyp.exception.UserNotExistsException;
import io.xccit.zxyp.vo.common.AjaxResult;
import io.xccit.zxyp.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CH_ywx
 * @date 2023/10/13
 * @description 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理全局异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxResult error(Exception e) {
        return AjaxResult.build(null, 500, e.getCause().getMessage());
    }

    /**
     * 密码错误捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(PasswordWrongException.class)
    public AjaxResult passwordWrong(PasswordWrongException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 用户不存在捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UserNotExistsException.class)
    public AjaxResult userNotExists(UserNotExistsException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }
}