package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.WarehouseRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.WarehouseDO;
import edu.yctc.erpsystem.service.WarehouseInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.WarehouseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 仓库管理接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/warehouse-management")
public class WarehouseRestControllerImpl implements WarehouseRestController {

    @Resource
    private WarehouseInterService warehouseService;

    @Override
    @GetMapping("getWarehouse")
    public ResultDO<PageUtils<WarehouseVO>> getWarehouse(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return warehouseService.getWarehouse(params);
    }

    @Override
    @GetMapping("getWarehouseByConditions")
    public ResultDO<PageUtils<WarehouseVO>> getWarehouseByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return warehouseService.getWarehouseByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody WarehouseDO warehouseDO) {
        if (StringUtils.isBlank(warehouseDO.getName()) || StringUtils.isBlank(warehouseDO.getType()) || StringUtils.isBlank(warehouseDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return warehouseService.insert(warehouseDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody WarehouseDO warehouseDO) {
        if (StringUtils.isBlank(warehouseDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return warehouseService.delete(warehouseDO.getId());
    }

    @Override
    @PostMapping("updateWarehouse")
    public ResultDO<Void> updateWarehouse(@RequestBody WarehouseDO warehouseDO) {
        if (StringUtils.isBlank(warehouseDO.getName()) || StringUtils.isBlank(warehouseDO.getType()) || StringUtils.isBlank(warehouseDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return warehouseService.updateWarehouse(warehouseDO);
    }

}
