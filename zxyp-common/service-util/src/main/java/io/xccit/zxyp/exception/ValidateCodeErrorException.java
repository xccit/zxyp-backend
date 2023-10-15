package io.xccit.zxyp.exception;

import io.xccit.zxyp.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/10/15
 * @description 验证码错误异常
 */
@Data
public class ValidateCodeErrorException extends RuntimeException {
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public ValidateCodeErrorException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public ValidateCodeErrorException(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
