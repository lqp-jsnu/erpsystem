package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 原材料待入库表
 *
 * @author wjd
 */
public class MaterialPurchaseToBeStorageVO {

    /**
     *  主键
     */
    private String id;

    /**
     * 采购单号
     */
    private String orderNumber;

    /**
     * 供应商代号
     */

    private String code;

    /**
     * 品名／磁棒／尺寸(材质)
     */

    private String itemName;

    /**
     * 规格/初R值/电阻线(线径)
     */
    private String spec;

    /**
     * 采购数量
     */
    private String amount;

    /**
     * 入库数量
     */
    private String inAmount;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 希望交期
     */
    private Date hopeDeliveryDate;

    /**
     * 发票抬头
     */
    private String titleName;

    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * t_materialinfo的主键
     */
    private String materialInfoId;

    /**
     * t_supplier的主键
     */
    private String supplierId;

    /**
     * t_materialpurchase的主键
     */
    private String materialPurchaseId;

    /**
     * t_invoicetitle的主键
     */
    private String invoiceTitleId;

    /**
     * 是否全部入库
     */
    private String allIn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
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

    public Date getHopeDeliveryDate() {
        return hopeDeliveryDate;
    }

    public void setHopeDeliveryDate(Date hopeDeliveryDate) {
        this.hopeDeliveryDate = hopeDeliveryDate;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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

    public String getMaterialInfoId() {
        return materialInfoId;
    }

    public void setMaterialInfoId(String materialInfoId) {
        this.materialInfoId = materialInfoId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getAllIn() {
        return allIn;
    }

    public void setAllIn(String allIn) {
        this.allIn = allIn;
    }

    @Override
    public String toString() {
        return "MaterialPurchaseToBeStorageVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", hopeDeliveryDate=" + hopeDeliveryDate +
                ", titleName='" + titleName + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", materialInfoId='" + materialInfoId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", materialPurchaseId='" + materialPurchaseId + '\'' +
                ", invoiceTitleId='" + invoiceTitleId + '\'' +
                ", allIn='" + allIn + '\'' +
                '}';
    }

}
