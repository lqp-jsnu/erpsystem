package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 手动出库
 *
 * @author smile
 */
public class ManualExportVO {

    /**
     * ManualExport的主键
     */
    private String id;

    /**
     * 出库单号
     */
    private String exportNumber;

    /**
     * 库存数量
     */
    private String leftAmount;

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
     * 出库数量
     */
    private String amount;

    /**
     * 出库仓库
     */
    private String houseName;

    /**
     * 出库日期
     */
    private Date exportDate;

    /**
     * 出库者
     */
    private String user;

    /**
     * 审核者
     */
    private String checker;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private String checkFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * MaterialInventory的主键
     */
    private String materialInventoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(String exportNumber) {
        this.exportNumber = exportNumber;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getMaterialInventoryId() {
        return materialInventoryId;
    }

    public void setMaterialInventoryId(String materialInventoryId) {
        this.materialInventoryId = materialInventoryId;
    }

    @Override
    public String toString() {
        return "ManualExportVO{" +
                "id='" + id + '\'' +
                ", exportNumber='" + exportNumber + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", amount='" + amount + '\'' +
                ", houseName='" + houseName + '\'' +
                ", exportDate=" + exportDate +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", createTime=" + createTime +
                ", checkFlag='" + checkFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", materialInventoryId='" + materialInventoryId + '\'' +
                '}';
    }

}
