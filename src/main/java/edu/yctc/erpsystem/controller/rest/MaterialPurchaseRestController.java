package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.MaterialPurchaseDO;
import edu.yctc.erpsystem.entity.MaterialPurchaseToBeStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialPurchaseVO;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 原材料采购逻辑数据接口
 *
 * @author wjd
 */
public interface MaterialPurchaseRestController {

    /**
     * 获得所有原材料采购信息
     *
     * @param params 过滤参数
     * @return 原材料采购链表
     */
    ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchase(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料采购信息
     *
     * @param params 过滤参数
     * @return 原材料采购链表
     */
    ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchaseByConditions(Map<String, Object> params);

    /**
     * 增加原材料采购信息
     *
     * @param materialPurchaseDO 原材料采购实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialPurchaseDO materialPurchaseDO, HttpSession httpSession);

    /**
     * 删除记录
     *
     * @param materialPurchaseDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 通过id修改 hopeDeliveryDate amount remark信息
     *
     * @param materialPurchaseDO 原材料采购实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialPurchase(MaterialPurchaseDO materialPurchaseDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialPurchaseDO 原材料采购实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialPurchaseDO materialPurchaseDO, HttpSession httpSession);

    /**
     * 插入原材料采购记录至待入库
     *
     * @param materialPurchaseToBeStorageDO 原材料待入库实体链表
     * @return 是否成功
     */
    ResultDO<Void> insertToStored(List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageDO);

    /**
     * 批量导入
     *
     * @return 是否成功
     */
    ResultDO<Void> importExcel(HttpSession httpSession);

}
