package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.DetailedManufactureProcessSlaveRestController;
import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.DetailedManufactureProcessSlaveInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.DetailedManufactureProcessSlaveVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 制造流程单详细
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/detailed-manufacturing-process-sheet")
public class DetailedManufactureProcessSlaveRestControllerImpl implements DetailedManufactureProcessSlaveRestController {

    @Resource
    private DetailedManufactureProcessSlaveInterService detailedManufactureProcessSlaveService;

    @Override
    @GetMapping("getDetailedManufactureProcessSlaves")
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getDetailedManufactureProcessSlaves(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return detailedManufactureProcessSlaveService.getDetailedManufactureProcessSlaves(params);
    }

    @Override
    @GetMapping("getDetailedManufactureProcessSlaveByConditions")
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getDetailedManufactureProcessSlaveByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return detailedManufactureProcessSlaveService.getDetailedManufactureProcessSlaveByConditions(params);
    }

    @Override
    @PostMapping("updateIsIntoMake")
    public ResultDO<Void> updateIsIntoMake(@RequestBody ManufactureProcessSlaveDO manufactureProcessSlaveDO) {
        if (StringUtils.isBlank(manufactureProcessSlaveDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return detailedManufactureProcessSlaveService.updateIsIntoMake(manufactureProcessSlaveDO.getId());
    }

    @Override
    @PostMapping("updateMaterialExport")
    public ResultDO<Void> updateMaterialExport(@RequestBody DetailedManufactureProcessSlaveVO manufactureProcessSlaveVO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(manufactureProcessSlaveVO.getId())
                || StringUtils.isBlank(manufactureProcessSlaveVO.getExportNumber()) || StringUtils.isBlank(manufactureProcessSlaveVO.getExportDate().toString())
                || StringUtils.isBlank(manufactureProcessSlaveVO.getMaterialInfoMasterId()) || StringUtils.isBlank(manufactureProcessSlaveVO.getMaterialInventoryMasterId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        manufactureProcessSlaveVO.setUserId( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return detailedManufactureProcessSlaveService.updateMaterialExport(manufactureProcessSlaveVO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody DetailedManufactureProcessSlaveVO[] detailedManufactureProcessSlaveVO) {
        if (null == detailedManufactureProcessSlaveVO || 0 == detailedManufactureProcessSlaveVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        return detailedManufactureProcessSlaveService.exportExcel(detailedManufactureProcessSlaveVO);
    }

    @Override
    @PostMapping("exportExcelDianZu")
    public ResultDO<Void> exportExcelDianZu(@RequestBody ManufactureProcessSlaveDO manufactureProcessSlaveDO) {
        if (StringUtils.isBlank(manufactureProcessSlaveDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return detailedManufactureProcessSlaveService.exportExcelDianZu(manufactureProcessSlaveDO);
    }

    @Override
    @PostMapping("exportExcelTangHuang")
    public ResultDO<Void> exportExcelTangHuang(@RequestBody ManufactureProcessSlaveDO manufactureProcessSlaveDO) {
        if (StringUtils.isBlank(manufactureProcessSlaveDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return detailedManufactureProcessSlaveService.exportExcelTangHuang(manufactureProcessSlaveDO);
    }
}
