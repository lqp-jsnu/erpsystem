package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialPurchaseToBeStorageRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialPurchaseToBeStorageInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialPurchaseToBeStorageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料待入库数据接口实现
 *
 * @author wjd
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/raw-materials-to-be-stored")
public class MaterialPurchaseToBeStorageRestControllerImpl implements MaterialPurchaseToBeStorageRestController {

    /** 搜索字段 */
    private static final String SUPPLIER_ID = "supplierId";
    private static final String SPEC = "spec";

    @Resource
    private MaterialPurchaseToBeStorageInterService materialPurchaseToBeStorageService;

    @Override
    @GetMapping("getMaterialPurchaseToBeStorage")
    public ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorage(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseToBeStorageService.getMaterialPurchaseToBeStorage(params);
    }

    @Override
    @GetMapping("getMaterialPurchaseToBeStorageByConditions")
    public ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorageByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseToBeStorageService.getMaterialPurchaseToBeStorageByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody List<MaterialPurchaseToBeStorageVO> materialPurchaseToBeStorageVO) {
        if (null == materialPurchaseToBeStorageVO || 0 == materialPurchaseToBeStorageVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseToBeStorageService.exportExcel(materialPurchaseToBeStorageVO);
    }

    @Override
    @GetMapping("getMaterialPurchaseToBeStorageBySearchSuppId")
    public ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorageBySearchSuppId(@RequestParam Map<String, Object> params) {
        if (!validation(params) || StringUtils.isBlank((String)params.get(SUPPLIER_ID)) || StringUtils.isBlank((String)params.get(SPEC))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseToBeStorageService.getMaterialPurchaseToBeStorageBySearchSuppId(params);
    }

}
