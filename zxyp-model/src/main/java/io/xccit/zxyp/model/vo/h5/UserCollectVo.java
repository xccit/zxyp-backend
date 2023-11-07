package io.xccit.zxyp.model.vo.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description
 */
@Schema(name = "用户收藏信息")
@Data
public class UserCollectVo {
    @Schema(name = "SkuId")
    private Long skuId;
    @Schema(name = "商品标题")
    private String skuName;
    @Schema(name = "创建时间")
    private String createTime;
    @Schema(name = "更新时间")
    private String updateTime;
    @Schema(name = "图片")
    private String thumbImg;
    @Schema(name = "售价")
    private Double salePrice;
    @Schema(name = "是否删除 0否 1是")
    private Integer isDeleted;
}
