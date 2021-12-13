package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialPurchaseToBeStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialPurchaseToBeStorageVO;

import java.util.List;
import java.util.Map;

/**
 * 原材料待入库逻辑接口
 *
 * @author wjd
 */
public interface MaterialPurchaseToBeStorageInterService {

    /**
     * 获得原材料待入库信息
     *
     * @param params 过滤参数
     * @return 原材料待入库信息链表
     */
    ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorage(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料待入库信息
     *
     * @param params 过滤参数
     * @return 原材料待入库信息链表
     */
    ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorageByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param materialPurchaseToBeStorageVO 原材料待入库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(List<MaterialPurchaseToBeStorageVO> materialPurchaseToBeStorageVO);

    /**
     * 通过供应商代号获得所有原材料待入库信息
     *
     * @param params 过滤参数
     * @return 原材料待入库信息链表
     */
    ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorageBySearchSuppId(Map<String, Object> params);

}
