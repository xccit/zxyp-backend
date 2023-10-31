package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品单位Mapper
 */
@Mapper
public interface ProductUnitMapper {

    /**
     * 商品单位列表
     * @return
     */
    List<ProductUnit> list();
}
