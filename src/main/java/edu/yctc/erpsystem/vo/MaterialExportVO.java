package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 材料出库信息
 *
 * @author wjd
 */
public class MaterialExportVO {

    /**
     * id
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
     * 客户订单号
     */
    private String code;

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

    /**
     * 供应商编号
     */
    private String supplierCode;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 单位
     */
    private String unit;

    /**
     * 发出仓库
     */
    private String houseName;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 是否出库
     */
    private String isMaterialExport;

    /**
     * 备注
     */
    private String remark;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsMaterialExport() {
        return isMaterialExport;
    }

    public void setIsMaterialExport(String isMaterialExport) {
        this.isMaterialExport = isMaterialExport;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MaterialExportVO{" +
                "id='" + id + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", exportNumber='" + exportNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", exportDate=" + exportDate +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", user='" + user + '\'' +
                ", checker='" + checker + '\'' +
                ", createTime=" + createTime +
                ", checkFlag='" + checkFlag + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unit='" + unit + '\'' +
                ", houseName='" + houseName + '\'' +
                ", name='" + name + '\'' +
                ", isMaterialExport='" + isMaterialExport + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
