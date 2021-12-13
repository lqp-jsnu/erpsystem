package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 原材料入库表
 *
 * @author lqp
 */
public class MaterialStorageDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialpurchasetobestorage的主键（主表）
     */
    private String materialPurchaseToBeStorageId;

    /**
     * t_warehouse的主键（仓库）
     */
    private String wareHouseId;

    /**
     * t_dict的主键（入库类型）
     */
    private String dictId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 来料日期
     */
    private Date inDate;

    /**
     * 入库日期
     */
    private Date inStorageDate;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 录入者  入库数量
     */
    private String inAmount;

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

    public String getMaterialPurchaseToBeStorageId() {
        return materialPurchaseToBeStorageId;
    }

    public void setMaterialPurchaseToBeStorageId(String materialPurchaseToBeStorageId) {
        this.materialPurchaseToBeStorageId = materialPurchaseToBeStorageId;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
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

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
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
        return "MaterialStorageDO{" +
                "id='" + id + '\'' +
                ", materialPurchaseToBeStorageId='" + materialPurchaseToBeStorageId + '\'' +
                ", wareHouseId='" + wareHouseId + '\'' +
                ", dictId='" + dictId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", inDate=" + inDate +
                ", inStorageDate=" + inStorageDate +
                ", checkFlag='" + checkFlag + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
