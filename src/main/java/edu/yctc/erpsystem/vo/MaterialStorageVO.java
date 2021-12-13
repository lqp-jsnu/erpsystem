package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 原材料入库视图
 *
 * @author wjd
 */
public class MaterialStorageVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 采购单号
     */
    private String orderNumber;

    /**
     * 材料信息
     */
    private String materialInfoId;

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
     * 入库类型
     */
    private String dictValue;

    /**
     * 存放仓库
     */
    private String houseName;

    /**
     * 来料日期
     */
    private Date inDate;

    /**
     * 入库日期
     */
    private Date inStorageDate;

    /**
     * 录入日期
     */
    private Date createTime;

    /**
     * 录入者
     */
    private String user;

    /**
     * 审核者
     */
    private String checker;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * t_materialpurchasetobestorage的主键
     */
    private String materialPurchaseToBeStorageId;

    /**
     * 入库类型 t_dict的主键
     */
    private String dictId;

    /**
     * t_warehouse的主键
     */
    private String wareHouseId;

    /**
     * t_supplier的主键
     */
    private String supplierId;

    /**
     * 金额
     */
    private String totalMoney;

    /**
     * 采购数量
     */
    private String amount;

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

    public String getMaterialInfoId() {
        return materialInfoId;
    }

    public void setMaterialInfoId(String materialInfoId) {
        this.materialInfoId = materialInfoId;
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

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getMaterialPurchaseToBeStorageId() {
        return materialPurchaseToBeStorageId;
    }

    public void setMaterialPurchaseToBeStorageId(String materialPurchaseToBeStorageId) {
        this.materialPurchaseToBeStorageId = materialPurchaseToBeStorageId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MaterialStorageVO{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", materialInfoId='" + materialInfoId + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", houseName='" + houseName + '\'' +
                ", inDate=" + inDate +
                ", inStorageDate=" + inStorageDate +
                ", createTime=" + createTime +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", materialPurchaseToBeStorageId='" + materialPurchaseToBeStorageId + '\'' +
                ", dictId='" + dictId + '\'' +
                ", wareHouseId='" + wareHouseId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

}
