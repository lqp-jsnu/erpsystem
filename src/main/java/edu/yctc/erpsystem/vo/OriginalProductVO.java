package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 成品原始原始信息表
 *
 * @author smile
 */
public class OriginalProductVO {

    /**
     * 客户代号
     */
    private String code;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 规格
     */
    private String spec;

    /**
     * '品名／磁棒／尺寸(材质)'
     */
    private String masterItemName;

    /**
     * '规格/初值/电阻线(线径)'
     */
    private String masterSpec;

    /**
     * 客户料号，不可以重复
     */
    private String productNumber;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private String unitPrice;

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
    private String remark;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     * 图纸Url
     */
    private String drawingUrl;

    /**
     * 主键
     */
    private String id;

    /**
     * t_customer的主键
     */
    private String customerId;

    /**
     * t_materialinfomaster的主键
     */
    private String materialInfoMasterId;

    /**
     * syuser的主键
     */
    private String checker;

    /**
     * 录入日期
     */
    private Date createTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getMasterItemName() {
        return masterItemName;
    }

    public void setMasterItemName(String masterItemName) {
        this.masterItemName = masterItemName;
    }

    public String getMasterSpec() {
        return masterSpec;
    }

    public void setMasterSpec(String masterSpec) {
        this.masterSpec = masterSpec;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getDrawingUrl() {
        return drawingUrl;
    }

    public void setDrawingUrl(String drawingUrl) {
        this.drawingUrl = drawingUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OriginalProductVO{" +
                "code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", masterItemName='" + masterItemName + '\'' +
                ", masterSpec='" + masterSpec + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", label='" + label + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", remark='" + remark + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", drawingUrl='" + drawingUrl + '\'' +
                ", id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", checker='" + checker + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
