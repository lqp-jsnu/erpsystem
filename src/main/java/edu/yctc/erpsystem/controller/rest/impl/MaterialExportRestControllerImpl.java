package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialExportRestController;
import edu.yctc.erpsystem.entity.MaterialExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialExportInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 材料出库信息数据接口实现
 *
 * @author wjd
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-outgoing-information")
public class MaterialExportRestControllerImpl implements MaterialExportRestController {

    @Resource
    private MaterialExportInterService materialExportService;

    @Override
    @GetMapping("getMaterialExport")
    public ResultDO<PageUtils<MaterialExportVO>> getMaterialExport(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportService.getMaterialExport(params);
    }

    @Override
    @GetMapping("getMaterialExportByConditions")
    public ResultDO<PageUtils<MaterialExportVO>> getMaterialExportByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportService.getMaterialExportByConditions(params);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialExportDO materialExportDO) {
        if (StringUtils.isBlank(materialExportDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportService.delete(materialExportDO.getId());
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody MaterialExportDO materialExportDO, HttpSession httpSession) {
        if (StringUtils.isBlank(materialExportDO.getId()) || StringUtils.isBlank(materialExportDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialExportDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialExportService.updateCheckerById(materialExportDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialExportVO[] materialExportVO) {
        if (null == materialExportVO || 0 == materialExportVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportService.exportExcel(materialExportVO);
    }

    @Override
    @PostMapping("exportDetailExcel")
    public ResultDO<Void> exportDetailExcel(@RequestBody List<MaterialExportVO> materialExportVO) {
        if (null == materialExportVO || 0 == materialExportVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportService.exportDetailExcel(materialExportVO);
    }

}


