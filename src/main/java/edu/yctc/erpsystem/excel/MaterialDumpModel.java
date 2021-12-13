package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 原材料报废excel实体类
 *
 * @author xiaotao
 */
public class MaterialDumpModel extends BaseRowModel {

    @ExcelProperty(value = {"原材料报废信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"原材料报废信息", "供应商编号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"原材料报废信息", "供应商名称"}, index = 2)
    private String name;

    @ExcelProperty(value = {"原材料报废信息", "品名／磁棒／尺寸(材质)"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"原材料报废信息", "规格/初R值/电阻线(线径)"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"原材料报废信息", "报废数量"}, index = 5)
    private String amount;

    @ExcelProperty(value = {"原材料报废信息", "报废日期"}, index = 6)
    private String date;

    @ExcelProperty(value = {"原材料报废信息", "报废原因"}, index = 7)
    private String reason;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "MaterialDumpModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

}
