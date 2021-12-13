package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.SupplierRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.SupplierDO;
import edu.yctc.erpsystem.service.SupplierInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 供应商数据接口实现
 *
 * @author lqp
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/supplier-management")
public class SupplierRestControllerImpl implements SupplierRestController {

    @Resource
    private SupplierInterService supplierService;

    @Override
    @GetMapping("getSuppliers")
    public ResultDO<PageUtils<SupplierDO>> getSuppliers(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return supplierService.getSuppliers(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody SupplierDO supplierDO) {
        if (StringUtils.isBlank(supplierDO.getCode()) || StringUtils.isBlank(supplierDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return supplierService.insert(supplierDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody SupplierDO supplierDO) {
        if (StringUtils.isBlank(supplierDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return supplierService.delete(supplierDO.getId());
    }

    @Override
    @PostMapping("updateSupplier")
    public ResultDO<Void> updateSupplier(@RequestBody SupplierDO supplierDO) {
        if (StringUtils.isBlank(supplierDO.getCode()) || StringUtils.isBlank(supplierDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return supplierService.updateSupplier(supplierDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody SupplierDO[] supplierDO) {
        if (null == supplierDO || 0 == supplierDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return supplierService.exportExcel(supplierDO);
    }

}
