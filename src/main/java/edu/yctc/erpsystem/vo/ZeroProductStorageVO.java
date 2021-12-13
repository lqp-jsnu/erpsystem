package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 零品入库视图
 *
 * @author zzy
 */
public class ZeroProductStorageVO {

    /**
     *主键
     *
     */
    private String id;

    /**
     * 客户订单号
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
     * 生产数量
     */
    private String productQuantity;

    /**
     * 零品入库数量
     */
    private String zeroStorageAmount;

    /**
     * 零品存放仓库
     */
    private String houseName;

    /**
     * 是否有成品入库
     */
    private String isProductStorage;

    /**
     *录入者
     */
    private String enter;

    /**
     * 审核者
     */
    private String checker;

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

    /**
     * t_manufactProcessSlaveId的主键
     */
    private String manufactureProcessSlaveId;

    /**
     * t_warehouse的主键
     */
    private String zeroWareHouseId;

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

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getZeroStorageAmount() {
        return zeroStorageAmount;
    }

    public void setZeroStorageAmount(String zeroStorageAmount) {
        this.zeroStorageAmount = zeroStorageAmount;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getIsProductStorage() {
        return isProductStorage;
    }

    public void setIsProductStorage(String isProductStorage) {
        this.isProductStorage = isProductStorage;
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

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
    }

    public String getZeroWareHouseId() {
        return zeroWareHouseId;
    }

    public void setZeroWareHouseId(String zeroWareHouseId) {
        this.zeroWareHouseId = zeroWareHouseId;
    }

    @Override
    public String toString() {
        return "ZeroProdStorageVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", zeroStorageAmount='" + zeroStorageAmount + '\'' +
                ", houseName='" + houseName + '\'' +
                ", isProductStorage='" + isProductStorage + '\'' +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", storageDate=" + storageDate +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", zeroWareHouseId='" + zeroWareHouseId + '\'' +
                '}';
    }

}
