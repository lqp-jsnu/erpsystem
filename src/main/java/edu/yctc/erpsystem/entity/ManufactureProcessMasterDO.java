package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 制造流程单主表
 *
 * @author xiaotao
 */
public class ManufactureProcessMasterDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_customerorder的主键（客户订单）
     */
    private String customerOrderId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

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

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ManufactureProcessMasterDO{" +
                "id='" + id + '\'' +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", user='" + user + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
