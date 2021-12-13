package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 客户订单表
 *
 * @author lqp
 */
public class CustomerOrderDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_customer的主键（客户）
     */
    private String customerId;

    /**
     * t_originalproduct的主键（成品）
     */
    private String originalProductId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 订单数量（客户需求的数量）
     */
    private String orderAmount;

    /**
     * 投产数量（实际生产的数量）
     */
    private String productAmount;

    /**
     * 客户订单号（最好不能重复）
     */
    private String orderNumber;

    /**
     * 采购日期
     */
    private Date orderDate;

    /**
     * 交货日期
     */
    private Date deliveryDate;

    /**
     * 计划到账日期
     */
    private Date planArrivalDate;

    /**
     * 实际到账日期
     */
    private Date actualArrivalDate;

    /**
     * 钱是否到账
     */
    private String isArrival;

    /**
     * 业务人员审核标志
     */
    private String checkFlag;

    /**
     * 生产管理人员审核标志
     */
    private String finalCheckFlag;

    /**
     * 是否生成制造流程单
     */
    private String isGeneraManufacture;

    /**
     * 订单状态（生成制造流程单）
     */
    private String orderStatus;

    /**
     * 每单投产数量（拆单后实际投入生产的数量） 每单实际投产数量加起来如果 != prod_amount 则最后一单全部补上
     */
    private String everyOrderAmount;

    /**
     * 每单产品数量（拆单后每单客户需求的数量） order_amount / every_product_amount = 拆单数
     */
    private String everyProductAmount;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
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

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public Date getPlanArrivalDate() {
        return planArrivalDate;
    }

    public void setPlanArrivalDate(Date planArrivalDate) {
        this.planArrivalDate = planArrivalDate;
    }

    public Date getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(Date actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public String getIsArrival() {
        return isArrival;
    }

    public void setIsArrival(String isArrival) {
        this.isArrival = isArrival;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getFinalCheckFlag() {
        return finalCheckFlag;
    }

    public void setFinalCheckFlag(String finalCheckFlag) {
        this.finalCheckFlag = finalCheckFlag;
    }

    public String getIsGeneraManufacture() {
        return isGeneraManufacture;
    }

    public void setIsGeneraManufacture(String isGeneraManufacture) {
        this.isGeneraManufacture = isGeneraManufacture;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getEveryOrderAmount() {
        return everyOrderAmount;
    }

    public void setEveryOrderAmount(String everyOrderAmount) {
        this.everyOrderAmount = everyOrderAmount;
    }

    public String getEveryProductAmount() {
        return everyProductAmount;
    }

    public void setEveryProductAmount(String everyProductAmount) {
        this.everyProductAmount = everyProductAmount;
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
        return "CustomerOrderDO{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", productAmount='" + productAmount + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", planArrivalDate=" + planArrivalDate +
                ", actualArrivalDate=" + actualArrivalDate +
                ", isArrival='" + isArrival + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", finalCheckFlag='" + finalCheckFlag + '\'' +
                ", isGeneraManufacture='" + isGeneraManufacture + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", everyProductAmount='" + everyProductAmount + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
