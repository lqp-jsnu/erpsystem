package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ScheduleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 排程表
 *
 * @author smile
 */
@Mapper
public interface ScheduleDAO {

    /**
     * 获得排程表的数量
     *
     * @param params 过滤参数
     * @return 排程表数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有排程表信息
     *
     * @param params 过滤参数
     * @return 排程表信息链表
     */
    List<ScheduleDO> getSchedule(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param scheduleDO 实体
     */
    void insert(ScheduleDO scheduleDO);

    /**
     * 通过id修改qie han rao tu chai
     *
     * @param scheduleDO 未完结排程
     */
    void updateSchedule(ScheduleDO scheduleDO);

    /**
     * 通过id修改isFinish
     *
     * @param id 未完结排程实体
     */
    void updateIsFinish(@Param("id") String id);

    /**
     * 通过主键获得排程
     *
     * @param id 主键
     * @return 排程
     */
    ScheduleDO getScheduleById(@Param("id") String id);

    /**
     * 通过主键获得所有排程
     *
     * @param ids 主键表
     * @return 排程表
     */
    List<ScheduleDO> getScheduleByIds(@Param("ids") List<String> ids);

}
