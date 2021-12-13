package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.WarehouseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 仓库DAO接口
 *
 * @author lqp
 */
@Mapper
public interface WarehouseDAO {

    /**
     * 获得仓库管理信息数据的数量
     *
     * @param params 过滤参数
     * @return 仓库管理信息数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得仓库管理信息
     *
     * @param params 过滤参数
     * @return 仓库管理链表
     */
    List<WarehouseDO> getWarehouse(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param warehouseDO 仓库实体
     */
    void insert(WarehouseDO warehouseDO);

    /**
     * 删除
     *
     * @param id 仓库id
     */
    void delete(@Param("id") String id);

    /**
     * 修改仓库信息
     *
     * @param warehouseDO 仓库实体
     */
    void updateWareHouse(WarehouseDO warehouseDO);

    /**
     * 通过仓库id获得仓库名称
     *
     * @param id 仓库id
     * @return 仓库名称
     */
    WarehouseDO getWarehouseById(@Param("id") String id);

    /**
     * 通过仓库id获得仓库名称
     *
     * @param ids 仓库id
     * @return 仓库名称
     */
    List<WarehouseDO> getWarehouseByIds(@Param("ids") List<String> ids);

}
