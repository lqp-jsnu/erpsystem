package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 成品库存表
 *
 * @author lqp
 */
public class ProductInventoryDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_prodstorage的主键（主表）
     */
    private String productStorageId;

    /**
     * 是否调拨
     */
    private String isAllocation;

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

    public String getProductStorageId() {
        return productStorageId;
    }

    public void setProductStorageId(String productStorageId) {
        this.productStorageId = productStorageId;
    }

    public String getIsAllocation() {
        return isAllocation;
    }

    public void setIsAllocation(String isAllocation) {
        this.isAllocation = isAllocation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductInventoryDO{" +
                "id='" + id + '\'' +
                ", productStorageId='" + productStorageId + '\'' +
                ", isAllocation='" + isAllocation + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
