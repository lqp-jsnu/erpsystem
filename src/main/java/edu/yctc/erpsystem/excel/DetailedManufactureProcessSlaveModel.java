package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 制造流程单详细
 *
 * @author smile
 */
public class DetailedManufactureProcessSlaveModel extends BaseRowModel {
    @ExcelProperty(value = {"制造流程单详细", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"制造流程单详细", "工作传票号码"}, index = 1)
    private String jobTicketNumber;

    @ExcelProperty(value = {"制造流程单详细", "客户订单号"}, index = 2)
    private String orderNumber;

    @ExcelProperty(value = {"制造流程单详细", "客户代号"}, index = 3)
    private String code;

    @ExcelProperty(value = {"制造流程单详细", "客户料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"制造流程单详细", "品名"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"制造流程单详细", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"制造流程单详细", "品名／磁棒／尺寸(材质)"}, index = 7)
    private String masterItemName;

    @ExcelProperty(value = {"制造流程单详细", "规格/初值/电阻线(线径)"}, index = 8)
    private String masterSpec;

    @ExcelProperty(value = {"制造流程单详细", "订购数量"}, index = 9)
    private String orderAmount;

    @ExcelProperty(value = {"制造流程单详细", "投产数量"}, index = 10)
    private String productAmount;

    @ExcelProperty(value = {"制造流程单详细", "每单投产数量"}, index = 11)
    private String everyOrderAmount;

    @ExcelProperty(value = {"制造流程单详细", "采购日期"}, index = 12)
    private String orderDate;

    @ExcelProperty(value = {"制造流程单详细", "交货日期"}, index = 13)
    private String deliveryDate;

    @ExcelProperty(value = {"制造流程单详细", "是否补单"}, index = 14)
    private String isIssueOrder;

    @ExcelProperty(value = {"制造流程单详细", "是否入库"}, index = 15)
    private String isInHouse;

    @ExcelProperty(value = {"制造流程单详细", "是否进入生成"}, index = 16)
    private String isIntoMake;

    @ExcelProperty(value = {"制造流程单详细", "是否生成流程单"}, index = 17)
    private String isGenerateManufacture;

    @ExcelProperty(value = {"制造流程单详细", "是否出库"}, index = 18)
    private String isMaterialExport;

    @ExcelProperty(value = {"制造流程单详细", "出库是否审核通过"}, index = 19)
    private String isExportCheckPass;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getMasterItemName() {
        return masterItemName;
    }

    public void setMasterItemName(String masterItemName) {
        this.masterItemName = masterItemName;
    }

    public String getMasterSpec() {
        return masterSpec;
    }

    public void setMasterSpec(String masterSpec) {
        this.masterSpec = masterSpec;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getEveryOrderAmount() {
        return everyOrderAmount;
    }

    public void setEveryOrderAmount(String everyOrderAmount) {
        this.everyOrderAmount = everyOrderAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getIsIssueOrder() {
        return isIssueOrder;
    }

    public void setIsIssueOrder(String isIssueOrder) {
        this.isIssueOrder = isIssueOrder;
    }

    public String getIsInHouse() {
        return isInHouse;
    }

    public void setIsInHouse(String isInHouse) {
        this.isInHouse = isInHouse;
    }

    public String getIsIntoMake() {
        return isIntoMake;
    }

    public void setIsIntoMake(String isIntoMake) {
        this.isIntoMake = isIntoMake;
    }

    public String getIsGenerateManufacture() {
        return isGenerateManufacture;
    }

    public void setIsGenerateManufacture(String isGenerateManufacture) {
        this.isGenerateManufacture = isGenerateManufacture;
    }

    public String getIsMaterialExport() {
        return isMaterialExport;
    }

    public void setIsMaterialExport(String isMaterialExport) {
        this.isMaterialExport = isMaterialExport;
    }

    public String getIsExportCheckPass() {
        return isExportCheckPass;
    }

    public void setIsExportCheckPass(String isExportCheckPass) {
        this.isExportCheckPass = isExportCheckPass;
    }

    @Override
    public String toString() {
        return "DetailedManufactureProcessSlaveModel{" +
                "index='" + index + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", masterItemName='" + masterItemName + '\'' +
                ", masterSpec='" + masterSpec + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", productAmount='" + productAmount + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", isIssueOrder='" + isIssueOrder + '\'' +
                ", isInHouse='" + isInHouse + '\'' +
                ", isIntoMake='" + isIntoMake + '\'' +
                ", isGenerateManufacture='" + isGenerateManufacture + '\'' +
                ", isMaterialExport='" + isMaterialExport + '\'' +
                ", isExportCheckPass='" + isExportCheckPass + '\'' +
                '}';
    }

}
