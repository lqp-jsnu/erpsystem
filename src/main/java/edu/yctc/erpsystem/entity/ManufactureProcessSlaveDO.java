package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 制造流程单副表
 *
 * @author lqp
 */
public class ManufactureProcessSlaveDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_manufactprocessmaster的主键（主表）
     */
    private String manufactureProcessMasterId;

    /**
     * 每单实际数量（拆单后）
     */
    private String everyAmount;

    /**
     * 是否补单
     */
    private String isIssueOrder;

    /**
     * 是否产品入库
     */
    private String isInHouse;

    /**
     * 工作传票号码
     */
    private String jobTicketNumber;

    /**
     * 是否进入生产制造
     */
    private String isIntoMake;

    /**
     * 每单实际订购数量（客户需求）
     */
    private String everyOrderAmount;

    /**
     * 是否零品入库
     */
    private String isZeroProductInHouse;

    /**
     * 是否生成流程单Excel
     */
    private String isGenerateManufacture;

    /**
     * 是否出库
     */
    private String isMaterialExport;

    /**
     * 出库是否通过审核
     */
    private String isExportCheckPass;

    /**
     * 是否已经入库
     */
    private String isHaveProductStorage;

    /**
     * 是否已经零品入库
     */
    private String isHaveZeroProductStorage;

    /**
     * 创建日期
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufactureProcessMasterId() {
        return manufactureProcessMasterId;
    }

    public void setManufactureProcessMasterId(String manufactureProcessMasterId) {
        this.manufactureProcessMasterId = manufactureProcessMasterId;
    }

    public String getEveryAmount() {
        return everyAmount;
    }

    public void setEveryAmount(String everyAmount) {
        this.everyAmount = everyAmount;
    }

    public String getIsIssueOrder() {
        return isIssueOrder;
    }

    public void setIsIssueOrder(String isIssueOrder) {
        this.isIssueOrder = isIssueOrder;
    }

    public String getIsInHouse() {
        return isInHouse;
    }

    public void setIsInHouse(String isInHouse) {
        this.isInHouse = isInHouse;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getIsIntoMake() {
        return isIntoMake;
    }

    public void setIsIntoMake(String isIntoMake) {
        this.isIntoMake = isIntoMake;
    }

    public String getEveryOrderAmount() {
        return everyOrderAmount;
    }

    public void setEveryOrderAmount(String everyOrderAmount) {
        this.everyOrderAmount = everyOrderAmount;
    }

    public String getIsZeroProductInHouse() {
        return isZeroProductInHouse;
    }

    public void setIsZeroProductInHouse(String isZeroProductInHouse) {
        this.isZeroProductInHouse = isZeroProductInHouse;
    }

    public String getIsGenerateManufacture() {
        return isGenerateManufacture;
    }

    public void setIsGenerateManufacture(String isGenerateManufacture) {
        this.isGenerateManufacture = isGenerateManufacture;
    }

    public String getIsMaterialExport() {
        return isMaterialExport;
    }

    public void setIsMaterialExport(String isMaterialExport) {
        this.isMaterialExport = isMaterialExport;
    }

    public String getIsExportCheckPass() {
        return isExportCheckPass;
    }

    public void setIsExportCheckPass(String isExportCheckPass) {
        this.isExportCheckPass = isExportCheckPass;
    }

    public String getIsHaveProductStorage() {
        return isHaveProductStorage;
    }

    public void setIsHaveProductStorage(String isHaveProductStorage) {
        this.isHaveProductStorage = isHaveProductStorage;
    }

    public String getIsHaveZeroProductStorage() {
        return isHaveZeroProductStorage;
    }

    public void setIsHaveZeroProductStorage(String isHaveZeroProductStorage) {
        this.isHaveZeroProductStorage = isHaveZeroProductStorage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ManufactureProcessSlaveDO{" +
                "id='" + id + '\'' +
                ", manufactureProcessMasterId='" + manufactureProcessMasterId + '\'' +
                ", everyAmount='" + everyAmount + '\'' +
                ", isIssueOrder='" + isIssueOrder + '\'' +
                ", isInHouse='" + isInHouse + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", isIntoMake='" + isIntoMake + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", isZeroProductInHouse='" + isZeroProductInHouse + '\'' +
                ", isGenerateManufacture='" + isGenerateManufacture + '\'' +
                ", isMaterialExport='" + isMaterialExport + '\'' +
                ", isExportCheckPass='" + isExportCheckPass + '\'' +
                ", isHaveProductStorage='" + isHaveProductStorage + '\'' +
                ", isHaveZeroProductStorage='" + isHaveZeroProductStorage + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
