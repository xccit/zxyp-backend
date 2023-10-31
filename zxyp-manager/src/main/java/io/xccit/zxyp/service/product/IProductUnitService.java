package io.xccit.zxyp.service.product;

import io.xccit.zxyp.model.entity.base.ProductUnit;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description 商品单位业务层
 */
public interface IProductUnitService {

    /**
     * 商品单位列表
     * @return
     */
    List<ProductUnit> list();
}
