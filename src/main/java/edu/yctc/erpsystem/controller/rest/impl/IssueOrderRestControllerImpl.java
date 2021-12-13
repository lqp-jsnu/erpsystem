package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.IssueOrderRestController;
import edu.yctc.erpsystem.entity.IssueOrderDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.IssueOrderInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.IssueOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 生产补单表
 *
 * @author smile
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/production-to-fill-a-single")
public class IssueOrderRestControllerImpl implements IssueOrderRestController {

    @Resource
    private IssueOrderInterService issueOrderService;

    @Override
    @GetMapping("getIssueOrder")
    public ResultDO<PageUtils<IssueOrderVO>> getIssueOrder(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return issueOrderService.getIssueOrder(params);
    }

    @Override
    @GetMapping("getIssueOrderByConditions")
    public ResultDO<PageUtils<IssueOrderVO>> getIssueOrderByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return issueOrderService.getIssueOrderByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody IssueOrderDO issueOrderDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(issueOrderDO.getIssueAmount()) || StringUtils.isBlank(issueOrderDO.getCustomerOrderId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        issueOrderDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return issueOrderService.insert(issueOrderDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody IssueOrderDO issueOrderDO) {
        if (StringUtils.isBlank(issueOrderDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return issueOrderService.delete(issueOrderDO.getId());
    }

    @Override
    @PostMapping("updateIssueOrder")
    public ResultDO<Void> updateIssueOrder(@RequestBody IssueOrderDO issueOrderDO) {
        if (StringUtils.isBlank(issueOrderDO.getId()) || StringUtils.isBlank(issueOrderDO.getIssueAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return issueOrderService.updateIssueOrder(issueOrderDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody IssueOrderDO issueOrderDO, HttpSession httpSession) {
        if (StringUtils.isBlank(issueOrderDO.getId()) || StringUtils.isBlank(issueOrderDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        issueOrderDO.setChecker( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return issueOrderService.updateCheckerById(issueOrderDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody IssueOrderVO[] issueOrderVO) {
        if (null == issueOrderVO || 0 == issueOrderVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return issueOrderService.exportExcel(issueOrderVO);
    }

    @Override
    @PostMapping("make")
    public ResultDO<Void> make(@RequestBody IssueOrderVO issueOrder) {
        if ( StringUtils.isBlank(issueOrder.getId()) || StringUtils.isBlank(issueOrder.getIssueAmount()) || StringUtils.isBlank(issueOrder.getCustomerId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return issueOrderService.make(issueOrder);
    }

}
