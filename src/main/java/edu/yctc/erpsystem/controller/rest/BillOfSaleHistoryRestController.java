package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleHistoryVO;

import java.util.List;
import java.util.Map;

/**
 * 出货单历史数据接口
 *
 * @author zzy
 */
public interface BillOfSaleHistoryRestController {

    /**
     * 获得所有出货单历史信息
     *
     *@param params 过滤参数
     * @return 出货单历史信息链表
     */
    ResultDO<PageUtils<BillOfSaleHistoryVO>> getBillOfSaleHistory(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的出货单历史信息
     *
     * @param params 过滤参数
     * @return 出货单历史信息链表
     */
    ResultDO<PageUtils<BillOfSaleHistoryVO>> getBillOfSaleHistoryByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param billOfSaleHistoryVO 历史出货单信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(BillOfSaleHistoryVO[] billOfSaleHistoryVO);

    /**
     * 插入原材料采购记录至待入库
     *
     * @param billOfSaleHistoryVO 出货单入库实体链表
     * @return 是否成功
     */
    ResultDO<Void> exportExcelByModel(List<BillOfSaleHistoryVO> billOfSaleHistoryVO);

}
