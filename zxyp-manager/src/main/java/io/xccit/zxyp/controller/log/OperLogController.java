package io.xccit.zxyp.controller.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.mapper.log.OperLogMapper;
import io.xccit.zxyp.model.entity.system.OperLog;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description
 */
@Tag(name = "操作日志管理")
@RestController
@RequestMapping("/admin/log/oper")
public class OperLogController {

    @Autowired
    OperLogMapper operLogMapper;

    /**
     * 分页操作日志记录
     * @return
     */
    @Operation(summary = "分页操作日志记录")
    @GetMapping("/{current}/{pageSize}")
    public AjaxResult list(@PathVariable Integer current,
                           @PathVariable Integer pageSize){
        PageHelper.startPage(current,pageSize);
        List<OperLog> operLogList = operLogMapper.list();
        PageInfo<OperLog> operLogPageInfo = new PageInfo<>(operLogList);
        return AjaxResult.build(operLogPageInfo, ResultCodeEnum.SUCCESS);
    }
}
