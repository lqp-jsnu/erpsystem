package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 生产补单表
 *
 * @author smile
 */
public class IssueOrderVO {

    /**
     * IssueOrder的主键
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
     * 单位名称
     */
    private String name;

    /**
     * 客户料号，不可以重复
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
     * 客订数量
     */
    private String orderAmount;

    /**
     * 单位
     */
    private String unit;

    /**
     * 补单数量
     */
    private String issueAmount;

    /**
     * 采购日期
     */
    private Date orderDate;

    /**
     * 交货日期
     */
    private Date deliveryDate;

    /**
     * 录入者
     */
    private String user;

    /**
     * 审核者
     */
    private String checker;

    /**
     *状态
     */
    private String checkFlag;

    /**
     * 是否生成制造流程单
     */
    private String isGenerateManufacture;
    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 成品原始id
     */
    private String originalProductId;

    /**
     * 客户订单id
     */
    private String customerOrderId;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 录入者
     */
    private String userId;

    /**
     * 制造流程单录入者
     */
    private String manufactureProcessSlaveUserId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(String issueAmount) {
        this.issueAmount = issueAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getIsGenerateManufacture() {
        return isGenerateManufacture;
    }

    public void setIsGenerateManufacture(String isGenerateManufacture) {
        this.isGenerateManufacture = isGenerateManufacture;
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

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManufactureProcessSlaveUserId() {
        return manufactureProcessSlaveUserId;
    }

    public void setManufactureProcessSlaveUserId(String manufactureProcessSlaveUserId) {
        this.manufactureProcessSlaveUserId = manufactureProcessSlaveUserId;
    }

    @Override
    public String toString() {
        return "IssueOrderVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", issueAmount='" + issueAmount + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", isGenerateManufacture='" + isGenerateManufacture + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", userId='" + userId + '\'' +
                ", manufactureProcessSlaveUserId='" + manufactureProcessSlaveUserId + '\'' +
                '}';
    }

}
