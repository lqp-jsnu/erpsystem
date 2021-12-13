package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 质量抽检excel实体类
 *
 * @author zzy
 */
public class QualitySamplingModel extends BaseRowModel {

    @ExcelProperty(value = {"质量抽检信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"质量抽检信息", "订单号"}, index = 1)
    private String orderNumber;

    @ExcelProperty(value = {"质量抽检信息", "客户代码"}, index = 2)
    private String code;

    @ExcelProperty(value = {"质量抽检信息", "工作传票号"}, index = 3)
    private String jobTicketNumber;

    @ExcelProperty(value = {"质量抽检信息", "成品料号"}, index = 4)
    private String productNumber;

    @ExcelProperty(value = {"质量抽检信息", "品名"}, index = 5)
    private String itemName;

    @ExcelProperty(value = {"质量抽检信息", "规格"}, index = 6)
    private String spec;

    @ExcelProperty(value = {"质量抽检信息", "包装数量"}, index = 7)
    private String packageNumber;

    @ExcelProperty(value = {"质量抽检信息", "包装员"}, index = 8)
    private String packer;

    @ExcelProperty(value = {"质量抽检信息", "抽样数"}, index = 9)
    private String sampleNumber;

    @ExcelProperty(value = {"质量抽检信息", "AQL"}, index = 10)
    private String aql;

    @ExcelProperty(value = {"质量抽检信息", "合格数"}, index = 11)
    private String passNumber;

    @ExcelProperty(value = {"质量抽检信息", "不良数"}, index = 12)
    private String failNumber;

    @ExcelProperty(value = {"质量抽检信息", "检验员"}, index = 13)
    private String checkOperator;

    @ExcelProperty(value = {"质量抽检信息", "检验结果"}, index = 14)
    private String sampleResult;

    @ExcelProperty(value = {"质量抽检信息", "录入者"}, index = 15)
    private String enter;

    @ExcelProperty(value = {"质量抽检信息", "审核者"}, index = 16)
    private String checker;

    @ExcelProperty(value = {"质量抽检信息", "状态"}, index = 17)
    private String checkFlag;

    @ExcelProperty(value = {"质量抽检信息", "录入时间"}, index = 18)
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getPacker() {
        return packer;
    }

    public void setPacker(String packer) {
        this.packer = packer;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getAql() {
        return aql;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public String getFailNumber() {
        return failNumber;
    }

    public void setFailNumber(String failNumber) {
        this.failNumber = failNumber;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
    }

    public String getSampleResult() {
        return sampleResult;
    }

    public void setSampleResult(String sampleResult) {
        this.sampleResult = sampleResult;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QualitySamplingModel{" +
                "index='" + index + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", code='" + code + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", spec='" + spec + '\'' +
                ", packageNumber='" + packageNumber + '\'' +
                ", packer='" + packer + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", aql='" + aql + '\'' +
                ", passNumber='" + passNumber + '\'' +
                ", failNumber='" + failNumber + '\'' +
                ", checkOperator='" + checkOperator + '\'' +
                ", sampleResult='" + sampleResult + '\'' +
                ", enter='" + enter + '\'' +
                ", checker='" + checker + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
