package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.entity.ResultDO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * try-catch代码处理
 *
 * @author lqp
 */
public class CallbackUtils {

    private final static Logger logger = LoggerFactory.getLogger("service");

    /**
     * 获取回调函数
     *
     * @param name 函数名
     * @param data 记录实体toString
     * @param runnable 回调函数
     * @return 分页链表
     */
    public static<T> ResultDO<PageUtils<T>> getCallback(String name, String data, GetRunnable<T> runnable) {
        try {
            return runnable.run();
        } catch(Exception e) {
            logger.error(name + " exception, system error, data={}, e={}", data, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    /**
     * 插入回调函数
     *
     * @param name 函数名
     * @param data 记录实体toString
     * @param runnable 回调函数
     * @return 是否成功
     */
    public static ResultDO<Void> insertCallback(String name, String data, Runnable runnable) {
        try {
            runnable.run();
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            SQLIntegrityConstraintViolationException cause = (SQLIntegrityConstraintViolationException) e.getCause();

            // 列不能重复
            if (e.getCause().getClass() == SQLIntegrityConstraintViolationException.class && ConstantHolder.UNIQUE_CONSTRAINT_ERROR_CODE == cause.getErrorCode()) {
                return new ResultDO<>(false, ResultCode.UNIQUE_CONSTRAINT_ERROR, ResultCode.MSG_UNIQUE_CONSTRAINT_ERROR, null);
            }

            logger.error(name + " insert exception, system error, data={}, e={}", data, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    /**
     * 删除回调函数
     *
     * @param name 函数名
     * @param id 记录id
     * @param runnable 回调函数
     * @return 是否成功
     */
    public static ResultDO<Void> deleteCallback(String name, String id, Runnable runnable) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            runnable.run();
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            SQLIntegrityConstraintViolationException cause = (SQLIntegrityConstraintViolationException) e.getCause();

            // 外键约束
            if (e.getCause().getClass() == SQLIntegrityConstraintViolationException.class && ConstantHolder.FOREIGN_KEY_CONSTRAINT_ERROR_CODE == cause.getErrorCode()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResultDO<>(false, ResultCode.FOREIGN_KEY_CONSTRAINT, ResultCode.MSG_FOREIGN_KEY_CONSTRAINT, null);
            }

            logger.error(name + " delete exception, system error, id={}, e={}", id, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    /**
     * 更新回调函数
     *
     * @param name 函数名
     * @param data 记录实体toString
     * @param runnable 回调函数
     * @return 是否成功
     */
    public static ResultDO<Void> updateCallback(String name, String data, Runnable runnable) {
        try {
            runnable.run();
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            SQLIntegrityConstraintViolationException cause = (SQLIntegrityConstraintViolationException) e.getCause();

            // 列不能重复
            if (e.getCause().getClass() == SQLIntegrityConstraintViolationException.class && ConstantHolder.UNIQUE_CONSTRAINT_ERROR_CODE == cause.getErrorCode()) {
                return new ResultDO<>(false, ResultCode.UNIQUE_CONSTRAINT_ERROR, ResultCode.MSG_UNIQUE_CONSTRAINT_ERROR, null);
            }

            logger.error(name + " exception, system error, data={}, e={}", data, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    /**
     * export回调函数
     *
     * @param name 函数名
     * @param data 记录实体toString
     * @param runnable 回调函数
     * @return 是否成功
     */
    public static ResultDO<Void> exportCallback(String name, String data, ExportRunnable runnable) {
        try {
            return runnable.run();
        } catch (Exception e) {
            logger.error(name + " exportExcel exception, system error, data={}, e={}", data, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

}
