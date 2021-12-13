package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 材料明细表
 *
 * @author xiaotao
 */
public class MaterialExportDetailDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialexport的主键（主表）
     */
    private String materialExportId;

    /**
     * t_materialInventory的主键
     */
    private String materialInventoryId;

    /**
     * 出库数量
     */
    private String amount;

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

    public String getMaterialInventoryId() {
        return materialInventoryId;
    }

    public void setMaterialInventoryId(String materialInventoryId) {
        this.materialInventoryId = materialInventoryId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MaterialExportDetailDO{" +
                "id='" + id + '\'' +
                ", materialExportId='" + materialExportId + '\'' +
                ", materialInventoryId='" + materialInventoryId + '\'' +
                ", amount='" + amount + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
