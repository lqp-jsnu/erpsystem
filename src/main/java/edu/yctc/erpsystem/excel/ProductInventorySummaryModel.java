package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 成品库存汇总excel实体类
 *
 * @author qiang
 */
public class ProductInventorySummaryModel extends BaseRowModel {

    @ExcelProperty(value = {"成品库存汇总", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"成品库存汇总", "客户代码"}, index = 1)
    private String code;

    @ExcelProperty(value = {"成品库存汇总", "客户料号"}, index = 2)
    private String productNumber;

    @ExcelProperty(value = {"成品库存汇总", "品名"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"成品库存汇总", "规格"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"成品库存汇总", "单位"}, index = 5)
    private String unit;

    @ExcelProperty(value = {"成品库存汇总", "入库数量"}, index = 6)
    private String storageAmount;

    @ExcelProperty(value = {"成品库存汇总", "入库时间"}, index = 7)
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

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }

    @Override
    public String toString() {
        return "ProdInventorySummaryModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", storageDate='" + storageDate + '\'' +
                '}';
    }

}
