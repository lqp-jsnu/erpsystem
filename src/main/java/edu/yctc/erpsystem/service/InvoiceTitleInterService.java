package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 发票抬头逻辑接口
 *
 * @author xcg
 */
public interface InvoiceTitleInterService {

    /**
     * 获得发票抬头信息
     *
     * @param params 过滤参数
     * @return 发票抬头信息
     */
    ResultDO<PageUtils<InvoiceTitleDO>> getInvoiceTitle(Map<String, Object> params);

    /**
     * 添加发票抬头
     *
     * @param invoiceTitleDO 发票抬头实体
     * @return 是否成功
     */
    ResultDO<Void> insert(InvoiceTitleDO invoiceTitleDO);

    /**
     * 删除
     *
     * @param id 发票抬头id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 根据发票抬头id恢复发票抬头
     *
     * @param invoiceTitleDO 发票抬头实体
     * @return 是否成功
     */
    ResultDO<Void> recoverInvoiceTitle(InvoiceTitleDO invoiceTitleDO);

    /**
     * 修改发票抬头
     *
     * @param invoiceTitleDO 发票抬头实体
     * @return 是否成功
     */
    ResultDO<Void> updateInvoiceTitle(InvoiceTitleDO invoiceTitleDO);

}
