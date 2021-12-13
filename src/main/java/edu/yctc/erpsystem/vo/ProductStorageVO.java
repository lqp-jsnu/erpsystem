package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 成品入库视图
 *
 * @author zzy
 */
public class ProductStorageVO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_warehouse的主键（成品存放仓库）
     */
    private String wareHouseId;

    /**
     * t_manufactprocessslave的主键（制作流程单）
     */
    private String manufactureProcessSlaveId;

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
     * 是否有零品入库
     */
    private String isZeroProductStorage;

    /**
     * 录入者
     */
    private String enter;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     * 入库日期
     */
    private Date storageDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
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

    public String getIsZeroProductStorage() {
        return isZeroProductStorage;
    }

    public void setIsZeroProductStorage(String isZeroProductStorage) {
        this.isZeroProductStorage = isZeroProductStorage;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
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

    @Override
    public String toString() {
        return "ProductStorageVO{" +
                "id='" + id + '\'' +
                ", wareHouseId='" + wareHouseId + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", houseName='" + houseName + '\'' +
                ", isZeroProductStorage='" + isZeroProductStorage + '\'' +
                ", enter='" + enter + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", storageDate=" + storageDate +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }

}
