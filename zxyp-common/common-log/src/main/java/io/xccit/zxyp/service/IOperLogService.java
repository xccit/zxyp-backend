package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.system.OperLog;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 日志操作Service
 */
public interface IOperLogService {
    /**
     * 保存日志
     * @param operLog
     */
    void saveOperLog(OperLog operLog);

    /**
     * 分页操作日志记录
     * @return
     */
    List<OperLog> listOperPage();
}
