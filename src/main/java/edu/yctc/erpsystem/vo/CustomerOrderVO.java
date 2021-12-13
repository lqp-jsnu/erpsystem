package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 订单查询
 *
 * @author smile
 */
public class CustomerOrderVO {

    /**
     * CustomerOrder的主键
     */
    private String id;

    /**
     * 客户订单号
     */
    private String orderNumber;

    /**
     * 客户代号
     */
    private String code;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 品名
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 客户料号，不可以重复
     */
    private String productNumber;

    /**
     * 订单数量
     */
    private String orderAmount;

    /**
     * 投产数量
     */
    private String productAmount;

    /**
     * 每单投产数量
     */
    private String everyOrderAmount;

    /**
     * 每单数量
     */
    private String everyProductAmount;

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
     * 是否到账
     */
    private String isArrival;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     * 是否生成制造流程单
     */
    private String isGeneraManufacture;

    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 生产管理人员审核标志
     */
    private String finalCheckFlag;

    /**
     * '品名／磁棒／尺寸(材质)'
     */
    private String materialItemName;

    /**
     * '规格/初值/电阻线(线径)'
     */
    private String materialSpec;

    /**
     * 录入者
     */
    private String user;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 成品原始id
     */
    private String originalProductId;

    /**
     * 材料id
     */
    private String materialInfoMasterId;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    public String getIsGeneraManufacture() {
        return isGeneraManufacture;
    }

    public void setIsGeneraManufacture(String isGeneraManufacture) {
        this.isGeneraManufacture = isGeneraManufacture;
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

    public String getFinalCheckFlag() {
        return finalCheckFlag;
    }

    public void setFinalCheckFlag(String finalCheckFlag) {
        this.finalCheckFlag = finalCheckFlag;
    }

    public String getMaterialItemName() {
        return materialItemName;
    }

    public void setMaterialItemName(String materialItemName) {
        this.materialItemName = materialItemName;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    @Override
    public String toString() {
        return "CustomerOrderVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", productAmount='" + productAmount + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", everyProductAmount='" + everyProductAmount + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", planArrivalDate=" + planArrivalDate +
                ", actualArrivalDate=" + actualArrivalDate +
                ", isArrival='" + isArrival + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", isGeneraManufacture='" + isGeneraManufacture + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", finalCheckFlag='" + finalCheckFlag + '\'' +
                ", materialItemName='" + materialItemName + '\'' +
                ", materialSpec='" + materialSpec + '\'' +
                ", user='" + user + '\'' +
                ", customerId='" + customerId + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                '}';
    }

}
