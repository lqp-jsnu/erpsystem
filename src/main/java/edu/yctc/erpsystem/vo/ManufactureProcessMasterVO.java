package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 制造流程单视图
 *
 * @author smile
 */
public class ManufactureProcessMasterVO {

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
     * 单位名称
     */
    private String name;

    /**
     * 客户料号
     */
    private String productNumber;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 订购数量
     */
    private String everyAmount;

    /**
     * 投产数量
     */
    private String productAmount;

    /**
     * 拆单数
     */
    private String splitNumber;

    /**
     * 入库单数
     */
    private String inHouseNumber;

    /**
     * 采购日期
     */
    private Date orderDate;

    /**
     * 交货日期
     */
    private Date deliveryDate;

    /**
     * 生成者
     */
    private String user;

    /**
     * 录入日期
     */
    private Date createTime;

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

    public String getEveryAmount() {
        return everyAmount;
    }

    public void setEveryAmount(String everyAmount) {
        this.everyAmount = everyAmount;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(String splitNumber) {
        this.splitNumber = splitNumber;
    }

    public String getInHouseNumber() {
        return inHouseNumber;
    }

    public void setInHouseNumber(String inHouseNumber) {
        this.inHouseNumber = inHouseNumber;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ManufactureProcessMasterVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", everyAmount='" + everyAmount + '\'' +
                ", productAmount='" + productAmount + '\'' +
                ", splitNumber='" + splitNumber + '\'' +
                ", inHouseNumber='" + inHouseNumber + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", user='" + user + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
