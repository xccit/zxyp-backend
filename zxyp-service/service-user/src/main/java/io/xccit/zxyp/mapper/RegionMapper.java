package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.base.Region;
import io.xccit.zxyp.model.vo.h5.BaseAddressVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 地区Mapper
 */
@Mapper
public interface RegionMapper {

    /**
     * 根据上级地区ID获取地区信息
     * @param parentCode
     * @return
     */
    List<Region> getRegionByParentCode(Long parentCode);

    /**
     * 查询基础地址
     * @param provinceCode
     * @param cityCode
     * @param districtCode
     * @return
     */
    BaseAddressVO getFullAddress(String provinceCode, String cityCode, String districtCode);
}
