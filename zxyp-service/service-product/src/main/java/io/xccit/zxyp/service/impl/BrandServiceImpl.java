package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.BrandMapper;
import io.xccit.zxyp.model.entity.product.Brand;
import io.xccit.zxyp.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 品牌业务层
 */
@Service
@Slf4j
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;
    /**
     * 查询全部品牌
     *
     * @return
     */
    @Cacheable(value = "brand",key = "'list'")
    @Override
    public List<Brand> list() {
        return brandMapper.list();
    }
}
