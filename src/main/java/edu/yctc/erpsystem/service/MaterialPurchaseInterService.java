package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialPurchaseDO;
import edu.yctc.erpsystem.entity.MaterialPurchaseToBeStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialPurchaseVO;

import java.util.List;
import java.util.Map;

/**
 * 原材料采购逻辑接口
 *
 * @author wjd
 */
public interface MaterialPurchaseInterService {

    /**
     * 获得所有原材料采购信息
     *
     * @param params 过滤参数
     * @return 原材料采购信息链表
     */
    ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchase(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料采购信息
     *
     * @param params 过滤参数
     * @return 原材料采购信息链表
     */
    ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchaseByConditions(Map<String, Object> params);

    /**
     * 插入原材料采购记录
     *
     * @param materialPurchaseDO 原材料采购信息视图
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 删除记录
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialPurchaseDO 原材料采购实体
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 通过id修改Amount HopeDeliveryDate Remark
     *
     * @param materialPurchaseDO 原材料采购实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialPurchase(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 插入原材料采购记录至待入库
     *
     * @param materialPurchaseToBeStorageDO 原材料采购信息视图
     * @return 是否成功
     */
    ResultDO<Void> insertToStored(List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageDO);

    /**
     * 批量导入
     *
     * @param user 录入者
     * @return 是否成功
     */
    ResultDO<Void> importExcel(String user);

}
