package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 原材料采购导入excel实体类
 *
 * @author wjd
 *
 */
public class MaterialPurchaseImportModel extends BaseRowModel {

    @ExcelProperty(value = {"原材料采购单", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"原材料采购单", "供应商代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"原材料采购单", "品名／磁棒／尺寸(材质)"}, index = 2)
    private String itemName;

    @ExcelProperty(value = {"原材料采购单", "规格/初R值/电阻线(线径)"}, index = 3)
    private String spec;

    @ExcelProperty(value = {"原材料采购单", "采购数量"}, index = 4)
    private String amount;

    @ExcelProperty(value = {"原材料采购单", "单价"}, index = 5)
    private String unitPrice;

    @ExcelProperty(value = {"原材料采购单", "单位"}, index = 6)
    private String unit;

    @ExcelProperty(value = {"原材料采购单", "希望交期"}, index = 7)
    private String hopeDeliveryDate;

    @ExcelProperty(value = {"原材料采购单", "备注"}, index = 8)
    private String remark;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getHopeDeliveryDate() {
        return hopeDeliveryDate;
    }

    public void setHopeDeliveryDate(String hopeDeliveryDate) {
        this.hopeDeliveryDate = hopeDeliveryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MaterialPurchaseImportModel{" +
                "index='" + index + '\'' +
                ", itemName='" + itemName + '\'' +
                ", code='" + code + '\'' +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unit='" + unit + '\'' +
                ", hopeDeliveryDate='" + hopeDeliveryDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
