package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.TheFinishedProductManagementController;
import edu.yctc.erpsystem.session.SessionContentHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 成品管理页面实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/the-finished-product-management")
public class TheFinishedProductManagementControllerImpl implements TheFinishedProductManagementController {

    @Override
    @GetMapping("original-information-of-finished-product")
    public String showOriginalInformationOfFinishedProduct(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ORIGINAL_INFORMATION_OF_FINISHED_PRODUCT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("customer-order")
    public String showCustomerOrder(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.CUSTOMER_ORDER : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("customer-order-details")
    public String showCustomerOrderDetails(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.CUSTOMER_ORDER_DETAILS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("order-query")
    public String showOrderQuery(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ORDER_QUERY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("production-to-fill-a-single")
    public String showProductionToFillSingle(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.PRODUCTION_TO_FILL_A_SINGLE : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("manufacturing-process-sheet")
    public String showManufacturingProcessSheet(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MANUFACTURING_PROCESS_SHEET : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("detailed-manufacturing-process-sheet")
    public String showDetailedManufacturingProcessSheet(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.DETAILED_MANUFACTURING_PROCESS_SHEET : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("the-flow-sheet-has-been-printed")
    public String showTheFlowSheetHasBeenPrinted(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.THE_FLOW_SHEET_HAS_BEEN_PRINTED : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("the-quality-of-sampling")
    public String showTheQualityOfSampling(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.THE_QUALITY_OF_SAMPLING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("finishing-warehousing")
    public String showFinishingWarehousing(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHING_WAREHOUSING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("finished-product-warehousing-details")
    public String showFinishedProductWarehousingDetails(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHED_PRODUCT_WAREHOUSING_DETAILS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("qualified-rate-of-finished-product")
    public String showQualifiedRateOfFinishedProduct(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.QUALIFIED_RATE_OF_FINISHED_PRODUCT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("finished-goods-repertory")
    public String showFinishedGoodsRepertory(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHED_GOODS_REPERTORY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("the-finished-product-transfers")
    public String showTheFinishedProductTransfers(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.THE_FINISHED_PRODUCT_TRANSFERS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("zero-product-put-in-storage")
    public String showZeroProductPutInStorage(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ZERO_PRODUCT_PUT_IN_STORAGE : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("zero-inventory")
    public String showZeroInventory(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ZERO_INVENTORY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("zero-article-transfers")
    public String showZeroArticleTransfers(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ZERO_ARTICLE_TRANSFERS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("finished-product-sales-information")
    public String showFinishedProductSalesInformation(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHED_PRODUCT_SALES_INFORMATION : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("delivery-order")
    public String showDeliveryOrder(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.DELIVERY_ORDER : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("invoice-history")
    public String showInvoiceHistory(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.INVOICE_HISTORY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("receipt-summary")
    public String showReceiptSummary(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.RECEIPT_SUMMARY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("finished-goods-inventory")
    public String showFinishedGoodsInventory(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHED_GOODS_INVENTORY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("finished-goods-inventory-summary")
    public String showFinishedGoodsInventorySummary(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHED_GOODS_INVENTORY_SUMMARY : TemplatePath.NO_AUTHORIZATION;
    }

}
