package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 材料调拨表
 *
 * @author lqp
 */
public class ProductAllocationDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_prodinventory的主键（成品库存）
     */
    private String productInventoryId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * t_warehouse的主键（调拨后的仓库）
     */
    private String warehouseId;

    /**
     * 调拨数量
     */
    private String amount;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 调拨日期
     */
    private Date allocationDate;

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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
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
        return "ProductAllocationDO{" +
                "id='" + id + '\'' +
                ", productInventoryId='" + productInventoryId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", amount='" + amount + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", allocationDate=" + allocationDate +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
