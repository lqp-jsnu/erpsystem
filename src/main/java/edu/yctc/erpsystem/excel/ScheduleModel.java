package edu.yctc.erpsystem.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 未完结排程excel实体类
 *
 * @author smile
 */
public class ScheduleModel extends BaseRowModel {
    @ExcelProperty(value = {"未完结排程信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"未完结排程信息", "客户代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"未完结排程信息", "工作传票号码"}, index = 2)
    private String jobTicketNumber;

    @ExcelProperty(value = {"未完结排程信息", "品名"}, index = 3)
    private String itemName;

    @ExcelProperty(value = {"未完结排程信息", "规格"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"未完结排程信息", "每单数量"}, index = 5)
    private String everyOrderAmount;

    @ExcelProperty(value = {"未完结排程信息", "交付日期"}, index = 6)
    private String deliveryDate;

    @ExcelProperty(value = {"未完结排程信息", "特殊要求"}, index = 7)
    private String remark;

    @ExcelProperty(value = {"未完结排程信息", "切—成"}, index = 8)
    private String qie;

    @ExcelProperty(value = {"未完结排程信息", "焊—热"}, index = 9)
    private String han;

    @ExcelProperty(value = {"未完结排程信息", "绕—整"}, index = 10)
    private String rao;

    @ExcelProperty(value = {"未完结排程信息", "涂—镀"}, index = 11)
    private String tu;

    @ExcelProperty(value = {"未完结排程信息", "拆—包"}, index = 12)
    private String chai;

    @ExcelProperty(value = {"未完结排程信息", "是否完成"}, index = 13)
    private String isFinish;

    @ExcelProperty(value = {"未完结排程信息", "录入日期"}, index = 14)
    private String createTime;

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

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
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

    public String getEveryOrderAmount() {
        return everyOrderAmount;
    }

    public void setEveryOrderAmount(String everyAmount) {
        this.everyOrderAmount = everyOrderAmount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQie() {
        return qie;
    }

    public void setQie(String qie) {
        this.qie = qie;
    }

    public String getHan() {
        return han;
    }

    public void setHan(String han) {
        this.han = han;
    }

    public String getRao() {
        return rao;
    }

    public void setRao(String rao) {
        this.rao = rao;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getChai() {
        return chai;
    }

    public void setChai(String chai) {
        this.chai = chai;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScheduleModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", remark='" + remark + '\'' +
                ", qie='" + qie + '\'' +
                ", han='" + han + '\'' +
                ", rao='" + rao + '\'' +
                ", tu='" + tu + '\'' +
                ", chai='" + chai + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
