package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.InvoiceTitleDAO;
import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.InvoiceTitleInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 发票抬头逻辑接口实现
 *
 * @author xcg
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("invoiceTitleService")
public class InvoiceTitleServiceImpl implements InvoiceTitleInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private InvoiceTitleDAO invoiceTitleDAO;

    @Override
    public ResultDO<PageUtils<InvoiceTitleDO>> getInvoiceTitle(Map<String, Object> params) {
        return CallbackUtils.getCallback("getInvoiceTitle", params.toString(), () -> {
            List<InvoiceTitleDO> invoiceTitleList = invoiceTitleDAO.getInvoiceTitle(params);
            if (invoiceTitleList == null) {
                logger.error("getInvoiceTitle exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(invoiceTitleDAO.count(params), invoiceTitleList));
        });
    }

    @Override
    public ResultDO<Void> insert(InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("InvoiceTitle", invoiceTitleDO.toString(), () -> invoiceTitleDAO.insert(invoiceTitleDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("InvoiceTitle", id, () -> invoiceTitleDAO.delete(id));
    }

    @Override
    public ResultDO<Void> recoverInvoiceTitle(InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("recoverInvoiceTitle", invoiceTitleDO.toString(), () -> invoiceTitleDAO.restore(invoiceTitleDO.getId()));
    }

    @Override
    public ResultDO<Void> updateInvoiceTitle(InvoiceTitleDO invoiceTitleDO) {
        if (StringUtils.isBlank(invoiceTitleDO.getId()) || StringUtils.isBlank(invoiceTitleDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateInvoiceTitle", invoiceTitleDO.toString(), () -> invoiceTitleDAO.updateInvoiceTitle(invoiceTitleDO));
    }

}
