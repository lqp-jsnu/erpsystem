package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 成品入库明细excel实体类
 *
 * @author zzy
 */
public class ProductStorageDetailsModel extends BaseRowModel {

    @ExcelProperty(value = {"成品入库明细信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"成品入库明细信息", "客户代码"}, index = 1)
    private String code;

    @ExcelProperty(value = {"成品入库明细信息", "工作传票号"}, index = 2)
    private String jobTicketNumber;

    @ExcelProperty(value = {"成品入库明细信息", "订单号"}, index = 3)
    private String orderNumber;

    @ExcelProperty(value = {"成品入库明细信息", "规格"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"成品入库明细信息", "产品入库数量"}, index = 5)
    private String storageAmount;

    @ExcelProperty(value = {"成品入库明细信息", "单位"}, index = 6)
    private String unit;

    @ExcelProperty(value = {"成品入库明细信息", "单价"}, index = 7)
    private String unitPrice;

    @ExcelProperty(value = {"成品入库明细信息", "金额"}, index = 8)
    private String money;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ProductStorageDetailsModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", spec='" + spec + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

}
