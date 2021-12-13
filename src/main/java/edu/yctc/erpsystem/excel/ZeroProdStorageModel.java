package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 零品入库excel实体类
 *
 * @author zzy
 */
public class ZeroProdStorageModel extends BaseRowModel {

    @ExcelProperty(value = {"零品入库信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"零品入库信息", "订单号"}, index = 1)
    private String orderNumber;

    @ExcelProperty(value = {"零品入库信息", "客户代码"}, index = 2)
    private String code;

    @ExcelProperty(value = {"零品入库信息", "工作传票号"}, index = 3)
    private String jobTicketNumber;

    @ExcelProperty(value = {"零品入库信息", "客户料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"零品入库信息", "品名"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"零品入库信息", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"零品入库信息", "生产数量"}, index = 7)
    private String productQuantity;

    @ExcelProperty(value = {"零品入库信息", "零品入库数量"}, index = 8)
    private String zeroStorageAmount;

    @ExcelProperty(value = {"零品入库信息", "零品存放仓库"}, index = 9)
    private String houseName;

    @ExcelProperty(value = {"零品入库信息", "是否有成品入库"}, index = 11)
    private String isProductStorage;

    @ExcelProperty(value = {"零品入库信息", "录入者"}, index = 12)
    private String enter;

    @ExcelProperty(value = {"零品入库信息", "审核者"}, index = 13)
    private String checker;

    @ExcelProperty(value = {"零品入库信息", "状态"}, index = 14)
    private String checkFlag;

    @ExcelProperty(value = {"零品入库信息", "入库日期"}, index = 15)
    private String storageDate;

    @ExcelProperty(value = {"零品入库信息", "创建时间"}, index = 16)
    private String createTime;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
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

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getZeroStorageAmount() {
        return zeroStorageAmount;
    }

    public void setZeroStorageAmount(String zeroStorageAmount) {
        this.zeroStorageAmount = zeroStorageAmount;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getIsProductStorage() {
        return isProductStorage;
    }

    public void setIsProductStorage(String isProductStorage) {
        this.isProductStorage = isProductStorage;
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

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ZeroProdStorageModel{" +
                "index='" + index + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", zeroStorageAmount='" + zeroStorageAmount + '\'' +
                ", houseName='" + houseName + '\'' +
                ", isProductStorage='" + isProductStorage + '\'' +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", storageDate='" + storageDate + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
