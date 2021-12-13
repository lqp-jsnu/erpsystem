package edu.yctc.erpsystem.controller;

import javax.servlet.http.HttpSession;

/**
 * 成品管理页面接口
 *
 * @author lqp
 */
public interface TheFinishedProductManagementController {

    /**
     * 展示成品原始信息页面
     *
     * @param httpSession session
     * @return 成品原始信息页面
     */
    String showOriginalInformationOfFinishedProduct(HttpSession httpSession);

    /**
     * 展示客户订单页面
     *
     * @param httpSession session
     * @return 客户订单页面
     */
    String showCustomerOrder(HttpSession httpSession);

    /**
     * 展示客户订单明细页面
     *
     * @param httpSession session
     * @return 客户订单明细页面
     */
    String showCustomerOrderDetails(HttpSession httpSession);

    /**
     * 展示订单查询页面
     *
     * @param httpSession session
     * @return 订单查询页面
     */
    String showOrderQuery(HttpSession httpSession);

    /**
     * 展示生产补单页面
     *
     * @param httpSession session
     * @return 生产补单页面
     */
    String showProductionToFillSingle(HttpSession httpSession);

    /**
     * 展示制造流程单页面
     *
     * @param httpSession session
     * @return 制造流程单页面
     */
    String showManufacturingProcessSheet(HttpSession httpSession);

    /**
     * 展示制造流程单详细页面
     *
     * @param httpSession session
     * @return 制造流程单详细页面
     */
    String showDetailedManufacturingProcessSheet(HttpSession httpSession);

    /**
     * 展示已列印流程单页面
     *
     * @param httpSession session
     * @return 已列印流程单页面
     */
    String showTheFlowSheetHasBeenPrinted(HttpSession httpSession);

    /**
     * 展示质量抽检页面
     *
     * @param httpSession session
     * @return 质量抽检页面
     */
    String showTheQualityOfSampling(HttpSession httpSession);

    /**
     * 展示成品入库页面
     *
     * @param httpSession session
     * @return 成品入库页面
     */
    String showFinishingWarehousing(HttpSession httpSession);

    /**
     * 展示成品入库明细页面
     *
     * @param httpSession session
     * @return 成品入库明细页面
     */
    String showFinishedProductWarehousingDetails(HttpSession httpSession);

    /**
     * 展示成品合格率页面
     *
     * @param httpSession session
     * @return 成品合格率页面
     */
    String showQualifiedRateOfFinishedProduct(HttpSession httpSession);

    /**
     * 展示成品库存页面
     *
     * @param httpSession session
     * @return 成品库存页面
     */
    String showFinishedGoodsRepertory(HttpSession httpSession);

    /**
     * 展示成品调拨页面
     *
     * @param httpSession session
     * @return 成品调拨页面
     */
    String showTheFinishedProductTransfers(HttpSession httpSession);

    /**
     * 展示零品入库页面
     *
     * @param httpSession session
     * @return 零品入库页面
     */
    String showZeroProductPutInStorage(HttpSession httpSession);

    /**
     * 展示零品库存页面
     *
     * @param httpSession session
     * @return 零品库存页面
     */
    String showZeroInventory(HttpSession httpSession);

    /**
     * 展示零品调拨页面
     *
     * @param httpSession session
     * @return 零品调拨页面
     */
    String showZeroArticleTransfers(HttpSession httpSession);

    /**
     * 展示成品销售信息页面
     *
     * @param httpSession session
     * @return 成品销售信息页面
     */
    String showFinishedProductSalesInformation(HttpSession httpSession);

    /**
     * 展示出货单页面
     *
     * @param httpSession session
     * @return 出货单页面
     */
    String showDeliveryOrder(HttpSession httpSession);

    /**
     * 展示出货单历史页面
     *
     * @param httpSession session
     * @return 出货单历史页面
     */
    String showInvoiceHistory(HttpSession httpSession);

    /**
     * 展示入库单汇总页面
     *
     * @param httpSession session
     * @return 入库单汇总页面
     */
    String showReceiptSummary(HttpSession httpSession);

    /**
     * 展示成品盘点页面
     *
     * @param httpSession session
     * @return 成品盘点页面
     */
    String showFinishedGoodsInventory(HttpSession httpSession);

    /**
     * 展示成品库存汇总页面
     *
     * @param httpSession session
     * @return 成品库存汇总页面
     */
    String showFinishedGoodsInventorySummary(HttpSession httpSession);

}
