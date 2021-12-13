package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.FlowSheetPrintedRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.FlowSheetPrintedInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.DetailedManufactureProcessSlaveVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 已列印流程单表
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/the-flow-sheet-has-been-printed")
public class FlowSheetPrintedRestControllerImpl implements FlowSheetPrintedRestController{

    @Resource
    private FlowSheetPrintedInterService flowSheetPrintedService;

    @Override
    @GetMapping("getFlowSheetPrinted")
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getFlowSheetPrinted(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return flowSheetPrintedService.getFlowSheetPrinted(params);
    }

    @Override
    @GetMapping("getFlowSheetPrintedByConditions")
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getFlowSheetPrintedByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return flowSheetPrintedService.getFlowSheetPrintedByConditions(params);
    }

}
