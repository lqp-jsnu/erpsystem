package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 材料原始信息表
 *
 * @author xiaotao
 */
public class MaterialInfoDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialinfomaster主键（主表）
     */
    private String materialInfoMasterId;

    /**
     * t_supplier主键（供应商）
     */
    private String supplierId;

    /**
     * syuser主键（审核者）
     */
    private String checker;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 特殊要求
     */
    private String specialRequire;

    /**
     * 删除标志
     */
    private String deleteFlag;

    /**
     * 是否审核
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

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSpecialRequire() {
        return specialRequire;
    }

    public void setSpecialRequire(String specialRequire) {
        this.specialRequire = specialRequire;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        return "MaterialInfoDO{" +
                "id='" + id + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", checker='" + checker + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", specialRequire='" + specialRequire + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
