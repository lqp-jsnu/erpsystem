package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 成品合格率视图
 *
 * @author zzy
 */
public class QualificationRateOfProductVO {

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     *  客户代号
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
     * 合格率
     */
    private String rate;

    /**
     *  生产数量
     */
    private String productQuantity;

    /**
     *  产品入库数量
     */
    private String storageAmount;

    /**
     * 存放仓库
     */
    private String houseName;

    /**
     * 录入者
     */
    private String enter;

    /**
     * 入库日期
     */
    private Date storageDate;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    @Override
    public String toString() {
        return "QualificationRateOfProductVO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", rate='" + rate + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", houseName='" + houseName + '\'' +
                ", enter='" + enter + '\'' +
                ", storageDate=" + storageDate +
                '}';
    }

}
