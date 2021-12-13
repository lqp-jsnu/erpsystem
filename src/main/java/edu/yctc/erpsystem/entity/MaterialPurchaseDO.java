package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 原材料采购表
 *
 * @author xiaotao
 */
public class MaterialPurchaseDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_supplier的主键（供应商）
     */
    private String supplierId;

    /**
     * t_materialinfo的主键（材料信息）
     */
    private String materialInfoId;

    /**
     * syuser的主键（录入者）
     */
    private String user;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 采购数量
     */
    private String amount;

    /**
     * 希望交货日期
     */
    private Date hopeDeliveryDate;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 是否已经导出到Excel，如果导出到Excel，则将数据插入到待入库表
     */
    private String isExport;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getMaterialInfoId() {
        return materialInfoId;
    }

    public void setMaterialInfoId(String materialInfoId) {
        this.materialInfoId = materialInfoId;
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

    public Date getHopeDeliveryDate() {
        return hopeDeliveryDate;
    }

    public void setHopeDeliveryDate(Date hopeDeliveryDate) {
        this.hopeDeliveryDate = hopeDeliveryDate;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getIsExport() {
        return isExport;
    }

    public void setIsExport(String isExport) {
        this.isExport = isExport;
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
        return "MaterialPurchaseDO{" +
                "id='" + id + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", materialInfoId='" + materialInfoId + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", amount='" + amount + '\'' +
                ", hopeDeliveryDate=" + hopeDeliveryDate +
                ", checkFlag='" + checkFlag + '\'' +
                ", isExport='" + isExport + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
