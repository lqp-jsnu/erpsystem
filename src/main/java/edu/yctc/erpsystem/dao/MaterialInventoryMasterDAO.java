package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInventoryMasterDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 材料库存信息DAO接口
 *
 * @author qiang
 */
@Mapper
public interface MaterialInventoryMasterDAO {

    /**
     * 获得材料库存数据的数量
     *
     * @param params 过滤参数
     * @return 原材料入库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有材料库存信息
     *
     * @param params 过滤参数
     * @return 材料库存信息链表
     */
    List<MaterialInventoryMasterDO> getMaterialInventoryMaster(Map<String, Object> params);

    /**
     * 插入一条记录
     * @param materialInventoryMasterDO 材料库存实体
     */
    void insert(MaterialInventoryMasterDO materialInventoryMasterDO);

    /**
     * 通过id获得材料库存信息
     *
     * @param id 材料库存信息id
     * @return 材料库存信息
     */
    MaterialInventoryMasterDO getMaterialInventoryMasterById(@Param("id") String id);

    /**
     * 通过id获得材料库存信息
     *
     * @param ids 材料库存信息id表
     * @return 材料库存信息表
     */
    List<MaterialInventoryMasterDO> getMaterialInventoryMasterByIds(@Param("ids") List<String> ids);

    /**
     * 通过materialInfoMasterId获得材料库存信息
     *
     * @param materialInfoMasterId 原材料主信息id
     * @return 材料库存信息
     */
    MaterialInventoryMasterDO getMaterialInventoryMasterByMaterialInfoMasterId(@Param("materialInfoMasterId") String materialInfoMasterId);

}
