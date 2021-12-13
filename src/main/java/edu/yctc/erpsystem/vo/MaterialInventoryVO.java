package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 材料盘点
 *
 * @author smile lqp
 */
public class MaterialInventoryVO {

    /**
     * MaterialInventory的主键
     */
    private String id;

    /**
     * t_materialinfomaster的主键
     */
    private String materialInfoMasterId;

    /**
     * t_materialinventorymaster的主键
     */
    private String materialInventoryMasterId;

    /**
     * t_warehouse的主键
     */
    private String warehouseId;

    /**
     * t_supplier的主键
     */
    private String supplierId;

    /**
     * 供应商代号
     */
    private String code;

    /**
     * 采购单号
     */
    private String orderNumber;

    /**
     * 来料日期
     */
    private Date inDate;

    /**
     * 入库日期
     */
    private Date inStorageDate;

    /**
     * 品名／磁棒／尺寸(材质)
     */
    private String itemName;

    /**
     * 规格/初R值/电阻线(线径)
     */
    private String spec;

    /**
     * 库存数量
     */
    private String leftAmount;

    /**
     * 入库数量
     */
    private String inAmount;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 单位
     */
    private String unit;

    /**
     * 金额
     */
    private  String jine;

    /**
     * 仓库名称
     */
    private String houseName;

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

    public String getMaterialInventoryMasterId() {
        return materialInventoryMasterId;
    }

    public void setMaterialInventoryMasterId(String materialInventoryMasterId) {
        this.materialInventoryMasterId = materialInventoryMasterId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Override
    public String toString() {
        return "MaterialInventoryVO{" +
                "id='" + id + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", materialInventoryMasterId='" + materialInventoryMasterId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", code='" + code + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", inDate=" + inDate +
                ", inStorageDate=" + inStorageDate +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unit='" + unit + '\'' +
                ", jine='" + jine + '\'' +
                ", houseName='" + houseName + '\'' +
                '}';
    }

}
