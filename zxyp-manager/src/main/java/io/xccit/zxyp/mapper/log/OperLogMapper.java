package io.xccit.zxyp.mapper.log;

import io.xccit.zxyp.model.entity.system.OperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 操作日志Mapper
 */
@Mapper
public interface OperLogMapper {

    /**
     * 保存日志
     * @param operLog
     */
    void save(OperLog operLog);
}
