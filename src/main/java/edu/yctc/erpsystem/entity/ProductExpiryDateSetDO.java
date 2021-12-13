package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 成品有效期表
 *
 * @author lqp
 */
public class ProductExpiryDateSetDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_originalproduct的主键（成品信息）
     */
    private String originalProductId;

    /**
     * 保质期
     */
    private Integer expiryDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    public Integer getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Integer expiryDate) {
        this.expiryDate = expiryDate;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProductExpiryDateSetDO{" +
                "id='" + id + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", expiryDate=" + expiryDate +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
