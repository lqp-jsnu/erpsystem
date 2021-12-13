package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 客户管理excel实体类
 *
 * @author qiang
 */
public class CustomerModel extends BaseRowModel {

    @ExcelProperty(value = {"客户信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"客户信息", "客户代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"客户信息", "客户名称"}, index = 2)
    private String name;

    @ExcelProperty(value = {"客户信息", "客户地址"}, index = 3)
    private String address;

    @ExcelProperty(value = {"客户信息", "供应商编码"}, index = 4)
    private String supplierCode;

    @ExcelProperty(value = {"客户信息", "联系人"}, index = 5)
    private String contact;

    @ExcelProperty(value = {"客户信息", "移动电话"}, index = 6)
    private String mobileTelephone;

    @ExcelProperty(value = {"客户信息", "固定电话"}, index = 7)
    private String fixedTelephone;

    @ExcelProperty(value = {"客户信息", "传真"}, index = 8)
    private String fax;

    @ExcelProperty(value = {"客户信息", "邮件地址"}, index = 9)
    private String email;

    @ExcelProperty(value = {"客户信息", "结款方式"}, index = 10)
    private String payType;

    @ExcelProperty(value = {"客户信息", "交货周期"}, index = 11)
    private String deliveryTime;

    @ExcelProperty(value = {"客户信息", "备注"}, index = 12)
    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

    public String getFixedTelephone() {
        return fixedTelephone;
    }

    public void setFixedTelephone(String fixedTelephone) {
        this.fixedTelephone = fixedTelephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", contact='" + contact + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", fixedTelephone='" + fixedTelephone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", payType='" + payType + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
