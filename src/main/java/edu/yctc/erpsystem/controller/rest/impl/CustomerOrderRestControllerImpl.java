package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.CustomerOrderRestController;
import edu.yctc.erpsystem.entity.CustomerOrderDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.CustomerOrderInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 客户订单
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/customer-order")
public class CustomerOrderRestControllerImpl implements CustomerOrderRestController {

    @Resource
    private CustomerOrderInterService customerOrderService;

    @Override
    @GetMapping("getCustomerOrder")
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrder(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderService.getCustomerOrder(params);
    }

    @Override
    @GetMapping("getCustomerOrderByConditions")
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderService.getCustomerOrderByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody CustomerOrderDO customerOrderDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(customerOrderDO.getOriginalProductId())
                || StringUtils.isBlank(customerOrderDO.getCustomerId()) || StringUtils.isBlank(customerOrderDO.getOrderAmount())
                || StringUtils.isBlank(customerOrderDO.getOrderNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        customerOrderDO.setUser( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return customerOrderService.insert(customerOrderDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderService.delete(customerOrderDO.getId());
    }

    @Override
    @PostMapping("updateCustomerOrder")
    public ResultDO<Void> updateCustomerOrder(@RequestBody CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderService.updateCustomerOrder(customerOrderDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody CustomerOrderDO customerOrderDO, HttpSession httpSession) {
        if (StringUtils.isBlank(customerOrderDO.getId()) || StringUtils.isBlank(customerOrderDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        customerOrderDO.setChecker( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return customerOrderService.updateCheckerById(customerOrderDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody CustomerOrderVO[] customerOrderVO) {
        if (null == customerOrderVO || 0 == customerOrderVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderService.exportExcel(customerOrderVO);
    }

    @Override
    @PostMapping("importExcel")
    public ResultDO<Void> importExcel(HttpSession httpSession) {
        CustomerOrderDO customerOrderUserId = new CustomerOrderDO();
        customerOrderUserId.setUser( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());

        return customerOrderService.importExcel(customerOrderUserId);
    }

}
