package io.xccit.zxyp.service.product.impl;

import io.xccit.zxyp.mapper.product.BrandMapper;
import io.xccit.zxyp.service.product.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description
 */
@Slf4j
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;
}
