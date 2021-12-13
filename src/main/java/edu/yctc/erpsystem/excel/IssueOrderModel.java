package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 生产补单表
 * @author smile
 */
public class IssueOrderModel extends BaseRowModel {

    @ExcelProperty(value = {"生产补单信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"生产补单信息", "订单号"}, index = 1)
    private String orderNumber;

    @ExcelProperty(value = {"生产补单信息", "客户代码"}, index = 2)
    private String code;

    @ExcelProperty(value = {"生产补单信息", "单位名称"}, index = 3)
    private String name;

    @ExcelProperty(value = {"生产补单信息", "客户料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"生产补单信息", "品名"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"生产补单信息", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"生产补单信息", "订单数量"}, index = 7)
    private String orderAmount;

    @ExcelProperty(value = {"生产补单信息", "补单数量"}, index = 8)
    private String issueAmount;

    @ExcelProperty(value = {"生产补单信息", "单位"}, index = 9)
    private String unit;

    @ExcelProperty(value = {"生产补单信息", "采购日期"}, index = 10)
    private String orderDate;

    @ExcelProperty(value = {"生产补单信息", "交货日期"}, index = 11)
    private String deliveryDate;

    @ExcelProperty(value = {"生产补单信息", "录入者"}, index = 12)
    private String user;

    @ExcelProperty(value = {"生产补单信息", "审核者"}, index = 13)
    private String checker;

    @ExcelProperty(value = {"生产补单信息", "状态"}, index = 14)
    private String checkFlag;

    @ExcelProperty(value = {"生产补单信息", "是否生成制造流程单"}, index = 15)
    private String isGenerateManufacture;

    @ExcelProperty(value = {"生产补单信息", "录入日期"}, index = 16)
    private String createTime;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(String issueAmount) {
        this.issueAmount = issueAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getIsGenerateManufacture() {
        return isGenerateManufacture;
    }

    public void setIsGenerateManufacture(String isGenerateManufacture) {
        this.isGenerateManufacture = isGenerateManufacture;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "IssueOrderModel{" +
                "index='" + index + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", issueAmount='" + issueAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", isGenerateManufacture='" + isGenerateManufacture + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
