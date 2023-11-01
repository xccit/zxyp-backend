package io.xccit.zxyp.model.entity.order;

import io.swagger.v3.oas.annotations.media.Schema;
import io.xccit.zxyp.model.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "订单统计数据封装")
@Data
public class OrderStatistics extends BaseEntity {

    private Date orderDate;
    private BigDecimal totalAmount;
    private Integer totalNum;
    
}