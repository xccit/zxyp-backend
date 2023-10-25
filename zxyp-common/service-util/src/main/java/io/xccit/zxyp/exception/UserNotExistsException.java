package io.xccit.zxyp.exception;

import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/10/13
 * @description 用户不存在异常
 */
@Data
public class UserNotExistsException extends RuntimeException{

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public UserNotExistsException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public UserNotExistsException(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
