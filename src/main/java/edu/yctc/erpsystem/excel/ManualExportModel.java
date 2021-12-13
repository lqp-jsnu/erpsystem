package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 手动出库表
 *
 * @author smile
 */
public class ManualExportModel extends BaseRowModel {

    @ExcelProperty(value = {"手动出库", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"手动出库", "出库单号"}, index = 1)
    private String exportNumber;

    @ExcelProperty(value = {"手动出库", "供应商编号"}, index = 2)
    private String code;

    @ExcelProperty(value = {"手动出库", "品名／磁棒／尺寸(材质)"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"手动出库", "规格/初R值/电阻线(线径)"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"手动出库", "出库数量"}, index = 5)
    private String amount;

    @ExcelProperty(value = {"手动出库", "出库仓库"}, index = 6)
    private String houseName;

    @ExcelProperty(value = {"手动出库", "出库日期"}, index = 7)
    private String exportDate;

    @ExcelProperty(value = {"手动出库", "出库者"}, index = 8)
    private String user;

    @ExcelProperty(value = {"手动出库", "审核者"}, index = 9)
    private String checker;

    @ExcelProperty(value = {"手动出库", "录入时间"}, index = 10)
    private String createTime;

    @ExcelProperty(value = {"手动出库", "状态"}, index = 11)
    private String checkFlag;

    @ExcelProperty(value = {"手动出库", "备注"}, index = 12)
    private String remark;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(String exportNumber) {
        this.exportNumber = exportNumber;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ManualExportModel{" +
                "index='" + index + '\'' +
                ", exportNumber='" + exportNumber + '\'' +
                ", code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", houseName='" + houseName + '\'' +
                ", exportDate='" + exportDate + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", createTime='" + createTime + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
