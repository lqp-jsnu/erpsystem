package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInventoryDO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料入库信息DAO接口
 *
 * @author lqp
 */
@Mapper
public interface MaterialInventoryDAO {

    /**
     * 获得原材料入库数据的数量
     *
     * @param params 过滤参数
     * @return 原材料入库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 通过供应商id获得所有原材料入库信息
     *
     * @param params 过滤参数
     * @return 原材料入库信息链表
     */
    List<MaterialInventoryVO> getMaterialInventory(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialInventoryDO 实体
     */
    void insert(MaterialInventoryDO materialInventoryDO);

    /**
     * 更新leftAmount
     *
     * @param materialInventoryDO 实体
     */
    void updateLeftAmountById(MaterialInventoryDO materialInventoryDO);

    /**
     * 批量更新leftAmount
     *
     * @param materialInventoryDO 实体
     */
    void updateAllLeftAmountByIds(@Param("materialInventoryDO")List<MaterialInventoryDO> materialInventoryDO);

    /**
     * 通过id获得原材料入库信息
     *
     * @param id 主键
     * @return 原材料入库信息
     */
    MaterialInventoryDO getMaterialInventoryById(@Param("id") String id);

    /**
     * 通过id获得原材料入库信息
     *
     * @param ids 主键链表
     * @return 原材料入库信息链表
     */
    List<MaterialInventoryDO> getMaterialInventoryByIds(@Param("ids") List<String> ids);

    /**
     * 通过主表Id获得原材料入库信息
     *
     * @param materialInventoryMasterId 主键数组
     * @return 原材料入库信息链表
     */
    List<MaterialInventoryDO> getMaterialInventoryByMasterId(@Param("materialInventoryMasterId") String materialInventoryMasterId);

}
