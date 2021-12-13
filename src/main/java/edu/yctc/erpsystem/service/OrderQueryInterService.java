package edu.yctc.erpsystem.service;


import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;

import java.util.Map;

/**
 * 订单查询
 *
 * @author smile
 */
public interface OrderQueryInterService {

    /**
     * 获得所有订单查询信息
     *
     * @param params 过滤参数
     * @return 订单查询表
     */
    ResultDO<PageUtils<CustomerOrderVO>> getOrderQuery(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的订单查询信息
     *
     * @param params 过滤参数
     * @return 订单查询表
     */
    ResultDO<PageUtils<CustomerOrderVO>> getOrderQueryByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param customerOrders 订单查询信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(CustomerOrderVO[] customerOrders);

}
