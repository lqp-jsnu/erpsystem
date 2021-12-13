package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 材料库存表
 *
 * @author xiaotao
 */
public class MaterialInventoryDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialstorage的主键
     */
    private String materialStorageId;

    /**
     * t_materialinventorymaster的主键（主表）
     */
    private String materialInventoryMasterId;

    /**
     * t_dict的主键（入库类型）
     */
    private String dictId;

    /**
     * 来料日期
     */
    private Date inDate;

    /**
     * 入库日期
     */
    private Date inStorageDate;

    /**
     * 剩余的库存数量（最后为0）
     */
    private String leftAmount;

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

    public String getMaterialStorageId() {
        return materialStorageId;
    }

    public void setMaterialStorageId(String materialStorageId) {
        this.materialStorageId = materialStorageId;
    }

    public String getMaterialInventoryMasterId() {
        return materialInventoryMasterId;
    }

    public void setMaterialInventoryMasterId(String materialInventoryMasterId) {
        this.materialInventoryMasterId = materialInventoryMasterId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
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

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MaterialInventoryDO{" +
                "id='" + id + '\'' +
                ", materialStorageId='" + materialStorageId + '\'' +
                ", materialInventoryMasterId='" + materialInventoryMasterId + '\'' +
                ", dictId='" + dictId + '\'' +
                ", inDate=" + inDate +
                ", inStorageDate=" + inStorageDate +
                ", leftAmount='" + leftAmount + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
