package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 出货单历史表
 *
 * @author xiaotao
 */
public class BillOfSaleHistoryDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_billofsale的主键（主表）
     */
    private String billOfSaleId;

    /**
     * t_invoicetitle的主键（发票抬头）
     */
    private String invoiceTitleId;

    /**
     * 出货单号
     */
    private String number;

    /**
     * 发货日期
     */
    private Date date;

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

    public String getBillOfSaleId() {
        return billOfSaleId;
    }

    public void setBillOfSaleId(String billOfSaleId) {
        this.billOfSaleId = billOfSaleId;
    }

    public String getInvoiceTitleId() {
        return invoiceTitleId;
    }

    public void setInvoiceTitleId(String invoiceTitleId) {
        this.invoiceTitleId = invoiceTitleId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BillOfSaleHistoryDO{" +
                "id='" + id + '\'' +
                ", billOfSaleId='" + billOfSaleId + '\'' +
                ", invoiceTitleId='" + invoiceTitleId + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", createTime=" + createTime +
                '}';
    }

}
