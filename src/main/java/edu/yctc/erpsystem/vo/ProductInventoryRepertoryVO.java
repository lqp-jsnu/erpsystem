package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 成品库存视图
 *
 * @author zzy
 */
public class ProductInventoryRepertoryVO {

    /**
     * 主键
     */
    String id;

    /**
     * t_prodstorage的主键（主表）
     */
    private String productStorageId;

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
     * 客户料号
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
     * 单位
     */
    private String unit;

    /**
     * 库存数量
     */
    private String storageAmount;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 金额
     */
    private String money;

    /**
     * 存放仓库
     */
    private String houseName;

    /**
     * 是否调拨
     */
    private String isAllocation;

    /**
     * 入库日期
     */
    private Date storageDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductStorageId() {
        return productStorageId;
    }

    public void setProductStorageId(String productStorageId) {
        this.productStorageId = productStorageId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getIsAllocation() {
        return isAllocation;
    }

    public void setIsAllocation(String isAllocation) {
        this.isAllocation = isAllocation;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    @Override
    public String toString() {
        return "ProductInventoryRepertoryVO{" +
                "id='" + id + '\'' +
                ", productStorageId='" + productStorageId + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                ", houseName='" + houseName + '\'' +
                ", isAllocation='" + isAllocation + '\'' +
                ", storageDate=" + storageDate +
                '}';
    }

}

