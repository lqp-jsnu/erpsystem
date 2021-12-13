package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 首页产品保质期监控视图
 *
 * @author qiang
 */
public class MainProductExpiryDateVO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_originalproduct的主键
     */
    private String originalProductId;

    /**
     * 入库时间
     */
    private Date storageDate;

    /**
     * 产品料号，不可以重复
     */
    private String productNumber;

    /**
     * 规格
     */
    private String spec;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 过期/剩余天数
     */
    private String date;

    /**
     * 过期/剩余天数(整型)
     */
    private Integer leaveDate;

    /**
     * 检测结果
     */
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Integer leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MainProductExpiryDateVO{" +
                "id='" + id + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", storageDate=" + storageDate +
                ", productNumber='" + productNumber + '\'' +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", date='" + date + '\'' +
                ", leaveDate=" + leaveDate +
                ", result='" + result + '\'' +
                '}';
    }

}
