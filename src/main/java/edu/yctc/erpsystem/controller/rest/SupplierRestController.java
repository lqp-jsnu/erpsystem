package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.SupplierDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 供应商数据接口
 *
 * @author lqp
 */
public interface SupplierRestController {

    /**
     * 获得供应商管理信息
     *
     * @param params 过滤参数
     * @return 供应商管理信息链表
     */
    ResultDO<PageUtils<SupplierDO>> getSuppliers(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param supplierDO 供应商管理信息
     * @return 是否成功
     */
    ResultDO<Void> insert(SupplierDO supplierDO);

    /**
     * 删除
     *
     * @param supplierDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(SupplierDO supplierDO);

    /**
     * 修改供应商信息
     *
     * @param supplierDO 供应商管理实体
     * @return 是否成功
     */
    ResultDO<Void> updateSupplier(SupplierDO supplierDO);

    /**
     * 导出excel
     *
     * @param supplierDO 供应商信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(SupplierDO[] supplierDO);

}
