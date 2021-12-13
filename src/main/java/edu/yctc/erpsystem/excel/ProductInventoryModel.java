package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

/**
 * 成品盘点excel实体
 *
 * @author zzy
 */
public class ProductInventoryModel extends BaseRowModel {

    @ExcelProperty(value = {"成品库存汇总单", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"成品库存汇总单", "客户代码"}, index = 1)
    private String code;

    @ExcelProperty(value = {"成品库存汇总单", "客户料号"}, index = 2)
    private  String productNumber;

    @ExcelProperty(value = {"成品库存汇总单", "规格"}, index = 3)
    private String spec;

    @ExcelProperty(value = {"成品库存汇总单", "库存数量"}, index = 4)
    private String storageAmount;

    @ExcelProperty(value = {"成品库存汇总单", "单位"}, index = 5)
    private String unit;

    @ExcelProperty(value = {"成品库存汇总单", "单价"}, index = 6)
    private String unitPrice;

    @ExcelProperty(value = {"成品库存汇总单", "金额"}, index = 7)
    private String money;

    @ExcelProperty(value = {"成品库存汇总单", "存放仓库"}, index = 8)
    private String houseName;

    @ExcelProperty(value = {"成品库存汇总单", "入库日期"}, index = 9)
    private String storageDate;

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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    @Override
    public String toString() {
        return "ProdInventoryModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", spec='" + spec + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                ", houseName='" + houseName + '\'' +
                ", storageDate='" + storageDate + '\'' +
                '}';
    }

}
