package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialPurchaseToBeStorageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料待入库信息DAO接口
 *
 * @author wjd
 */
@Mapper
public interface MaterialPurchaseToBeStorageDAO {

    /**
     * 获得原材料待入库数据的数量
     *
     * @param params 过滤参数
     * @return 原材料待入库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有原材料待入库信息
     *
     * @param params 过滤参数
     * @return 原材料待入库信息链表
     */
    List<MaterialPurchaseToBeStorageDO> getMaterialPurchaseToBeStorage(Map<String, Object> params);

    /**
     * 插入若干条记录
     *
     * @param materialPurchaseToBeStorageDO 原材料待入库实体
     */
    void insertAll(@Param("materialPurchaseToBeStorageDO") List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageDO);

    /**
     * 通过id更新待入库入库数量
     *
     * @param materialPurchaseToBeStorageDO 原材料采购实体
     */
    void updateInAmountByMaterialPurchaseToBeStorageId(MaterialPurchaseToBeStorageDO materialPurchaseToBeStorageDO);

    /**
     * 通过id更新待入库allIn
     *
     * @param materialPurchaseToBeStorageDO 原材料采购实体
     */
    void updateAllInByMaterialPurchaseToBeStorageId(MaterialPurchaseToBeStorageDO materialPurchaseToBeStorageDO);
    
    /**
     * 通过id获得原材料待入库信息
     *
     * @param id 主键
     * @return 原材料待入库信息
     */
    MaterialPurchaseToBeStorageDO getMaterialPurchaseToBeStorageById(@Param("id") String id);

    /**
     * 通过ids获得原材料待入库信息(原材料入库insert)
     *
     * @param ids 主键链表
     * @return 原材料待入库信息链表
     */
    List<MaterialPurchaseToBeStorageDO> getMaterialPurchaseToBeStorageByIds(@Param("ids") List<String> ids);

}
