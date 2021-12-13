package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 材料出库明细excel实体类
 *
 * @author wjd
 */
public class MaterialExportDetailModel extends BaseRowModel {

    @ExcelProperty(value = {"原材料出库信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"原材料出库信息", "制造流程单号"}, index = 1)
    private String jobTicketNumber;

    @ExcelProperty(value = {"原材料出库信息", "出库单号"}, index = 2)
    private String exportNumber;

    @ExcelProperty(value = {"原材料出库信息", "客户订单号"}, index = 3)
    private String orderNumber;

    @ExcelProperty(value = {"原材料出库信息", "供应商编码"}, index = 4)
    private String code;

    @ExcelProperty(value = {"原材料出库信息", "品名／磁棒／尺寸(材质)"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"原材料出库信息", "规格/初R值/电阻线(线径)"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"原材料出库信息", "发料数量"}, index = 7)
    private String issueNumber;

    @ExcelProperty(value = {"原材料出库信息", "单位"}, index = 8)
    private String unit;

    @ExcelProperty(value = {"原材料出库信息", "单价"}, index = 9)
    private String unitPrice;

    @ExcelProperty(value = {"原材料出库信息", "出库日期"}, index = 10)
    private String exportDate;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(String exportNumber) {
        this.exportNumber = exportNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
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

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    @Override
    public String toString() {
        return "MaterialExportDetailModel{" +
                "index='" + index + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", exportNumber='" + exportNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", exportDate='" + exportDate + '\'' +
                '}';
    }

}
