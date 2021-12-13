package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.CompletedScheduleRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ScheduleDO;
import edu.yctc.erpsystem.service.CompletedScheduleInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ScheduleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 已完成排程表
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/completed-schedule")
public class CompletedScheduleRestControllerImpl implements CompletedScheduleRestController {
    @Resource
    private CompletedScheduleInterService completedScheduleService;

    @Override
    @GetMapping("getCompletedSchedule")
    public ResultDO<PageUtils<ScheduleVO>> getCompletedSchedule(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return completedScheduleService.getCompletedSchedule(params);
    }

    @Override
    @GetMapping("getCompletedScheduleByConditions")
    public ResultDO<PageUtils<ScheduleVO>> getCompletedScheduleByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return completedScheduleService.getCompletedScheduleByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ScheduleVO[] scheduleVO) {
        if (null == scheduleVO || 0 == scheduleVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return completedScheduleService.exportExcel(scheduleVO);
    }

    @Override
    @PostMapping("exportExcelDianZu")
    public ResultDO<Void> exportExcelDianZu(@RequestBody ScheduleDO scheduleDO) {
        if (StringUtils.isBlank(scheduleDO.getMaterialExportId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return completedScheduleService.exportExcelDianZu(scheduleDO);
    }

    @Override
    @PostMapping("exportExcelTangHuang")
    public ResultDO<Void> exportExcelTangHuang(@RequestBody ScheduleDO scheduleDO) {
        if (StringUtils.isBlank(scheduleDO.getMaterialExportId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return completedScheduleService.exportExcelTangHuang(scheduleDO);
    }

}
