package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

/**
 * 成品库存excel实体
 *
 * @author zzy
 */
public class ProductInventoryRepertoryModel extends BaseRowModel {

    @ExcelProperty(value = {"成品库存信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"成品库存信息", "客户代码"}, index = 1)
    private  String code;

    @ExcelProperty(value = {"成品库存信息", "工作传票号"}, index = 2)
    private String jobTicketNumber;

    @ExcelProperty(value = {"成品库存信息", "订单号码"}, index = 3)
    private String orderNumber;

    @ExcelProperty(value = {"成品库存信息", "客户料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"成品库存信息", "单位"}, index = 5)
    private  String unit;

    @ExcelProperty(value = {"成品库存信息", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"成品库存信息", "库存数量"}, index = 7)
    private  String storageAmount;

    @ExcelProperty(value = {"成品库存信息", "单价"}, index = 8)
    private  String unitPrice;

    @ExcelProperty(value = {"成品库存信息", "金额"}, index = 9)
    private  String money;

    @ExcelProperty(value = {"成品库存信息", "存放仓库"}, index = 10)
    private  String houseName;

    @ExcelProperty(value = {"成品库存信息", "入库日期"}, index = 11)
    private String storageDate;

    @ExcelProperty(value = {"成品库存信息", "是否调拨"}, index = 12)
    private  String isAllocation;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }

    public String getIsAllocation() {
        return isAllocation;
    }

    public void setIsAllocation(String isAllocation) {
        this.isAllocation = isAllocation;
    }

    @Override
    public String toString() {
        return "ProductInventoryRepertoryModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", spec='" + spec + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                ", houseName='" + houseName + '\'' +
                ", storageDate='" + storageDate + '\'' +
                ", isAllocation='" + isAllocation + '\'' +
                '}';
    }

}
