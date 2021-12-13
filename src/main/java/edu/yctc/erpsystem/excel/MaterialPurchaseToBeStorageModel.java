package edu.yctc.erpsystem.excel;

/**
 * 原材料待入库excel实体类
 *
 * @author wjd
 */
public class MaterialPurchaseToBeStorageModel {

    /** 序号 */
    private String index;

    /** 品名／磁棒／尺寸(材质) */
    private String itemName;

    /** 规格/初R值/电阻线(线径) */
    private String spec;

    /** 采购单号 */
    private String orderNumber;

    /** 数量 */
    private String amount;

    /** 单位 */
    private String unit;

    /** 单价 */
    private String unitPrice;

    /** 总价 */
    private String totalPrice;

    /** 希望交期 */
    private String hopeDeliveryDate;

    /** 备注 */
    private String remark;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getHopeDeliveryDate() {
        return hopeDeliveryDate;
    }

    public void setHopeDeliveryDate(String hopeDeliveryDate) {
        this.hopeDeliveryDate = hopeDeliveryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MaterialPurchaseToBeStorageModel{" +
                "index='" + index + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", hopeDeliveryDate='" + hopeDeliveryDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
