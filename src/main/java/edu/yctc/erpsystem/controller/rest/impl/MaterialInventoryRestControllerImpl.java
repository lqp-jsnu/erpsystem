package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialInventoryRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialInventoryInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainMaterialExpiryDateVO;
import edu.yctc.erpsystem.vo.MainMaterialNumberVO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 材料盘点数据接口实现
 *
 * @author lqp
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-inventory")
public class MaterialInventoryRestControllerImpl implements MaterialInventoryRestController {

    /** 搜索字段 */
    private static final String SUPPLIER_ID = "supplierId";
    private static final String WAREHOUSE_ID = "warehouseId";
    private static final String SPEC = "spec";

    @Resource
    private MaterialInventoryInterService materialInventoryService;

    @Override
    @GetMapping("getMaterialInventory")
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventory(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.getMaterialInventory(params);
    }

    @Override
    @GetMapping("getMaterialInventoryByConditions")
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.getMaterialInventoryByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialInventoryVO[] materialInventories) {
        if (null == materialInventories || 0 == materialInventories.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.exportExcel(materialInventories);
    }

    @Override
    @GetMapping("getMaterialInventoryBySearchSuppId")
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryBySearchSuppId(@RequestParam Map<String, Object> params) {
        if (!validation(params) || StringUtils.isBlank((String)params.get(SUPPLIER_ID)) || StringUtils.isBlank((String)params.get(SPEC))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.getMaterialInventoryBySearchSuppId(params);
    }

    @Override
    @GetMapping("getMainMaterialExpiryDate")
    public ResultDO<PageUtils<MainMaterialExpiryDateVO>> getMainMaterialExpiryDate(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.getMainMaterialExpiryDate(params);
    }

    @Override
    @GetMapping("getMainMaterialNumber")
    public ResultDO<PageUtils<MainMaterialNumberVO>> getMainMaterialNumber(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.getMainMaterialNumber(params);
    }

    @Override
    @GetMapping("getMaterialInventoryBySearchWarehouse")
    public ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryBySearchWarehouse(@RequestParam Map<String, Object> params) {
        if (!validation(params) || StringUtils.isBlank((String)params.get(WAREHOUSE_ID)) || StringUtils.isBlank((String)params.get(SPEC))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInventoryService.getMaterialInventoryBySearchWarehouse(params);
    }

}
