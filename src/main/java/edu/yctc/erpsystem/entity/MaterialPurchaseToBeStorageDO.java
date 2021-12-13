package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 原材料待入库表
 *
 * @author xiaotao
 */
public class MaterialPurchaseToBeStorageDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialpurchase的主键
     */
    private String materialPurchaseId;

    /**
     * t_invoicetitle的主键
     */
    private String invoiceTitleId;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 采购单号
     */
    private String orderNumber;

    /**
     * 入库数量(同一个采购单可能分批入库)
     */
    private String inAmount;

    /**
     * 希望交货日期
     */
    private Date hopeDeliveryDate;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * 是否全部入库（如果全都入库，则前端不显示）
     */
    private String allIn;

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

    public String getMaterialPurchaseId() {
        return materialPurchaseId;
    }

    public void setMaterialPurchaseId(String materialPurchaseId) {
        this.materialPurchaseId = materialPurchaseId;
    }

    public String getInvoiceTitleId() {
        return invoiceTitleId;
    }

    public void setInvoiceTitleId(String invoiceTitleId) {
        this.invoiceTitleId = invoiceTitleId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public Date getHopeDeliveryDate() {
        return hopeDeliveryDate;
    }

    public void setHopeDeliveryDate(Date hopeDeliveryDate) {
        this.hopeDeliveryDate = hopeDeliveryDate;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getAllIn() {
        return allIn;
    }

    public void setAllIn(String allIn) {
        this.allIn = allIn;
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
        return "MaterialPurchaseToBeStorageDO{" +
                "id='" + id + '\'' +
                ", materialPurchaseId='" + materialPurchaseId + '\'' +
                ", invoiceTitleId='" + invoiceTitleId + '\'' +
                ", checker='" + checker + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", hopeDeliveryDate=" + hopeDeliveryDate +
                ", checkFlag='" + checkFlag + '\'' +
                ", allIn='" + allIn + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}

