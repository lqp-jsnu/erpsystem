package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.CustomerRestController;
import edu.yctc.erpsystem.entity.CustomerDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.CustomerInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 客户信息数据接口实现
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/customer-management")
public class CustomerRestControllerImpl implements CustomerRestController {

    @Resource
    private CustomerInterService customerService;

    @Override
    @GetMapping("getCustomer")
    public ResultDO<PageUtils<CustomerDO>> getCustomer(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerService.getCustomer(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody CustomerDO customerDO) {
        if (StringUtils.isBlank(customerDO.getCode()) || StringUtils.isBlank(customerDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerService.insert(customerDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody CustomerDO customerDO) {
        if (StringUtils.isBlank(customerDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerService.delete(customerDO.getId());
    }

    @Override
    @PostMapping("updateCustomer")
    public ResultDO<Void> updateCustomer(@RequestBody CustomerDO customerDO) {
        if (StringUtils.isBlank(customerDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerService.updateCustomer(customerDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody CustomerDO[] customerDO) {
        if (null == customerDO || 0 == customerDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerService.exportExcel(customerDO);
    }

    @Override
    @PostMapping("importExcel")
    public ResultDO<Void> importExcel() {
        return customerService.importExcel();
    }

}
