package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.RegionMapper;
import io.xccit.zxyp.model.entity.base.Region;
import io.xccit.zxyp.service.IRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 地区业务层
 */
@Slf4j
@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper regionMapper;

    /**
     * 根据上级地区ID获取地区信息
     *
     * @param parentCode
     * @return
     */
    @Override
    public List<Region> getRegionByParent(Long parentCode) {
        return regionMapper.getRegionByParentCode(parentCode);
    }
}
