package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 出货单视图
 *
 * @author zzy
 */
public class BillOfSaleVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 客户代号
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
     * 单价
     */
    private String unitPrice;

    /**
     * 单位
     */
    private String unit;

    /**
     * 金额
     */
    private String money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发票抬头主键
     */
    private String invoiceTitleId;

    /**
     * 出货单号
     */
    private String billOfSaleNumber;

    /**
     * 出货日期
     */
    private Date billOfSaleDate;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInvoiceTitleId() {
        return invoiceTitleId;
    }

    public void setInvoiceTitleId(String invoiceTitleId) {
        this.invoiceTitleId = invoiceTitleId;
    }

    public String getBillOfSaleNumber() {
        return billOfSaleNumber;
    }

    public void setBillOfSaleNumber(String billOfSaleNumber) {
        this.billOfSaleNumber = billOfSaleNumber;
    }

    public Date getBillOfSaleDate() {
        return billOfSaleDate;
    }

    public void setBillOfSaleDate(Date billOfSaleDate) {
        this.billOfSaleDate = billOfSaleDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BillOfSaleVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unit='" + unit + '\'' +
                ", money='" + money + '\'' +
                ", remark='" + remark + '\'' +
                ", invoiceTitleId='" + invoiceTitleId + '\'' +
                ", billOfSaleNumber='" + billOfSaleNumber + '\'' +
                ", billOfSaleDate=" + billOfSaleDate +
                ", createTime=" + createTime +
                '}';
    }

}
