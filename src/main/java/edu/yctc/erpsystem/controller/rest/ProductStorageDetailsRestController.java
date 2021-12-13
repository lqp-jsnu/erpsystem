package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductStorageDetailsVO;

import java.util.Map;

/**
 * 成品入库明细信息数据接口
 *
 * @author zzy
 */
public interface ProductStorageDetailsRestController {

    /**
     * 获得所有成品入库明细信息
     *
     * @param params 过滤参数
     * @return 成品入库明细信息链表
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
     * @param productStorageDetailsVO 成品入库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ProductStorageDetailsVO[] productStorageDetailsVO);

}
