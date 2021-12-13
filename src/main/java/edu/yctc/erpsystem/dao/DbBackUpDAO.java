package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.DbBackUpDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据库备份DAO接口
 *
 * @author lqp
 */
@Mapper
public interface DbBackUpDAO {

    /**
     * 获得数据库备份数据的数量
     *
     * @param params 过滤参数
     * @return 数据库备份数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得数据库备份记录
     *
     * @param params 过滤参数
     * @return 数据库备份链表
     */
    List<DbBackUpDO> getDbBackUp(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param dbBackUpDO 数据库备份实体
     */
    void insert(DbBackUpDO dbBackUpDO);

    /**
     * 根据数据库文件名删除若干条记录
     *
     * @param fileNames 数据库文件名链表
     */
    void deleteByFileNames(@Param("fileNames") List<String> fileNames);

}
