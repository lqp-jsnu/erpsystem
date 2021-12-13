package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 原材料采购表
 *
 * @author wjd
 */
public class MaterialPurchaseVO {

    /**
     * 主键
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
     * 供应商名称
     */
    private String name;

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
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 存放仓库
     */
    private String houseName;

    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 录入者
     */
    private String enter;

    /**
     * 审核者
     */
    private String checker;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     *备注
     */
    private String remark;

    /**
     * 希望交期
     */
    private Date hopeDeliveryDate;

    /**
     * 材料信息id
     */
    private String materialInfoId;

    /**
     * 是否已经导出到Excel，如果导出到Excel，则将数据插入到待入库表
     */
    private String isExport;

    /**
     * t_supplier的主键
     */
    private String supplierId;

    /**
     * 发票抬头的主键
     */
    private String invoiceTitleId;

    /**
     * 金额
     */
    private String totalMoney;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
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

    public Date getHopeDeliveryDate() {
        return hopeDeliveryDate;
    }

    public void setHopeDeliveryDate(Date hopeDeliveryDate) {
        this.hopeDeliveryDate = hopeDeliveryDate;
    }

    public String getMaterialInfoId() {
        return materialInfoId;
    }

    public void setMaterialInfoId(String materialInfoId) {
        this.materialInfoId = materialInfoId;
    }

    public String getIsExport() {
        return isExport;
    }

    public void setIsExport(String isExport) {
        this.isExport = isExport;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getInvoiceTitleId() {
        return invoiceTitleId;
    }

    public void setInvoiceTitleId(String invoiceTitleId) {
        this.invoiceTitleId = invoiceTitleId;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "MaterialPurchaseVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", houseName='" + houseName + '\'' +
                ", createTime=" + createTime +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", hopeDeliveryDate=" + hopeDeliveryDate +
                ", materialInfoId='" + materialInfoId + '\'' +
                ", isExport='" + isExport + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", invoiceTitleId='" + invoiceTitleId + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                '}';
    }

}
