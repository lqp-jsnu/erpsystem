package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.SupplierDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 供应商逻辑接口
 *
 * @author lqp
 */
public interface SupplierInterService {

    /**
     * 搜索供应商信息
     *
     * @param params 搜索信息
     * @return 供应商信息链表
     */
    ResultDO<PageUtils<SupplierDO>> getSuppliers(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param supplierDO 供应商信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(SupplierDO supplierDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改供应商信息
     *
     * @param supplierDO 供应商信息实体
     * @return 是否成功
     */
    ResultDO<Void> updateSupplier(SupplierDO supplierDO);

    /**
     * 导出excel
     *
     * @param supplierDO 供应商信息实体
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(SupplierDO[] supplierDO);

}
