package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.CustomerOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户订单DAO接口
 *
 * @author zzy
 */
@Mapper
public interface CustomerOrderDAO {

    /**
     * 获得客户订单数据的数量
     *
     * @param params 过滤参数
     * @return 客户订单数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有客户订单
     *
     * @param params 过滤参数
     * @return 客户订单信息表
     */
    List<CustomerOrderDO> getCustomerOrder(Map<String, Object> params);

    /**
     * 客户订单插入一条记录
     *
     * @param customerOrderDO 客户订单信息实体
     */
    void insert(CustomerOrderDO customerOrderDO);

    /**
     * 插入若干条记录
     *
     * @param customerOrderDO 实体
     */
    void insertAll(@Param("customerOrderDO") List<CustomerOrderDO> customerOrderDO);

    /**
     * 通过客户订单d删除记录
     *
     * @param id 客户订单信息主键
     */
    void delete(@Param("id")String id);

    /**
     * 客户订单明细通过id修改finalCheckFlag
     *
     * @param customerOrderDO 客户订单明细实体
     */
    void updateFinalCheckFlagById(CustomerOrderDO customerOrderDO);

    /**
     * 客户订单明细通过id编辑
     *
     * @param customerOrderDO 客户订单明细实体
     */
    void updateCustomerOrderDetailsById(CustomerOrderDO customerOrderDO);

    /**
     * 客户订单通过id修改编辑
     *
     * @param customerOrderDO 客户订单明细实体
     */
    void updateCustomerOrder(CustomerOrderDO customerOrderDO);

    /**
     * 客户订单通过id修改checkerId checkerFlag
     *
     * @param customerOrderDO 客户订单
     */
    void updateCheckerById(CustomerOrderDO customerOrderDO);

    /**
     * 通过客户订单明细id生成制造流程单
     *
     * @param id 客户订单明细主键
     */
    void make(@Param("id")String id);

    /**
     * 通过客户订单明细id完结
     *
     * @param id 客户订单明细主键
     */
    void finish(@Param("id")String id);

    /**
     * 通过主键获得客户订单
     *
     * @param id 主键
     * @return 客户订单信息
     */
    CustomerOrderDO getCustomerOrderById(@Param("id") String id);

    /**
     * 通过主键获得所有客户订单
     *
     * @param ids 主键链表
     * @return 客户订单信息链表
     */
    List<CustomerOrderDO> getCustomerOrderByIds(@Param("ids") List<String> ids);

}
