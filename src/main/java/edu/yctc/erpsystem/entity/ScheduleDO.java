package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 排程表
 *
 * @author xiaotao
 */
public class ScheduleDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialexport的主键（材料库存）
     */
    private String materialExportId;

    /**
     * 是否完成
     */
    private String isFinish;

    /**
     * 切
     */
    private String qie;

    /**
     * 单价
     */
    private String han;

    /**
     * 绕
     */
    private String rao;

    /**
     * 涂
     */
    private String tu;

    /**
     * 折
     */
    private String chai;

    /**
     * 交付日期
     */
    private Date deliveryDate;

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

    public String getMaterialExportId() {
        return materialExportId;
    }

    public void setMaterialExportId(String materialExportId) {
        this.materialExportId = materialExportId;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getQie() {
        return qie;
    }

    public void setQie(String qie) {
        this.qie = qie;
    }

    public String getHan() {
        return han;
    }

    public void setHan(String han) {
        this.han = han;
    }

    public String getRao() {
        return rao;
    }

    public void setRao(String rao) {
        this.rao = rao;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getChai() {
        return chai;
    }

    public void setChai(String chai) {
        this.chai = chai;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScheduleDO{" +
                "id='" + id + '\'' +
                ", materialExportId='" + materialExportId + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", qie='" + qie + '\'' +
                ", han='" + han + '\'' +
                ", rao='" + rao + '\'' +
                ", tu='" + tu + '\'' +
                ", chai='" + chai + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", createTime=" + createTime +
                '}';
    }

}
