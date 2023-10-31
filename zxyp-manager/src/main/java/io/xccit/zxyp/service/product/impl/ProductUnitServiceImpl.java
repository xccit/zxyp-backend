package io.xccit.zxyp.service.product.impl;

import io.xccit.zxyp.mapper.product.ProductUnitMapper;
import io.xccit.zxyp.model.entity.base.ProductUnit;
import io.xccit.zxyp.service.product.IProductUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description
 */
@Slf4j
@Service
public class ProductUnitServiceImpl implements IProductUnitService {

    @Autowired
    private ProductUnitMapper productUnitMapper;

    /**
     * 商品单位列表
     *
     * @return
     */
    @Override
    public List<ProductUnit> list() {
        return productUnitMapper.list();
    }
}
