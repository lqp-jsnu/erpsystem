package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 料品主信息excel实体
 *
 * @author xcg
 */
public class MaterialInfoMasterModel extends BaseRowModel {

    @ExcelProperty(value = {"料品主信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"料品主信息", "品名／磁棒／尺寸(材质)"}, index = 1)
    private String itemName;

    @ExcelProperty(value = {"料品主信息", "规格/初R值/电阻线(线径)"}, index = 2)
    private String spec;

    @ExcelProperty(value = {"料品主信息", "单位"}, index = 3)
    private String unit;

    @ExcelProperty(value = {"料品主信息", "备注"}, index = 4)
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MaterialInfoMasterModel{" +
                "index='" + index + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
