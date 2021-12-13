package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 产品保质期设置视图
 * @author qiang
 */
public class ProductExpiryDateSetVO {

    /**
     *主键
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
     * t_originalproduct的主键
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

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    @Override
    public String toString() {
        return "ProductExpiryDateSetVO{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", expiryDate=" + expiryDate +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                '}';
    }

}
