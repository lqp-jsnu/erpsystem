package edu.yctc.erpsystem.controller.rest.impl;


import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ManualExportRestController;
import edu.yctc.erpsystem.entity.ManualExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ManualExportInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManualExportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 手动出库表
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/manual-outbound")
public class ManualExportRestControllerImpl implements ManualExportRestController {

    @Resource
    private ManualExportInterService manualExportService;

    @Override
    @GetMapping("getManualExport")
    public ResultDO<PageUtils<ManualExportVO>> getManualExport(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manualExportService.getManualExport(params);
    }

    @Override
    @GetMapping("getManualExportByConditions")
    public ResultDO<PageUtils<ManualExportVO>> getManualExportByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manualExportService.getManualExportByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ManualExportDO manualExportDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(manualExportDO.getMaterialInventoryId())
                || StringUtils.isBlank(manualExportDO.getDate().toString()) || StringUtils.isBlank(manualExportDO.getAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        manualExportDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return manualExportService.insert(manualExportDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody ManualExportDO manualExport) {
        if (StringUtils.isBlank(manualExport.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manualExportService.delete(manualExport.getId());
    }

    @Override
    @PostMapping("updateManualExport")
    public ResultDO<Void> updateManualExport(@RequestBody ManualExportDO manualExportDO) {
        if (StringUtils.isBlank(manualExportDO.getId()) || StringUtils.isBlank(manualExportDO.getAmount())
                || StringUtils.isBlank(manualExportDO.getDate().toString()) || StringUtils.isBlank(manualExportDO.getRemark())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manualExportService.updateManualExport(manualExportDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody ManualExportDO manualExportDO, HttpSession httpSession) {
        if (StringUtils.isBlank(manualExportDO.getId()) || StringUtils.isBlank(manualExportDO.getCheckFlag()) || StringUtils.isBlank(manualExportDO.getMaterialInventoryId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        manualExportDO.setChecker( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return manualExportService.updateCheckerById(manualExportDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ManualExportVO[] manualExports) {
        if (null == manualExports || 0 == manualExports.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manualExportService.exportExcel(manualExports);
    }

}

