package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;

import java.util.Map;

/**
 * 成品盘点数据接口
 *
 * @author zzy
 */
public interface FinishedGoodsInventoryRestController {

    /**
     * 获得所有成品盘点信息
     *
     * @param params  过滤参数
     * @return  成品盘点信息链表
     */
    ResultDO<PageUtils<ProductInventoryRepertoryVO>> getFinishedGoodsInventory(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品盘点信息
     *
     * @param params 过滤参数
     * @return 成品盘点信息链表
     */
    ResultDO<PageUtils<ProductInventoryRepertoryVO>> getFinishedGoodsInventoryByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param productInventoryRepertoryVO 成品盘点信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ProductInventoryRepertoryVO[] productInventoryRepertoryVO);

}
