package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 成品原始信息表
 *
 * @author smile
 */
public class OriginalProductModel extends BaseRowModel {
    @ExcelProperty(value = {"成品原始信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"成品原始信息", "客户代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"成品原始信息", "品名"}, index = 2)
    private String itemName;

    @ExcelProperty(value = {"成品原始信息", "规格"}, index = 3)
    private String spec;

    @ExcelProperty(value = {"成品原始信息", "品名/磁棒/尺寸(材质)"}, index = 4)
    private String masterItemName;

    @ExcelProperty(value = {"成品原始信息", "规格/初R值/电阻线(线径)"}, index = 5)
    private String masterSpec;

    @ExcelProperty(value = {"成品原始信息", "客户料号"}, index = 6)
    private String productNumber;

    @ExcelProperty(value = {"成品原始信息", "单位"}, index = 7)
    private String unit;

    @ExcelProperty(value = {"成品原始信息", "单价"}, index = 8)
    private String unitPrice;

    @ExcelProperty(value = {"成品原始信息", "标签"}, index = 9)
    private String label;

    @ExcelProperty(value = {"成品原始信息", "色码"}, index = 10)
    private String colorCode;

    @ExcelProperty(value = {"成品原始信息", "特殊要求"}, index = 11)
    private String remark;

    @ExcelProperty(value = {"成品原始信息", "图纸Url"}, index = 12)
    private String drawingUrl;

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

    public String getMasterItemName() {
        return masterItemName;
    }

    public void setMasterItemName(String masterItemName) {
        this.masterItemName = masterItemName;
    }

    public String getMasterSpec() {
        return masterSpec;
    }

    public void setMasterSpec(String masterSpec) {
        this.masterSpec = masterSpec;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDrawingUrl() {
        return drawingUrl;
    }

    public void setDrawingUrl(String drawingUrl) {
        this.drawingUrl = drawingUrl;
    }

    @Override
    public String toString() {
        return "OriginalProductModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", masterItemName='" + masterItemName + '\'' +
                ", masterSpec='" + masterSpec + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", label='" + label + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", remark='" + remark + '\'' +
                ", drawingUrl='" + drawingUrl + '\'' +
                '}';
    }
}
