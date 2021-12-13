package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.DbBackUpDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 数据库备份逻辑接口
 *
 * @author lqp
 */
public interface DbBackUpInterService {

    /**
     * 获得数据库备份记录
     *
     * @param params 过滤参数
     * @return 数据库备份链表
     */
    ResultDO<PageUtils<DbBackUpDO>> getDbBackUps(Map<String, Object> params);

    /**
     * 根据数据库文件名删除若干记录
     *
     * @param fileNames 数据库文件名链表
     * @return 是否成功
     */
    ResultDO<Void> deleteByFileNames(List<String> fileNames);

    /**
     * 插入一条记录
     *
     * @return 是否成功
     */
    ResultDO<String> insert();

}
