package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.entity.ResultDO;

/**
 * try-catch代码处理接口
 *
 * @author lqp
 */
public interface GetRunnable<T> {

    /**
     * get接口
     *
     * @return 分页信息
     */
    ResultDO<PageUtils<T>> run();

}
