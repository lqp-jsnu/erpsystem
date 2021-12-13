package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 料品原始信息视图
 *
 * @author xcg
 */
public class MaterialInfoVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 品名／磁棒／尺寸(材质)
     */
    private String itemName;

    /**
     * 规格/初R值/电阻线(线径)
     */
    private String spec;

    /**
     * t_supplier主键
     */
    private String supplierId;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签
     */
    private String label;

    /**
     * 色码
     */
    private String colorCode;

    /**
     * 特殊要求
     */
    private String specialRequire;

    /**
     * 单位
     */
    private String unit;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 审核者
     */
    private String checker;

    /**
     * 供应商代号
     */
    private String code;

    /**
     * 供应商名字
     */
    private String name;

    /**
     * 料品主信息id
     */
    private String materialInfoMasterId;

    /**
     * 删除标识
     */
    private String deleteFlag;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getSpecialRequire() {
        return specialRequire;
    }

    public void setSpecialRequire(String specialRequire) {
        this.specialRequire = specialRequire;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "MaterialInfoVO{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", remark='" + remark + '\'' +
                ", label='" + label + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", specialRequire='" + specialRequire + '\'' +
                ", unit='" + unit + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", createTime=" + createTime +
                ", checker='" + checker + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }

}
