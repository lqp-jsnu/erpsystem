package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 出货单历史excel实体类
 *
 * @author zzy
 */
public class BillOfSaleHistoryModel extends BaseRowModel {

    @ExcelProperty(value = {"出货单历史信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"出货单历史信息", "出库单号"}, index = 1)
    private String billOfSaleNumber;

    @ExcelProperty(value = {"出货单历史信息", "客户订单号"}, index = 2)
    private String orderNumber;

    @ExcelProperty(value = {"出货单历史信息", "客户代码"}, index = 3)
    private String code;

    @ExcelProperty(value = {"出货单历史信息", "客户料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"出货单历史信息", "品名"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"出货单历史信息", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"出货单历史信息", "单位"}, index = 7)
    private String unit;

    @ExcelProperty(value = {"出货单历史信息", "单价"}, index = 8)
    private String unitPrice;

    @ExcelProperty(value = {"出货单历史信息", "出库数量"}, index = 9)
    private String exportAmount;

    @ExcelProperty(value = {"出货单历史信息", "金额"}, index = 10)
    private String money;

    @ExcelProperty(value = {"出货单历史信息", "出库日期"}, index = 11)
    private String billOfSaleDate;

    @ExcelProperty(value = {"出货单历史信息", "备注"}, index = 12)
    private String remark;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBillOfSaleNumber() {
        return billOfSaleNumber;
    }

    public void setBillOfSaleNumber(String billOfSaleNumber) {
        this.billOfSaleNumber = billOfSaleNumber;
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

    public String getExportAmount() {
        return exportAmount;
    }

    public void setExportAmount(String exportAmount) {
        this.exportAmount = exportAmount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBillOfSaleDate() {
        return billOfSaleDate;
    }

    public void setBillOfSaleDate(String billOfSaleDate) {
        this.billOfSaleDate = billOfSaleDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BillOfSaleHistoryModel{" +
                "index='" + index + '\'' +
                ", billOfSaleNumber='" + billOfSaleNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", money='" + money + '\'' +
                ", billOfSaleDate='" + billOfSaleDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
