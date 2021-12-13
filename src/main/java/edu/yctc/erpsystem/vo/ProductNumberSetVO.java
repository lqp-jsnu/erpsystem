package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 产品数量设置视图
 *
 * @author qiang
 */
public class ProductNumberSetVO {

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
     * 产品料号，不可以重复
     */
    private String productNumber;

    /**
     * 单位
     */
    private String unit;

    /**
     * 告警最小值
     */
    private String minNumber;

    /**
     * 告警最大值
     */
    private String maxNumber;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 原始成品主键
     */
    private String originalProductId;

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

    public String getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
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

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    @Override
    public String toString() {
        return "ProductNumberSetVO{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", minNumber='" + minNumber + '\'' +
                ", maxNumber='" + maxNumber + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                '}';
    }

}
