package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ProductInventoryDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainProductExpiryDateVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.ProductInventorySummaryVO;

import java.util.Map;

/**
 * 成品库存数据接口
 *
 * @author qaing
 */
public interface ProductInventoryController {

    /**
     * 获得所有成品库存汇总信息
     *
     * @param params 过滤参数
     * @return 成品库存汇总信息链表
     */
    ResultDO<PageUtils<ProductInventorySummaryVO>> getProductInventorySummary(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param prodInventorySummaryVO 成品库存汇总信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ProductInventorySummaryVO[] prodInventorySummaryVO);

    /**
     * 获得成品数量监控信息
     *
     * @param params 过滤参数
     * @return 成品数量监控信息链表
     */
    ResultDO<PageUtils<MainProductNumberVO>> getMainProductNumber(Map<String, Object> params);

    /**
     * 获得成品保质期监控信息
     *
     * @param params 过滤参数
     * @return 成品保质期监控信息链表
     */
    ResultDO<PageUtils<MainProductExpiryDateVO>> getMainProductExpiryDate(Map<String, Object> params);

    /**
     * 当成品入库审核通过时插入成品入库记录
     *
     * @param productInventoryDO 成品库存实体
     * @return 是否成功
     */
    ResultDO<Void> insert(ProductInventoryDO productInventoryDO);

}
