package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ManufactureProcessMasterRestController;
import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ManufactureProcessMasterInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManufactureProcessMasterVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 制造流程单数据接口实现
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/manufacturing-process-sheet")
public class ManufactureProcessMasterRestControllerImpl implements ManufactureProcessMasterRestController {

    @Resource
    private ManufactureProcessMasterInterService manufactureProcessMasterService;

    @Override
    @GetMapping("getManufactureProcessMaster")
    public ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMaster(@RequestParam Map<String, Object> params){
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessMasterService.getManufactureProcessMaster(params);
    }

    @Override
    @GetMapping("getManufactureProcessMasterByConditions")
    public ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMasterByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessMasterService.getManufactureProcessMasterByConditions(params);
    }

    @Override
    @PostMapping("exportAllDianZuExcel")
    public ResultDO<Void> exportAllDianZuExcel(@RequestBody ManufactureProcessSlaveDO[] manufactureProcessSlaveDO) {
        if (null == manufactureProcessSlaveDO || 0 == manufactureProcessSlaveDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessMasterService.exportAllDianZuExcel(manufactureProcessSlaveDO);
    }

    @Override
    @PostMapping("exportAllTanHuangExcel")
    public ResultDO<Void> exportAllTanHuangExcel(@RequestBody ManufactureProcessSlaveDO[] manufactureProcessSlaveDO) {
        if (null == manufactureProcessSlaveDO || 0 == manufactureProcessSlaveDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessMasterService.exportAllTanHuangExcel(manufactureProcessSlaveDO);
    }

}
