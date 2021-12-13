package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductStorageDetailsVO;

import java.util.Map;

/**
 * 成品入库明细信息逻辑接口
 *
 * @author zzy
 */
public interface ProductStorageDetailsInterService {

    /**
     * 获得所有成品入库明细信息
     *
     * @param params  过滤参数
     * @return  成品入库明细信息链表
     */
    ResultDO<PageUtils<ProductStorageDetailsVO>> getProductStorageDetails(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品入库明细信息
     *
     * @param params 过滤参数
     * @return 成品入库明细信息链表
     */
    ResultDO<PageUtils<ProductStorageDetailsVO>> getProductStorageDetailsByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param productStorageDetailsVO 成品出库明细信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ProductStorageDetailsVO[] productStorageDetailsVO);

}
