package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.CustomerDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 客户信息逻辑接口
 *
 * @author smile
 */
public interface CustomerInterService {

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
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改客户信息
     *
     * @param customerDO 主键
     * @return 是否成功
     */
    ResultDO<Void> updateCustomer(CustomerDO customerDO);

    /**
     * 导出excel
     *
     * @param customerDO 客户信息信息视图
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
