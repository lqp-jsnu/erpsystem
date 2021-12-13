package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 原材料报废表
 *
 * @author xiaotao
 */
public class MaterialDumpDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialinventory的主键（材料库存）
     */
    private String materialInventoryId;

    /**
     * syuser的主键（录入者）
     */
    private String enter;

    /**
     * t_supplier的主键（供应商）
     */
    private String checker;

    /**
     * 报废数量
     */
    private String amount;

    /**
     * 报废日期
     */
    private Date date;

    /**
     * 报废原因
     */
    private String reason;

    /**
     * 审核标志
     */
    private String checkFlag;

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

    public String getMaterialInventoryId() {
        return materialInventoryId;
    }

    public void setMaterialInventoryId(String materialInventoryId) {
        this.materialInventoryId = materialInventoryId;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MaterialDumpDO{" +
                "id='" + id + '\'' +
                ", materialInventoryId='" + materialInventoryId + '\'' +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
