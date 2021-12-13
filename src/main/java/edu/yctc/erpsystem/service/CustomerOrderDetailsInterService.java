package edu.yctc.erpsystem.service;


import edu.yctc.erpsystem.entity.CustomerOrderDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;

import java.util.Map;

/**
 * 客户订单明细
 *
 * @author smile
 */
public interface CustomerOrderDetailsInterService {

    /**
     * 获得所有客户订单明细信息
     *
     * @param params 过滤参数
     * @return 订单查询表
     */
    ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderDetails(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的客户订单明细信息
     *
     * @param params 过滤参数
     * @return 订单查询表
     */
    ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderDetailsByConditions(Map<String, Object> params);

    /**
     * 通过id修改custOrderNumber custCode itemName等
     *
     * @param customerOrderDO 客户订单明细实体
     * @return 是否成功
     */
    ResultDO<Void> updateCustomerOrderDetails(CustomerOrderDO customerOrderDO);

    /**
     * 通过id修改finalCheckFlag
     *
     * @param customerOrderDO 客户订单明细实体
     * @return 是否成功
     */
    ResultDO<Void> updateFinalCheckFlagById(CustomerOrderDO customerOrderDO);

    /**
     * 通过客户订单明细id完结，插入一条制造流程单
     *
     * @param customerOrder 客户订单明细视图
     * @return 是否成功
     */
    ResultDO<Void> make(CustomerOrderVO customerOrder);

    /**
     * 通过客户订单明细id生成制造流程单
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> finish(String id);

    /**
     * 导出excel
     *
     * @param customerOrderVO 客户订单详细信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(CustomerOrderVO[] customerOrderVO);

}
