package io.xccit.zxyp.service.log;

import io.xccit.zxyp.mapper.log.OperLogMapper;
import io.xccit.zxyp.model.entity.system.OperLog;
import io.xccit.zxyp.service.IOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description
 */
@Service
public class OperLogServiceImpl implements IOperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    /**
     * 保存日志,异步操作
     *
     * @param operLog
     */
    @Async
    @Override
    public void saveOperLog(OperLog operLog) {
        operLogMapper.save(operLog);
    }

    /**
     * 分页操作日志记录
     *
     * @return
     */
    @Override
    public List<OperLog> listOperPage() {
        return operLogMapper.list();
    }
}
