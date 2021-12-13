package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 排程表
 *
 * @author smile
 */
public class ScheduleVO {
    /**
     * 主键
     */
    private String id;

    /**
     * t_customerorder的主键
     */
    private String customerOrderId;

    /**
     * t_originalproduct的主键
     */
    private String originalProductId;

    /**
     * t_customer的主键
     */
    private String customerId;

    /**
     * t_materialexport的主键
     */
    private String materialExportId;

    /**
     * t_manufactprocessslave的主键
     */
    private String manufactureProcessSlaveId;

    /**
     * 客户代号
     */
    private String code;

    /**
     * 工作传票号码
     */
    private String jobTicketNumber;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 每单数量
     */
    private String everyOrderAmount;

    /**
     * 交付日期
     */
    private Date deliveryDate;

    /**
     * 特殊要求
     */
    private String remark;

    /**
     * 切
     */
    private String qie;

    /**
     * 焊
     */
    private String han;

    /**
     * 绕
     */
    private String rao;

    /**
     * 涂
     */
    private String tu;

    /**
     * 折
     */
    private String chai;

    /**
     * 是否完成
     */
    private String isFinish;

    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 库存数量
     */
    private String leftAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMaterialExportId() {
        return materialExportId;
    }

    public void setMaterialExportId(String materialExportId) {
        this.materialExportId = materialExportId;
    }

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
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

    public void setEveryOrderAmount(String everyOrderAmount) {
        this.everyOrderAmount = everyOrderAmount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    @Override
    public String toString() {
        return "ScheduleVO{" +
                "id='" + id + '\'' +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", materialExportId='" + materialExportId + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", remark='" + remark + '\'' +
                ", qie='" + qie + '\'' +
                ", han='" + han + '\'' +
                ", rao='" + rao + '\'' +
                ", tu='" + tu + '\'' +
                ", chai='" + chai + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", createTime=" + createTime +
                ", leftAmount='" + leftAmount + '\'' +
                '}';
    }

}
