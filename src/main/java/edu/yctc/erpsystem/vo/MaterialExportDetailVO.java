package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 材料出库明细
 *
 * @author wjd
 */
public class MaterialExportDetailVO {

    /**
     * t_materialExport的主键
     */
    private String id;

    /**
     * 制造流程单号
     */
    private String jobTicketNumber;

    /**
     * 出库单号
     */
    private String exportNumber;

    /**
     * 客户订单号
     */
    private String orderNumber;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 供应商代号
     */
    private String code;

    /**
     * 仓库名称
     */
    private String houseName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 出库日期
     */
    private Date exportDate;

    /**
     * 品名／磁棒／尺寸(材质)
     */
    private String itemName;

    /**
     * 规格/初R值/电阻线(线径)
     */
    private String spec;

    /**
     * 发料数量
     */
    private String issueNumber;

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
     * 审核标志
     */
    private String checkFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(String exportNumber) {
        this.exportNumber = exportNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
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

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
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

    @Override
    public String toString() {
        return "MaterialExportDetailVO{" +
                "id='" + id + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", exportNumber='" + exportNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", houseName='" + houseName + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", remark='" + remark + '\'' +
                ", exportDate=" + exportDate +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", createTime=" + createTime +
                ", checkFlag='" + checkFlag + '\'' +
                '}';
    }

}
