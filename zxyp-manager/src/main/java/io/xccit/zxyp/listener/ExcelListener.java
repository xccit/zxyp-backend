package io.xccit.zxyp.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import io.xccit.zxyp.mapper.product.CategoryMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description EasyExcel读操作监听器
 * 不可以交给Spring管理,详情查看:https://easyexcel.opensource.alibaba.com/qa/read
 */
@Slf4j
public class ExcelListener<T> implements ReadListener<T> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private CategoryMapper categoryMapper;
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param categoryMapper
     */
    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 每分析一行执行此方法
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context analysis context
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        //TODO 把每行数据都放在缓存区中
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止缓存几万条数据在内存，容易OOM(内存溢出)
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有操作完毕之后执行此方法
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }


    /**
     * 保存数据
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        categoryMapper.batchSave(cachedDataList);
        log.info("存储数据库成功！");
    }
}
