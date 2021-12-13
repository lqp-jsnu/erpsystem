package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 生成库存excel实体类
 *
 * @author smile
 */
public class MaterialInventoryMasterModel extends BaseRowModel {

    @ExcelProperty(value = {"原材料库存信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"原材料库存信息", "客户代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"原材料库存信息", "品名／磁棒／尺寸(材质)"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"原材料库存信息", "规格/初R值/电阻线(线径)"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"原材料库存信息", "数量"}, index = 11)
    private String leftAmount;

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

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    @Override
    public String toString() {
        return "MaterialInventoryMasterModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                '}';
    }
}
