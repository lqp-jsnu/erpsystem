package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 出货单历史视图
 *
 * @author zzy
 */
public class BillOfSaleHistoryVO {

    /**
     * id
     */
    private String id;

    /**
     * 客户订单号
     */
    private String orderNumber;

    /**
     * 出货单号
     */
    private String billOfSaleNumber;

    /**
     * 客户代码
     */
    private String code;

    /**
     * 客户料号
     */
    private String productNumber;

    /**
     * 品名
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 出库数量
     */
    private String exportAmount;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 金额
     */
    private String money;

    /**
     * 出库日期
     */
    private Date billOfSaleDate;

    /**
     * 发票抬头
     */
    private String titleName;

    /**
     * 备注
     */
    private String remark;

    /**
     *t_billOfSale的主键
     */
    private String billOfSaleId;

    /**
     * t_invoiceTitle的主键
     */
    private String invoiceTitleId;

    /**
     * t_customerOrder的主键
     */
    private String customerOrderId;

    /**
     * t_customer的主键
     */
    private String customerId;

    /**
     * t_originalProduct的主键
     */
    private String originalProductId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBillOfSaleNumber() {
        return billOfSaleNumber;
    }

    public void setBillOfSaleNumber(String billOfSaleNumber) {
        this.billOfSaleNumber = billOfSaleNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getExportAmount() {
        return exportAmount;
    }

    public void setExportAmount(String exportAmount) {
        this.exportAmount = exportAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getBillOfSaleDate() {
        return billOfSaleDate;
    }

    public void setBillOfSaleDate(Date billOfSaleDate) {
        this.billOfSaleDate = billOfSaleDate;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBillOfSaleId() {
        return billOfSaleId;
    }

    public void setBillOfSaleId(String billOfSaleId) {
        this.billOfSaleId = billOfSaleId;
    }

    public String getInvoiceTitleId() {
        return invoiceTitleId;
    }

    public void setInvoiceTitleId(String invoiceTitleId) {
        this.invoiceTitleId = invoiceTitleId;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    @Override
    public String toString() {
        return "BillOfSaleHistoryVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", billOfSaleNumber='" + billOfSaleNumber + '\'' +
                ", code='" + code + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                ", billOfSaleDate=" + billOfSaleDate +
                ", titleName='" + titleName + '\'' +
                ", remark='" + remark + '\'' +
                ", billOfSaleId='" + billOfSaleId + '\'' +
                ", invoiceTitleId='" + invoiceTitleId + '\'' +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                '}';
    }

}
