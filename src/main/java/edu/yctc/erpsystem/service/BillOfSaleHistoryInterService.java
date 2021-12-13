package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleHistoryVO;

import java.util.List;
import java.util.Map;

/**
 * 出货单历史逻辑接口
 *
 * @author zzy
 */
public interface BillOfSaleHistoryInterService {

    /**
     * 获得所有出货单历史信息
     *
     * @param params  过滤参数
     * @return  出货单历史信息链表
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
     * @param billOfSaleHistoryVO 出货单历史信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(BillOfSaleHistoryVO[] billOfSaleHistoryVO);

    /**
     * 通过模板导出excel
     *
     * @param billOfSaleHistoryVO 出货单历史信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcelByModel(List<BillOfSaleHistoryVO> billOfSaleHistoryVO);

}
