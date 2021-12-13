package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialInfoRestController;
import edu.yctc.erpsystem.entity.MaterialInfoDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialInfoInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 料品原始信息接口实现
 *
 * @author xcg
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/raw-material-information")
public class MaterialInfoRestControllerImpl implements MaterialInfoRestController {

    /** 搜索字段 */
    private static final String SUPPLIER_ID = "supplierId";
    private static final String SPEC = "spec";

    @Resource
    private MaterialInfoInterService materialInfoService;

    @Override
    @GetMapping("getMaterialInfo")
    public ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfo(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoService.getMaterialInfo(params);
    }

    @Override
    @GetMapping("getMaterialInfoByConditions")
    public ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoService.getMaterialInfoByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialInfoDO materialInfoDO) {
       if (StringUtils.isBlank(materialInfoDO.getSupplierId()) || StringUtils.isBlank(materialInfoDO.getUnitPrice()) || StringUtils.isBlank(materialInfoDO.getMaterialInfoMasterId())) {
           return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
       }

       return materialInfoService.insert(materialInfoDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialInfoDO materialInfoDO) {
        if (StringUtils.isBlank(materialInfoDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoService.delete(materialInfoDO.getId());
    }

    @Override
    @PostMapping("updateMaterialInfo")
    public ResultDO<Void> updateMaterialInfo(@RequestBody MaterialInfoDO materialInfoDO) {
        if (StringUtils.isBlank(materialInfoDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialInfoDO.getSupplierId())
                || StringUtils.isBlank(materialInfoDO.getUnitPrice()) || StringUtils.isBlank(materialInfoDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoService.updateMaterialInfo(materialInfoDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody MaterialInfoDO materialInfoDO, HttpSession httpSession) {
        if (StringUtils.isBlank(materialInfoDO.getId()) || StringUtils.isBlank(materialInfoDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialInfoDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialInfoService.updateCheckerById(materialInfoDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialInfoVO[] materialInfoVO) {
        if (null == materialInfoVO || 0 == materialInfoVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoService.exportExcel(materialInfoVO);
    }

    @Override
    @GetMapping("getMaterialInfoBySearchSuppId")
    public ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoBySearchSuppId(@RequestParam Map<String, Object> params) {
        if (!validation(params) || StringUtils.isBlank((String)params.get(SUPPLIER_ID)) || StringUtils.isBlank((String)params.get(SPEC))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoService.getMaterialInfoBySearchSuppId(params);
    }

}
