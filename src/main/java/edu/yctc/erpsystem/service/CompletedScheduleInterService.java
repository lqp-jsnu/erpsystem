package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ScheduleDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ScheduleVO;

import java.util.Map;

/**
 * 已完成排程表
 *
 * @author smile
 */
public interface CompletedScheduleInterService {

    /**
     * 获得所有已完成排程信息
     *
     * @param params 过滤参数
     * @return 排程表
     */
    ResultDO<PageUtils<ScheduleVO>> getCompletedSchedule(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的已完成排程信息
     *
     * @param params 过滤参数
     * @return 排程表
     */
    ResultDO<PageUtils<ScheduleVO>> getCompletedScheduleByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param schedules 已完结排程信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ScheduleVO[] schedules);

    /**
     * 导出电阻模板
     *
     * @param scheduleDO 完结排程信息信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcelDianZu(ScheduleDO scheduleDO);

    /**
     * 导出弹簧模板
     *
     * @param scheduleDO 完结排程信息信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcelTangHuang(ScheduleDO scheduleDO);

}
