package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.CustomerOrderDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 客户订单
 *
 * @author smile
 */
public interface CustomerOrderRestController {

    /**
     * 获得所有客户订单信息
     *
     * @param params 过滤参数
     * @return 订单查询表
     */
    ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrder(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的客户订单信息
     *
     * @param params 过滤参数
     * @return 订单查询表
     */
    ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderByConditions(Map<String, Object> params);

    /**
     * 增加客户订单信息
     *
     * @param customerOrderDO 客户订单实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(CustomerOrderDO customerOrderDO, HttpSession httpSession);

    /**
     * 删除记录
     *
     * @param customerOrderDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(CustomerOrderDO customerOrderDO);

    /**
     * 通过id编辑
     *
     * @param customerOrderDO 客户订单明细实体
     * @return 是否成功
     */
    ResultDO<Void> updateCustomerOrder(CustomerOrderDO customerOrderDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param customerOrderDO 客户订单实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(CustomerOrderDO customerOrderDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param customerOrderVO 客户订单信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(CustomerOrderVO[] customerOrderVO);

    /**
     * 批量导入
     *
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> importExcel(HttpSession httpSession);

}
