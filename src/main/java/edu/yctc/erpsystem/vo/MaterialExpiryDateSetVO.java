package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 原材料保质期设置视图
 * @author qiang
 */
public class MaterialExpiryDateSetVO {

    /**
     * 主键
     */
    private  String id;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 保质期
     */
    private Integer expiryDate;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 备注
     * */
    private String remark;

    /**
     * t_materialinfomaster的主键
     */
    private String materialInfoMasterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Integer expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    @Override
    public String toString() {
        return "MaterialExpiryDateSetVO{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", expiryDate=" + expiryDate +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                '}';
    }

}
