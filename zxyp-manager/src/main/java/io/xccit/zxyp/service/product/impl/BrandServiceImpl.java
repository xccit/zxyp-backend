package io.xccit.zxyp.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.mapper.product.BrandMapper;
import io.xccit.zxyp.model.entity.product.Brand;
import io.xccit.zxyp.service.product.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 品牌分页列表
     *
     * @param current
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Brand> listBrandPage(Integer current, Integer pageSize) {
        PageHelper.startPage(current,pageSize);
        List<Brand> brandList = brandMapper.list();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    /**
     * 品牌添加
     *
     * @param brand
     */
    @Override
    public void saveBrand(Brand brand) {
        brandMapper.save(brand);
    }

    /**
     * 品牌修改
     *
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        brandMapper.update(brand);
    }

    /**
     * 品牌删除/批量删除
     *
     * @param ids
     */
    @Override
    public void removeBrand(List<Long> ids) {
        brandMapper.remove(ids);
    }
}
