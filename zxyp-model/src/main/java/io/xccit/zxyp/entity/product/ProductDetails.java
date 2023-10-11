package io.xccit.zxyp.entity.product;

import io.xccit.zxyp.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}