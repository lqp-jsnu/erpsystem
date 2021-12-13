package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 质量抽检表
 *
 * @author xiaotao
 */
public class QualitySamplingDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_manufactprocessslave的主键（制作流程单）
     */
    private String manufactureProcessSlaveId;

    /**
     * syuser的主键（包装员）
     */
    private String packageOperator;

    /**
     * syuser的主键（检验员）
     */
    private String checkOperator;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 包装数量
     */
    private String packageNumber;

    /**
     * 抽样数
     */
    private String sampleNumber;

    /**
     * aql（接收质量限）
     */
    private String aql;

    /**
     * 合格数
     */
    private String passNumber;

    /**
     * 不良数
     */
    private String failNumber;

    /**
     * 检验结果
     */
    private String sampleResult;

    /**
     * 审核标志,0未审核 1审核通过 2审核未通过
     */
    private String checkFlag;

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

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
    }

    public String getPackageOperator() {
        return packageOperator;
    }

    public void setPackageOperator(String packageOperator) {
        this.packageOperator = packageOperator;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getAql() {
        return aql;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public String getFailNumber() {
        return failNumber;
    }

    public void setFailNumber(String failNumber) {
        this.failNumber = failNumber;
    }

    public String getSampleResult() {
        return sampleResult;
    }

    public void setSampleResult(String sampleResult) {
        this.sampleResult = sampleResult;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QualitySamplingDO{" +
                "id='" + id + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", packageOperator='" + packageOperator + '\'' +
                ", checkOperator='" + checkOperator + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", packageNumber='" + packageNumber + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", aql='" + aql + '\'' +
                ", passNumber='" + passNumber + '\'' +
                ", failNumber='" + failNumber + '\'' +
                ", sampleResult='" + sampleResult + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
