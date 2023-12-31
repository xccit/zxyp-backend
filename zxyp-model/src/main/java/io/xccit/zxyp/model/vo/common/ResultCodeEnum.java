package io.xccit.zxyp.model.vo.common;

import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {

    SUCCESS(200 , "操作成功") ,
    ERROR(201 , "操作失败") ,
    LOGIN_SUCCESS(200 , "登录成功") ,
    LOGIN_ERROR(201 , "用户名或密码错误"),
    CODE_VALIDATE_ERROR(202 , "验证码错误") ,
    LOGIN_AUTH(208 , "用户未登录"),
    USER_NAME_IS_EXISTS(209 , "用户名已经存在"),
    SYSTEM_ERROR(9999 , "系统异常,请稍后重试"),
    MENU_ERROR( 217, "该菜单下有子菜单，不可以删除"),
    SMS_ERROR( 218, "短信操作异常,请联系网站管理员"),
    SMS_CODE_ERROR( 219, "验证码错误"),
    DATA_ERROR(204, "数据异常"),
    EXCEL_ERROR(205, "Excel操作异常"),
    ACCOUNT_STOP( 216, "账号已停用"),
    STOCK_LESS( 219, "库存不足");

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
