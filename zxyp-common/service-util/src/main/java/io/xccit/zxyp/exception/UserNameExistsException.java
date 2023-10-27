package io.xccit.zxyp.exception;

import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/10/27
 * @description 用户名已存在异常
 */
@Data
public class UserNameExistsException extends RuntimeException{
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public UserNameExistsException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
    }

    public UserNameExistsException(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
