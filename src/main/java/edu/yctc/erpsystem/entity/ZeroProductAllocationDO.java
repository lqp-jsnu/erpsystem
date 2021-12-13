package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 零品投产调拨表
 *
 * @author xiaotao
 */
public class ZeroProductAllocationDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_zeroprodstorage的主键（零品入库）
     */
    private String zeroProductStorageId;

    /**
     * t_warehouse的主键（调拨后的仓库）
     */
    private String warehouseId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 调拨数量
     */
    private String amount;

    /**
     * 调拨日期
     */
    private Date allocationDate;

    /**
     * 审核标志
     */
    private String checkFlag;

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

    public String getZeroProductStorageId() {
        return zeroProductStorageId;
    }

    public void setZeroProductStorageId(String zeroProductStorageId) {
        this.zeroProductStorageId = zeroProductStorageId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
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
        return "ZeroProductAllocationDO{" +
                "id='" + id + '\'' +
                ", zeroProductStorageId='" + zeroProductStorageId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", amount='" + amount + '\'' +
                ", allocationDate=" + allocationDate +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
