package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ScheduleRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ScheduleDO;
import edu.yctc.erpsystem.service.ScheduleInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ScheduleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 排程表
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/open-schedule")
public class ScheduleRestControllerImpl implements ScheduleRestController {

    @Resource
    private ScheduleInterService scheduleService;

    @Override
    @GetMapping("getSchedule")
    public ResultDO<PageUtils<ScheduleVO>> getSchedule(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return scheduleService.getSchedule(params);
    }

    @Override
    @GetMapping("getScheduleByConditions")
    public ResultDO<PageUtils<ScheduleVO>> getScheduleByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return scheduleService.getScheduleByConditions(params);
    }

    @Override
    @PostMapping("updateSchedule")
    public ResultDO<Void> updateOpenSchedule(@RequestBody ScheduleDO scheduleDO) {
        if (StringUtils.isBlank(scheduleDO.getId()) || StringUtils.isBlank(scheduleDO.getQie()) || StringUtils.isBlank(scheduleDO.getHan())
                || StringUtils.isBlank(scheduleDO.getRao())|| StringUtils.isBlank(scheduleDO.getTu()) || StringUtils.isBlank(scheduleDO.getChai())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return scheduleService.updateSchedule(scheduleDO);
    }

    @Override
    @PostMapping("updateIsFinish")
    public ResultDO<Void> updateIsFinish(@RequestBody ScheduleDO scheduleDO) {
        if (StringUtils.isBlank(scheduleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return scheduleService.updateIsFinish(scheduleDO.getId());
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ScheduleVO[] scheduleVO) {
        if (null == scheduleVO || 0 == scheduleVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return scheduleService.exportExcel(scheduleVO);
    }

}
