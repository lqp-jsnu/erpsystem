package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 生产补单表
 *
 * @author lqp
 */
public class IssueOrderDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_customerorder的主键（待补的客户订单）
     */
    private String customerOrderId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 补单数量
     */
    private String issueAmount;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 是否生成制造流程单
     */
    private String isGeneraManufacture;

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

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
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

    public String getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(String issueAmount) {
        this.issueAmount = issueAmount;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getIsGeneraManufacture() {
        return isGeneraManufacture;
    }

    public void setIsGeneraManufacture(String isGeneraManufacture) {
        this.isGeneraManufacture = isGeneraManufacture;
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
        return "IssueOrderDO{" +
                "id='" + id + '\'' +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", issueAmount='" + issueAmount + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", isGeneraManufacture='" + isGeneraManufacture + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
