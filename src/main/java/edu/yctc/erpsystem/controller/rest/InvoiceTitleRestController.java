package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 发票抬头
 *
 * @author xcg
 */
public interface InvoiceTitleRestController {

    /**
     * 获得发票抬头信息
     *
     * @param params 过滤信息
     * @return 发票抬头
     */
    ResultDO<PageUtils<InvoiceTitleDO>> getInvoiceTitle(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param invoiceTitleDO 发票抬头
     * @return 是否成功
     */
    ResultDO<Void> insert(InvoiceTitleDO invoiceTitleDO);

    /**
     * 删除
     *
     * @param invoiceTitleDO 发票抬头id
     * @return 是否成功
     */
    ResultDO<Void> delete(InvoiceTitleDO invoiceTitleDO);

    /**
     * 根据发票抬头id恢复
     *
     * @param invoiceTitleDO 发票抬头实体
     * @return 是否成功
     */
    ResultDO<Void> recoverInvoiceTitle(InvoiceTitleDO invoiceTitleDO);

    /**
     * 修改发票抬头信息
     *
     * @param invoiceTitleDO 发票抬头实体
     * @return 是否成功
     */
    ResultDO<Void> updateInvoiceTitle(InvoiceTitleDO invoiceTitleDO);

}
