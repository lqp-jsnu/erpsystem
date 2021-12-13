package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 成品入库表
 *
 * @author lqp
 */
public class ProductStorageDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_manufactprocessslave的主键（制作流程单）
     */
    private String manufactureProcessSlaveId;

    /**
     * t_warehouse的主键（成品存放仓库）
     */
    private String wareHouseId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（录入者）
     */
    private String checker;

    /**
     * 成品入库数量
     */
    private String storageAmount;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 入库时间
     */
    private Date storageDate;

    /**
     * 生产数量
     */
    private String productQuantity;

    /**
     * 是否零品入库
     */
    private String isZeroProductStorage;

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

    public String getManufactureProcessSlaveId() {
        return manufactureProcessSlaveId;
    }

    public void setManufactureProcessSlaveId(String manufactureProcessSlaveId) {
        this.manufactureProcessSlaveId = manufactureProcessSlaveId;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
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

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
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

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getIsZeroProductStorage() {
        return isZeroProductStorage;
    }

    public void setIsZeroProductStorage(String isZeroProductStorage) {
        this.isZeroProductStorage = isZeroProductStorage;
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
        return "ProductStorageDO{" +
                "id='" + id + '\'' +
                ", manufactureProcessSlaveId='" + manufactureProcessSlaveId + '\'' +
                ", wareHouseId='" + wareHouseId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", storageDate=" + storageDate +
                ", productQuantity='" + productQuantity + '\'' +
                ", isZeroProductStorage='" + isZeroProductStorage + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
