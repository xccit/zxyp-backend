package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.product.Brand;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description
 */
public interface IBrandService {

    /**
     * 查询全部品牌
     * @return
     */
    List<Brand> list();
}
