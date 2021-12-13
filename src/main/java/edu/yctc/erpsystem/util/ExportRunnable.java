package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.entity.ResultDO;

/**
 * try-catch代码处理接口
 *
 * @author xiaotao
 */
public interface ExportRunnable {

    /**
     * export接口
     *
     * @return 分页信息
     */
    ResultDO<Void> run();

}
