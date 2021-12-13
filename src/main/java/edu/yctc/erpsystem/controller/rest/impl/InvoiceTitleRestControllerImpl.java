package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.InvoiceTitleRestController;
import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.InvoiceTitleInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 发票抬头接口实现
 *
 * @author xcg
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/the-invoice-looked-up")
public class InvoiceTitleRestControllerImpl implements InvoiceTitleRestController {

    @Resource
    private InvoiceTitleInterService invoiceTitleService;

    @Override
    @GetMapping("getInvoiceTitle")
    public ResultDO<PageUtils<InvoiceTitleDO>> getInvoiceTitle(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return invoiceTitleService.getInvoiceTitle(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return invoiceTitleService.insert(invoiceTitleDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return invoiceTitleService.delete(invoiceTitleDO.getId());
    }

    @Override
    @PostMapping("recoverInvoiceTitle")
    public ResultDO<Void> recoverInvoiceTitle(@RequestBody InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return invoiceTitleService.recoverInvoiceTitle(invoiceTitleDO);
    }

    @Override
    @PostMapping("updateInvoiceTitle")
    public ResultDO<Void> updateInvoiceTitle(@RequestBody InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getId()) || StringUtils.isBlank(invoiceTitleDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

       return invoiceTitleService.updateInvoiceTitle(invoiceTitleDO);
    }

}
