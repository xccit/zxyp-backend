package io.xccit.zxyp.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.xccit.zxyp.entity.base.BaseEntity;
import lombok.Data;

@Data
@Schema(description = "商品搜索条件实体类")
public class ProductDto extends BaseEntity {

    @Schema(description = "品牌id")
    private Long brandId;

    @Schema(description = "一级分类id")
    private Long category1Id;

    @Schema(description = "二级分类id")
    private Long category2Id;

    @Schema(description = "三级分类id")
    private Long category3Id;

}
