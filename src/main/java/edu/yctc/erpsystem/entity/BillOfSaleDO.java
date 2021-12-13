package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 出货单表
 *
 * @author lqp
 */
public class BillOfSaleDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_prodexport的主键（成品出库）
     */
    private String productExportId;

    /**
     * 出货状态
     */
    private String exportStatus;

    /**
     * syuser的主键（审核者）
     */
    private String checker;

    /**
     * 审核状态
     */
    private String checkFlag;

    /**
     * 产品编号
     */
    private String productNumber;

    /**
     * 客户订单号
     */
    private String orderNumber;

    /**
     * 备注
     */
    private String remark;

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

    public String getProductExportId() {
        return productExportId;
    }

    public void setProductExportId(String productExportId) {
        this.productExportId = productExportId;
    }

    public String getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BillOfSaleDO{" +
                "id='" + id + '\'' +
                ", productExportId='" + productExportId + '\'' +
                ", exportStatus='" + exportStatus + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
