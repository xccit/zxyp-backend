package io.xccit.zxyp.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.mapper.product.ProductSpecMapper;
import io.xccit.zxyp.model.entity.product.ProductSpec;
import io.xccit.zxyp.service.product.IProductSpecService;
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
public class ProductSpecServiceImpl implements IProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    /**
     * 商品规格分页列表
     *
     * @return
     */
    @Override
    public PageInfo<ProductSpec> listSpecPage(Integer current,Integer pageSize) {
        PageHelper.startPage(current,pageSize);
        List<ProductSpec> productSpecList = productSpecMapper.list();
        PageInfo<ProductSpec> pageInfo = new PageInfo<>(productSpecList);
        return pageInfo;
    }

    /**
     * 商品规格删除/批量删除
     *
     * @param ids
     */
    @Override
    public void remove(List<Long> ids) {
        productSpecMapper.remove(ids);
    }

    /**
     * 商品规格修改
     *
     * @param productSpec
     */
    @Override
    public void update(ProductSpec productSpec) {
        productSpecMapper.update(productSpec);
    }

    /**
     * 商品规格添加
     *
     * @param productSpec
     */
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }
}
