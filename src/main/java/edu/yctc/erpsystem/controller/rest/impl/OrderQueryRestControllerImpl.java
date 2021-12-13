package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.OrderQueryRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.OrderQueryInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 订单查询
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/order-query")
public class OrderQueryRestControllerImpl implements OrderQueryRestController {

    @Resource
    private OrderQueryInterService orderQueryService;

    @Override
    @GetMapping("getOrderQuery")
    public ResultDO<PageUtils<CustomerOrderVO>> getOrderQuery(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return orderQueryService.getOrderQuery(params);
    }

    @Override
    @GetMapping("getOrderQueryByConditions")
    public ResultDO<PageUtils<CustomerOrderVO>> getOrderQueryByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return orderQueryService.getOrderQueryByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody CustomerOrderVO[] customerOrderVO) {
        if (null == customerOrderVO || 0 == customerOrderVO.length) {
            return new ResultDO<>( false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null );
        }

        return orderQueryService.exportExcel(customerOrderVO);
    }

}
