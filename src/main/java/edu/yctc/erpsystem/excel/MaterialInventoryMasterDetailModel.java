package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 生成库存excel实体类
 *
 * @author wjd
 */
public class MaterialInventoryMasterDetailModel extends BaseRowModel {

    @ExcelProperty(value = {"原材料库存详细信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"原材料库存详细信息", "供应商代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"原材料库存详细信息", "采购单号"}, index = 2)
    private String orderNumber;

    @ExcelProperty(value = {"原材料库存详细信息", "品名／磁棒／尺寸(材质)"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"原材料库存详细信息", "规格/初R值/电阻线(线径)"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"原材料库存详细信息", "来料日期"}, index = 5)
    private String inDate;

    @ExcelProperty(value = {"原材料库存详细信息", "入库日期"}, index = 6)
    private String inStorageDate;

    @ExcelProperty(value = {"原材料库存详细信息", "库存数量"}, index = 7)
    private String leftAmount;

    @ExcelProperty(value = {"原材料库存详细信息", "入库数量"}, index = 8)
    private String inAmount;

    @ExcelProperty(value = {"原材料库存详细信息", "单价"}, index = 9)
    private String unitPrice;

    @ExcelProperty(value = {"原材料库存详细信息", "单位"}, index = 10)
    private String unit;

    @ExcelProperty(value = {"原材料库存详细信息", "金额"}, index = 11)
    private String jine;

    @ExcelProperty(value = {"原材料库存详细信息", "存放仓库"}, index = 12)
    private String houseName;

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

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

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(String inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
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

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Override
    public String toString() {
        return "MaterialInventoryMasterDetailModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", inDate='" + inDate + '\'' +
                ", inStorageDate='" + inStorageDate + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unit='" + unit + '\'' +
                ", jine='" + jine + '\'' +
                ", houseName='" + houseName + '\'' +
                '}';
    }

}
