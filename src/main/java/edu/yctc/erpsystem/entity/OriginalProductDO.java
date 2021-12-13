package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 成品原始信息表
 *
 * @author lqp
 */
public class OriginalProductDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialinfomaster的主键（原材料）
     */
    private String materialInfoMasterId;

    /**
     * t_customer的主键
     */
    private String customerId;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

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
     * 单价
     */
    private String unitPrice;

    /**
     * 产品料号，不可以重复
     */
    private String productNumber;

    /**
     * 标签
     */
    private String label;

    /**
     * 色码
     */
    private String colorCode;

    /**
     * 图纸Url
     */
    private String drawingUrl;

    /**
     * 删除标志
     */
    private String deleteFlag;

    /**
     * 检测标志
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    public String getDrawingUrl() {
        return drawingUrl;
    }

    public void setDrawingUrl(String drawingUrl) {
        this.drawingUrl = drawingUrl;
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
        return "OriginalProductDO{" +
                "id='" + id + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", checker='" + checker + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", label='" + label + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", drawingUrl='" + drawingUrl + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
