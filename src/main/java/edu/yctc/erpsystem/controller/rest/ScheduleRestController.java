package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ScheduleDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ScheduleVO;

import java.util.Map;

/**
 * 排程表
 *
 * @author smile
 */
public interface ScheduleRestController {

    /**
     * 获得所有未完结排程信息
     *
     * @param params 过滤参数
     * @return 排程表
     */
    ResultDO<PageUtils<ScheduleVO>> getSchedule(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的未完结排程信息
     *
     * @param params 过滤参数
     * @return 排程表
     */
    ResultDO<PageUtils<ScheduleVO>> getScheduleByConditions(Map<String, Object> params);

    /**
     * 通过id修改qie han rao tu chai
     *
     * @param scheduleDO 未完结排程
     * @return 是否成功
     */
    ResultDO<Void> updateOpenSchedule(ScheduleDO scheduleDO);

    /**
     * 通过id修改isFinish
     *
     * @param scheduleDO 未完结排程
     * @return 是否成功
     */
    ResultDO<Void> updateIsFinish(ScheduleDO scheduleDO);

    /**
     * 导出excel
     *
     * @param scheduleVO 未完结排程信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ScheduleVO[] scheduleVO);

}
