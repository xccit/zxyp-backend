package io.xccit.zxyp.exception;

import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/11/06
 * @description 前台用户未登录异常
 */
@Data
public class UserNoAuthException extends RuntimeException {

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public UserNoAuthException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public UserNoAuthException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
