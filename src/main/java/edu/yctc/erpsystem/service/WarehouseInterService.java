package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.WarehouseDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.WarehouseVO;

import java.util.Map;

/**
 * 仓库管理逻辑接口
 *
 * @author qiang
 */
public interface WarehouseInterService {

    /**
     * 获得仓库管理信息
     *
     * @param params 过滤参数
     * @return 仓库管理链表
     */
    ResultDO<PageUtils<WarehouseVO>> getWarehouse(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的仓库管理信息
     *
     * @param params 过滤参数
     * @return 仓库管理信息链表
     */
    ResultDO<PageUtils<WarehouseVO>> getWarehouseByConditions(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param warehouseDO 仓库管理信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(WarehouseDO warehouseDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改仓库信息
     *
     * @param warehouseDO 仓库管理信息实体
     * @return 是否成功
     */
    ResultDO<Void> updateWarehouse(WarehouseDO warehouseDO);

}
