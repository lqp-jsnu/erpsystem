package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户信息管理表
 *
 * @author smile
 */
@Mapper
public interface CustomerDAO {

    /**
     * 获得客户信息数据的数量
     *
     * @param params 过滤参数
     * @return 客户数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 搜索客户信息
     *
     * @param params 搜索信息
     * @return 客户信息主表链表
     */
    List<CustomerDO> getCustomer(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param customerDO 实体
     */
    void insert(CustomerDO customerDO);

    /**
     * 插入若干条记录
     *
     * @param customerDO 实体
     */
    void insertAll(@Param("customerDO") List<CustomerDO> customerDO);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(@Param("id") String id);

    /**
     * 修改客户信息
     *
     * @param customerDO 修改实体
     */
    void updateCustomer(CustomerDO customerDO);

    /**
     * 通过主键获得客户信息
     *
     * @param id 主键
     * @return 客户信息
     */
    CustomerDO getCustomerById(@Param("id") String id);

    /**
     * 通过主键获得所有客户信息
     *
     * @param ids 主键链表
     * @return 客户信息管理表
     */
    List<CustomerDO> getCustomerByIds(@Param("ids") List<String> ids);

}
