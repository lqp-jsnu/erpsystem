package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.CustomerDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 客户信息数据接口
 *
 * @author smile
 */
public interface CustomerRestController {

    /**
     * 获得客户管理数据
     *
     * @param params 过滤参数
     * @return 客户管理链表
     */
    ResultDO<PageUtils<CustomerDO>> getCustomer(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param customerDO 客户管理实体
     * @return 是否成功
     */
    ResultDO<Void> insert(CustomerDO customerDO);

    /**
     * 删除
     *
     * @param customerDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(CustomerDO customerDO);

    /**
     * 修改客户信息
     *
     * @param customerDO 客户管理实体
     * @return 是否成功
     */
    ResultDO<Void> updateCustomer(CustomerDO customerDO);

    /**
     * 导出excel
     *
     * @param customerDO 客户管理实体
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(CustomerDO[] customerDO);

    /**
     * 批量导入
     *
     * @return 是否成功
     */
    ResultDO<Void> importExcel();

}
