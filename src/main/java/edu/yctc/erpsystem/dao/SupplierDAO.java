package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.SupplierDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 供应商信息DAO接口
 *
 * @author lqp
 */
@Mapper
public interface SupplierDAO {

    /**
     * 获得供应商信息数据的数量
     *
     * @param params 过滤参数
     * @return 供应商信息数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 搜索供应商信息
     *
     * @param params 搜索信息
     * @return 供应商信息链表
     */
    List<SupplierDO> getSupplier(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param supplierDO 供应商信息实体
     */
    void insert(SupplierDO supplierDO);

    /**
     * 删除
     *
     * @param id 供应商信息id
     */
    void delete(@Param("id") String id);

    /**
     * 修改供应商信息
     *
     * @param supplierDO 供应商信息实体
     */
    void updateSupplier(SupplierDO supplierDO);

    /**
     * 通过主键获得供应商信息
     *
     * @param codes 供应商代号链表
     * @return 供应商信息链表
     */
    List<SupplierDO> getSupplierByCodes(@Param("codes") List<String> codes);

    /**
     * 通过主键获得供应商信息
     *
     * @param id 主键
     * @return 供应商信息链表
     */
    SupplierDO getSupplierById(@Param("id") String id);

    /**
     * 通过主键获得供应商信息
     *
     * @param ids 主键链表
     * @return 供应商信息链表
     */
    List<SupplierDO> getSupplierByIds(@Param("ids") List<String> ids);

}
