package io.xccit.zxyp.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.xccit.zxyp.entity.base.BaseEntity;
import lombok.Data;

@Data
@Schema(description = "品牌实体类")
public class Brand extends BaseEntity {

	@Schema(description = "品牌名称")
	private String name;

	@Schema(description = "品牌logo")
	private String logo;

}