package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.DbBackUpDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 数据库备份数据接口
 *
 * @author lqp
 */
public interface DbBackUpRestController {

    /**
     * 获得数据库备份记录
     *
     * @param params 分页信息
     * @return 数据库备份链表
     */
    ResultDO<PageUtils<DbBackUpDO>> getDbBackUps(@RequestParam Map<String, Object> params);

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
     * @return 是否成功(文件保存路径)
     */
    ResultDO<String> insert();

}
