package io.xccit.zxyp.exception.handler;

import io.xccit.zxyp.exception.*;
import io.xccit.zxyp.model.vo.common.AjaxResult;
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

    /**
     * 验证码不正确捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ValidateCodeErrorException.class)
    public AjaxResult validateError(ValidateCodeErrorException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 用户名已存在捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UserNameExistsException.class)
    public AjaxResult userNameExistsError(UserNameExistsException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 文件上传异常捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(FileUploadException.class)
    public AjaxResult fileUploadError(FileUploadException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 拥有子菜单请求删除异常捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HadChildrenException.class)
    public AjaxResult hadChildrenError(HadChildrenException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }

    /**
     * Excel操作异常捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ExcelOperatorException.class)
    public AjaxResult hadChildrenError(ExcelOperatorException ex) {
        return AjaxResult.build(null, ex.getCode(), ex.getMessage());
    }
}
