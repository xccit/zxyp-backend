package io.xccit.zxyp.model.vo.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 基础地址VO
 */
@Schema(name = "基础地址接收")
@Data
public class BaseAddressVO {

    @Schema(name = "省")
    private String province;
    @Schema(name = "市")
    private String city;
    @Schema(name = "县")
    private String district;
}
