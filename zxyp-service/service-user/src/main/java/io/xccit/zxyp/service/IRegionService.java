package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.base.Region;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description
 */
public interface IRegionService {

    /**
     * 根据上级地区ID获取地区信息
     * @param parentCode
     * @return
     */
    List<Region> getRegionByParent(Long parentCode);
}
