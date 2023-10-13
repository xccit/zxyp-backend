package io.xccit.zxyp.exception;

import io.xccit.zxyp.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/10/13
 * @description 密码错误异常异常
 */
@Data
public class PasswordWrongException extends RuntimeException{

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public PasswordWrongException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public PasswordWrongException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
