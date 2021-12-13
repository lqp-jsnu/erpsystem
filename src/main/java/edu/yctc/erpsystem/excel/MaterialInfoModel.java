package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 料品原始信息excel实体
 *
 * @author xcg
 */
public class MaterialInfoModel extends BaseRowModel {

    @ExcelProperty(value = {"料品原始信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"料品原始信息", "供应商代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"料品原始信息", "供应商名称"}, index = 2)
    private String name;

    @ExcelProperty(value = {"料品原始信息", "品名／磁棒／尺寸(材质)"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"料品原始信息", "规格/初R值/电阻线(线径)"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"料品原始信息", "单价"}, index = 5)
    private String unitPrice;

    @ExcelProperty(value = {"料品原始信息","备注"},index = 6)
    private String remark;

    @ExcelProperty(value = {"料品原始信息", "标签"}, index = 7)
    private String label;

    @ExcelProperty(value = {"料品原始信息", "色码（表面处理）"}, index = 8)
    private String colorCode;

    @ExcelProperty(value = {"料品原始信息","单位"},index = 9)
    private String unit;

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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "MaterialInfoModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", remark='" + remark + '\'' +
                ", label='" + label + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

}
