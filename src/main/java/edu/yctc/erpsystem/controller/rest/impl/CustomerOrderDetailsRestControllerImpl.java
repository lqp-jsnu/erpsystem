package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.CustomerOrderDetailsRestController;
import edu.yctc.erpsystem.entity.CustomerOrderDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.CustomerOrderDetailsInterService;
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
 * 客户订单明细
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/customer-order-details")
public class CustomerOrderDetailsRestControllerImpl implements CustomerOrderDetailsRestController {
    @Resource
    private CustomerOrderDetailsInterService customerOrderDetailsService;

    @Override
    @GetMapping("getCustomerOrderDetails")
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderDetails(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderDetailsService.getCustomerOrderDetails(params);
    }

    @Override
    @GetMapping("getCustomerOrderDetailsByConditions")
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderDetailsByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderDetailsService.getCustomerOrderDetailsByConditions(params);
    }

    @Override
    @PostMapping("updateCustomerOrderDetails")
    public ResultDO<Void> updateCustomerOrderDetails(@RequestBody CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId()) || StringUtils.isBlank(customerOrderDO.getEveryOrderAmount())
                || StringUtils.isBlank(customerOrderDO.getProductAmount()) || StringUtils.isBlank(customerOrderDO.getEveryProductAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderDetailsService.updateCustomerOrderDetails(customerOrderDO);
    }

    @Override
    @PostMapping("updateIsFinish")
    public ResultDO<Void> updateFinalCheckFlagById(@RequestBody CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId()) || StringUtils.isBlank(customerOrderDO.getFinalCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderDetailsService.updateFinalCheckFlagById(customerOrderDO);
    }

    @Override
    @PostMapping("make")
    public ResultDO<Void> make(@RequestBody CustomerOrderVO customerOrder, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(customerOrder.getId())
                || StringUtils.isBlank(customerOrder.getOriginalProductId()) || StringUtils.isBlank(customerOrder.getCustomerId())
                || StringUtils.isBlank(customerOrder.getProductAmount()) || StringUtils.isBlank(customerOrder.getEveryOrderAmount())
                || StringUtils.isBlank(customerOrder.getEveryProductAmount()) || StringUtils.isBlank(customerOrder.getOrderAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        customerOrder.setUser( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return customerOrderDetailsService.make(customerOrder);
    }

    @Override
    @PostMapping("finish")
    public ResultDO<Void> finish(@RequestBody CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderDetailsService.finish(customerOrderDO.getId());
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody CustomerOrderVO[] customerOrderVO) {
        if (null == customerOrderVO || 0 == customerOrderVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return customerOrderDetailsService.exportExcel(customerOrderVO);
    }

}
