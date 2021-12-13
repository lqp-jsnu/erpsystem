package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 质量抽检视图
 *
 * @author zzy
 */
public class QualitySamplingVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 客户代码
     */
    private String code;

    /**
     * 工作传票号
     */
    private String jobTicketNumber;

    /**
     * 成品料号
     */
    private String productNumber;

    /**
     * 品名
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 包装数量
     */
    private String packageNumber;

    /**
     * 抽样数
     */
    private String sampleNumber;

    /**
     * AQL 接收质量限
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
     * 状态
     */
    private String checkFlag;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * manufactureProcessSlaveId
     */
    private String manufactureProcessSlaveId;

    /**
     * 检验员
     */
    private String checkOperator;

    /**
     * 包装员
     */
    private String packageOperator;

    /**
     * 录入者
     */
    private String enter;

    /**
     * 审核者
     */
    private String checker;

    /**
     * syuser的主键（检验员）
     */
    private String checkOperatorId;

    /**
     * syuser的主键（包装员）
     */
    private String packageOperatorId;

    /**
     * syuser的主键（录入员）
     */
    private String userId;

    /**
     * syuser的主键（审核者）
     */
    private String checkerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
    }

    public String getPackageOperator() {
        return packageOperator;
    }

    public void setPackageOperator(String packageOperator) {
        this.packageOperator = packageOperator;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckOperatorId() {
        return checkOperatorId;
    }

    public void setCheckOperatorId(String checkOperatorId) {
        this.checkOperatorId = checkOperatorId;
    }

    public String getPackageOperatorId() {
        return packageOperatorId;
    }

    public void setPackageOperatorId(String packageOperatorId) {
        this.packageOperatorId = packageOperatorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    @Override
    public String toString() {
        return "QualitySamplingVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", packageNumber='" + packageNumber + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", aql='" + aql + '\'' +
                ", passNumber='" + passNumber + '\'' +
                ", failNumber='" + failNumber + '\'' +
                ", sampleResult='" + sampleResult + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", checkOperator='" + checkOperator + '\'' +
                ", packageOperator='" + packageOperator + '\'' +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", checkOperatorId='" + checkOperatorId + '\'' +
                ", packageOperatorId='" + packageOperatorId + '\'' +
                ", userId='" + userId + '\'' +
                ", checkerId='" + checkerId + '\'' +
                '}';
    }

}
