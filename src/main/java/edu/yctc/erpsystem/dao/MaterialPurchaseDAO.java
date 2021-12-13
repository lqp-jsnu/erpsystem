package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialPurchaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料采购DAO接口
 *
 * @author wjd
 */
@Mapper
public interface MaterialPurchaseDAO {

    /**
     * 获得原材料采购数据的数量
     *
     * @param params 过滤参数
     * @return 原材料采购数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有原材料采购信息
     *
     * @param params 过滤参数
     * @return 原材料采购信息链表
     */
    List<MaterialPurchaseDO> getMaterialPurchase(Map<String, Object> params);

    /**
     * 删除
     *
     * @param id 原材料采购信息id
     */
    void delete(@Param("id") String id);

    /**
     * 插入一条记录
     *
     * @param materialPurchaseDO 原材料采购实体
     */
    void insert(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 插入若干条记录
     *
     * @param materialPurchaseDO 实体
     */
    void insertAll(@Param("materialPurchaseDO") List<MaterialPurchaseDO> materialPurchaseDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialPurchaseDO 原材料采购实体
     */
    void updateCheckerById(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 通过id修改amount hopeDeliveryDate remark
     *
     * @param materialPurchaseDO 原材料采购实体
     */
    void updateMaterialPurchase(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 修改是否已经导出到Excel
     *
     * @param ids 原材料采购ids
     */
    void updateIsExportByIds(@Param("ids") List<String> ids);

    /**
     * 通过主键获得材料原始信息
     *
     * @param id 主键
     * @return 材料原始信息链表
     */
    MaterialPurchaseDO getMaterialPurchaseById(@Param("id") String id);

    /**
     * 通过主键获得所有材料原始信息
     *
     * @param ids 主键链表
     * @return 材料原始信息链表
     */
    List<MaterialPurchaseDO> getMaterialPurchaseByIds(@Param("ids") List<String> ids);

}
