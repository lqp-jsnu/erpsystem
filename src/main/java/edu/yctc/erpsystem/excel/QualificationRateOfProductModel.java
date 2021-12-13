package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 成品合格率excel实体类
 *
 * @author zzy
 */
public class QualificationRateOfProductModel extends BaseRowModel {

    @ExcelProperty(value = {"成品合格率信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"成品合格率信息", "客户代码"}, index = 1)
    private String code;

    @ExcelProperty(value = {"成品合格率信息", "工作传票号"}, index = 2)
    private String jobTicketNumber;

    @ExcelProperty(value = {"成品合格率信息", "订单号"}, index = 3)
    private String orderNumber;

    @ExcelProperty(value = {"成品合格率信息", "成品料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"成品合格率信息", "品名"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"成品合格率信息", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"成品合格率信息", "合格率"}, index = 7)
    private String rate;

    @ExcelProperty(value = {"成品合格率信息", "生产数量"}, index = 8)
    private String productQuantity;

    @ExcelProperty(value = {"成品合格率信息", "产品入库数量"}, index = 9)
    private String storageAmount;

    @ExcelProperty(value = {"成品合格率信息", "产品存放仓库"}, index = 10)
    private String houseName;

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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Override
    public String toString() {
        return "QualificationRateOfProductModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", rate='" + rate + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", houseName='" + houseName + '\'' +
                '}';
    }

}
