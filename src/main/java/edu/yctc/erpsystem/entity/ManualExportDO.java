package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 手动出库表
 *
 * @author lqp
 */
public class ManualExportDO {

    /**
     * 主键
     */
    private String id;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * t_materialinventory的主键（库存信息）
     */
    private String materialInventoryId;

    /**
     * 出库单号
     */
    private String number;

    /**
     * 出库日期
     */
    private Date date;

    /**
     * 出库数量
     */
    private String amount;

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

    public String getMaterialInventoryId() {
        return materialInventoryId;
    }

    public void setMaterialInventoryId(String materialInventoryId) {
        this.materialInventoryId = materialInventoryId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return "ManualExportDO{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", materialInventoryId='" + materialInventoryId + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", amount='" + amount + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
