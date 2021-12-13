package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.bo.MaterialExportBO;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialInventoryMasterRestController;
import edu.yctc.erpsystem.entity.MaterialExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialInventoryMasterInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInventoryMasterVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 材料库存数据接口实现
 *
 * @author wjd
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-repertory")
public class MaterialInventoryMasterRestControllerImpl implements MaterialInventoryMasterRestController {

    @Resource
    private MaterialInventoryMasterInterService materialInventoryMasterService;

    @Override
    @GetMapping("getMaterialRepertory")
    public ResultDO<PageUtils<MaterialInventoryMasterVO>> getMaterialRepertory(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryMasterService.getMaterialRepertory(params);
    }

    @Override
    @GetMapping("getMaterialRepertoryByConditions")
    public ResultDO<PageUtils<MaterialInventoryMasterVO>> getMaterialRepertoryByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryMasterService.getMaterialRepertoryByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialExportBO materialExportParam, HttpSession httpSession) {
        MaterialExportDO materialExportDO = materialExportParam.getMaterialExportDO();
        List<String> materialInventoryIds = materialExportParam.getMaterialInventoryIds();
        if (null == materialExportDO || null == materialInventoryIds || StringUtils.isBlank(materialExportDO.getDate().toString()) || StringUtils.isBlank(materialExportDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(materialExportDO.getMaterialInventoryMasterId()) || StringUtils.isBlank(materialExportDO.getNumber())
                || StringUtils.isBlank(materialExportDO.getIssueNumber()) || 0 == materialInventoryIds.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialExportDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialInventoryMasterService.insert(materialExportParam);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialInventoryMasterVO[] materialInventoryMaster) {
        if (null == materialInventoryMaster || 0 == materialInventoryMaster.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryMasterService.exportExcel(materialInventoryMaster);
    }

    @Override
    @PostMapping("exportDetailExcel")
    public ResultDO<Void> exportDetailExcel(@RequestBody List<MaterialInventoryMasterVO> materialInventoryMasterVO) {
        if (null == materialInventoryMasterVO || 0 == materialInventoryMasterVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryMasterService.exportDetailExcel(materialInventoryMasterVO);
    }

}
