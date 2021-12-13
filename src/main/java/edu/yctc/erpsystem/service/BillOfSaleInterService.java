package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.BillOfSaleDO;
import edu.yctc.erpsystem.entity.BillOfSaleHistoryDO;
import edu.yctc.erpsystem.entity.MaterialPurchaseDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleVO;

import java.util.Map;

/**
 * 出货单信息逻辑接口
 *
 * @author zzy
 */
public interface BillOfSaleInterService {

    /**
     * 获得所有出货单信息
     *
     * @param params  过滤参数
     * @return  出货单信息链表
     */
    ResultDO<PageUtils<BillOfSaleVO>> getBillOfSale(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的出货单信息
     *
     * @param params 过滤参数
     * @return 出货单信息链表
     */
    ResultDO<PageUtils<BillOfSaleVO>> getBillOfSaleByConditions(Map<String, Object> params);

    /**
     * 生成出库单功能
     *
     * @param billOfSaleHistoryDO 出货单历史实体
     * @return 是否成功
     */
    ResultDO<Void> insert(BillOfSaleHistoryDO billOfSaleHistoryDO);

    /**
     * 添加备注信息
     *
     * @param billOfSaleDO 待审核的信息
     * @return 是否成功
     */
    ResultDO<Void> updateRemarkById(BillOfSaleDO billOfSaleDO);

    /**
     * 导出excel
     *
     * @param billOfSalesVO 出货单信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(BillOfSaleVO[] billOfSalesVO);

    /**
     * 批量导入
     *
     * @return 是否成功
     */
    ResultDO<Void> importExcel();

}
