package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 原材料报废视图
 *
 * @author lqp
 */
public class MaterialDumpVO {

    /**
     * MaterialDump的主键
     */
    private String id;

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
     * 报废日期
     */
    private Date date;

    /**
     * 报废数量
     */
    private String amount;

    /**
     * 报废原因
     */
    private String reason;

    /**
     * 录入者
     */
    private String enter;

    /**
     * 审核者
     */
    private String checker;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 审核标志
     */
    private String checkFlag;

    /**
     * MaterialInventory的主键
     */
    private String materialInventoryId;

    /**
     * 库存数量
     */
    private String leftAmount;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getMaterialInventoryId() {
        return materialInventoryId;
    }

    public void setMaterialInventoryId(String materialInventoryId) {
        this.materialInventoryId = materialInventoryId;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    @Override
    public String toString() {
        return "MaterialDumpVO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", date=" + date +
                ", amount='" + amount + '\'' +
                ", reason='" + reason + '\'' +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", createTime=" + createTime +
                ", checkFlag='" + checkFlag + '\'' +
                ", materialInventoryId='" + materialInventoryId + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                '}';
    }

}
