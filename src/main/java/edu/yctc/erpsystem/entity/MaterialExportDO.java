package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 原材料出库表
 *
 * @author lqp
 */
public class MaterialExportDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialinventorymaster的主键（材料库存）
     */
    private String materialInventoryMasterId;

    /**
     * t_manufactprocessslave的主键（制作流程单  和成品产生联系）
     */
    private String manufactureProcessSlaveId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 出库日期
     */
    private Date date;

    /**
     * 出库单号
     */
    private String number;

    /**
     * 审核标记
     */
    private String checkFlag;

    /**
     * 发料数量
     */
    private String issueNumber;

    /**
     * 备注
     */
    private String remark;

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

    public String getMaterialInventoryMasterId() {
        return materialInventoryMasterId;
    }

    public void setMaterialInventoryMasterId(String materialInventoryMasterId) {
        this.materialInventoryMasterId = materialInventoryMasterId;
    }

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MaterialExportDO{" +
                "id='" + id + '\'' +
                ", materialInventoryMasterId='" + materialInventoryMasterId + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", date=" + date +
                ", number='" + number + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
