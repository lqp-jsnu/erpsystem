package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 客户订单表
 * @author smile
 */
public class CustomerOrderModel extends BaseRowModel {

    @ExcelProperty(value = {"客户订单信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"客户订单信息", "订单号"}, index = 1)
    private String orderNumber;

    @ExcelProperty(value = {"客户订单信息", "客户代码"}, index = 2)
    private String code;

    @ExcelProperty(value = {"客户订单信息", "客户名称"}, index = 3)
    private String name;

    @ExcelProperty(value = {"客户订单信息", "品名"}, index = 4)
    private String itemName;

    @ExcelProperty(value = {"客户订单信息", "规格"}, index = 5)
    private String spec;

    @ExcelProperty(value = {"客户订单信息", "订单数量"}, index = 6)
    private String orderAmount;

    @ExcelProperty(value = {"客户订单信息", "单位"}, index = 7)
    private String unit;

    @ExcelProperty(value = {"客户订单信息", "采购日期"}, index = 8)
    private String orderDate;

    @ExcelProperty(value = {"客户订单信息", "交货日期"}, index = 9)
    private String deliveryDate;

    @ExcelProperty(value = {"客户订单信息", "计划到账日期"}, index = 10)
    private String planArrivalDate;

    @ExcelProperty(value = {"客户订单信息", "实际到账日期"}, index = 11)
    private String actualArrivalDate;

    @ExcelProperty(value = {"客户订单信息", "是否到账"}, index = 12)
    private String isArrival;

    @ExcelProperty(value = {"客户订单信息", "备注"}, index = 13)
    private String remark;

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

    public String getPlanArrivalDate() {
        return planArrivalDate;
    }

    public void setPlanArrivalDate(String planArrivalDate) {
        this.planArrivalDate = planArrivalDate;
    }

    public String getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(String actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public String getIsArrival() {
        return isArrival;
    }

    public void setIsArrival(String isArrival) {
        this.isArrival = isArrival;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CustomerOrderModel{" +
                "index='" + index + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", planArrivalDate='" + planArrivalDate + '\'' +
                ", actualArrivalDate='" + actualArrivalDate + '\'' +
                ", isArrival='" + isArrival + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
