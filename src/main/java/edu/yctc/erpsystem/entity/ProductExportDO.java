package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 成品出库表
 *
 * @author lqp
 */
public class ProductExportDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_prodinventory的主键（主表）
     */
    private String productInventoryId;

    /**
     * t_customer的主键（销售者）
     */
    private String customerId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 订单号
     */
    private String number;

    /**
     * 出库日期
     */
    private Date date;

    /**
     * 出库数量
     */
    private String amount;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 客户订单号
     */
    private String orderNumber;

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

    public String getProductInventoryId() {
        return productInventoryId;
    }

    public void setProductInventoryId(String productInventoryId) {
        this.productInventoryId = productInventoryId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
        return "ProductExportDO{" +
                "id='" + id + '\'' +
                ", productInventoryId='" + productInventoryId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", amount='" + amount + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
