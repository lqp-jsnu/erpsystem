package edu.yctc.erpsystem.vo;

/**
 * 材料库存视图
 *
 * @author wjd
 */
public class MaterialInventoryMasterVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 供应商代号
     */
    private String code;

    /**
     * 采购单号
     */
    private String orderNumber;

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
     * 供应商id
     */
    private String supplierId;

    /**
     * 材料id
     */
    private String materialInfoId;

    /**
     * 待出库的制造流程单数
     */
    private Integer waitNumber;

    /**
     * t_materialinfomaster的主键
     */
    private String materialInfoMasterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getMaterialInfoId() {
        return materialInfoId;
    }

    public void setMaterialInfoId(String materialInfoId) {
        this.materialInfoId = materialInfoId;
    }

    public Integer getWaitNumber() {
        return waitNumber;
    }

    public void setWaitNumber(Integer waitNumber) {
        this.waitNumber = waitNumber;
    }

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    @Override
    public String toString() {
        return "MaterialInventoryMasterVO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", materialInfoId='" + materialInfoId + '\'' +
                ", waitNumber=" + waitNumber +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                '}';
    }

}
