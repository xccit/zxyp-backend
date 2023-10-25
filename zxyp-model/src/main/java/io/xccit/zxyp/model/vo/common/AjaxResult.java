package io.xccit.zxyp.model.vo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "响应结果实体类")
public class AjaxResult<T> {

    //返回码
    @Schema(description = "业务状态码")
    private Integer code;

    //返回消息
    @Schema(description = "响应消息")
    private String message;

    //返回数据
    @Schema(description = "业务数据")
    private T data;

    // 私有化构造
    private AjaxResult() {}

    // 返回数据
    public static <T> AjaxResult<T> build(T body, Integer code, String message) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 通过枚举构造Result对象
    public static <T> AjaxResult<T> build(T body , ResultCodeEnum resultCodeEnum) {
        return build(body , resultCodeEnum.getCode() , resultCodeEnum.getMessage()) ;
    }

}
