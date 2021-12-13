package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 零品投产库存表
 *
 * @author lqp
 */
public class ZeroProductInventoryDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_zeroprodstorage的主键
     */
    private String zeroProductStorageId;

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

    public String getZeroProductStorageId() {
        return zeroProductStorageId;
    }

    public void setZeroProductStorageId(String zeroProductStorageId) {
        this.zeroProductStorageId = zeroProductStorageId;
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
        return "ZeroProductInventoryDO{" +
                "id='" + id + '\'' +
                ", zeroProductStorageId='" + zeroProductStorageId + '\'' +
                ", isAllocation='" + isAllocation + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
