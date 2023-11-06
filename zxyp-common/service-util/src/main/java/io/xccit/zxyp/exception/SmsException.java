package io.xccit.zxyp.exception;

import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/11/06
 * @description 短信接口操作异常
 */
@Data
public class SmsException extends RuntimeException {

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public SmsException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

    public SmsException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
