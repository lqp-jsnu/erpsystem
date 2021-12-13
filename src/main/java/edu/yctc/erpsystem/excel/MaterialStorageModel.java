package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 原材料入库excel实体类
 *
 * @author wjd
 */
public class MaterialStorageModel extends BaseRowModel {

    @ExcelProperty(value = {"原材料入库单", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"原材料入库单", "采购单号"}, index = 1)
    private String orderNumber;

    @ExcelProperty(value = {"原材料入库单", "品名／磁棒／尺寸(材质)"}, index = 2)
    private String itemName;

    @ExcelProperty(value = {"原材料入库单", "规格/初R值/电阻线(线径)"}, index = 3)
    private String spec;

    @ExcelProperty(value = {"原材料入库单", "供应商代号"}, index = 4)
    private String code;

    @ExcelProperty(value = {"原材料入库单", "供应商名称"}, index = 5)
    private String name;

    @ExcelProperty(value = {"原材料入库单", "来料日期"}, index = 6)
    private String inStorageDate;

    @ExcelProperty(value = {"原材料入库单", "入库日期"}, index = 7)
    private String inDate;

    @ExcelProperty(value = {"原材料入库单", "采购数量"}, index = 8)
    private String amount;

    @ExcelProperty(value = {"原材料入库单", "入库数量"}, index = 9)
    private String inAmount;

    @ExcelProperty(value = {"原材料入库单", "单位"}, index = 10)
    private String unit;

    @ExcelProperty(value = {"原材料入库单", "单价"}, index = 11)
    private String unitPrice;

    @ExcelProperty(value = {"原材料入库单", "存放仓库"}, index = 12)
    private String houseName;

    @ExcelProperty(value = {"原材料入库单", "状态"}, index = 13)
    private String checkFlag;

    @ExcelProperty(value = {"原材料入库单", "备注"}, index = 14)
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

    public String getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(String inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MaterialStorageModel{" +
                "index='" + index + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", inStorageDate='" + inStorageDate + '\'' +
                ", inDate='" + inDate + '\'' +
                ", amount='" + amount + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", houseName='" + houseName + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
