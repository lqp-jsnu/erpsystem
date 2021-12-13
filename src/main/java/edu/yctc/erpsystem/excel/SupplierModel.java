package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 供应商管理excel实体类
 *
 * @author qiang
 */
public class SupplierModel extends BaseRowModel {

    @ExcelProperty(value = {"供应商信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"供应商信息", "供应商代号"}, index = 1)
    private String code;

    @ExcelProperty(value = {"供应商信息", "供应商名称"}, index = 2)
    private String name;

    @ExcelProperty(value = {"供应商信息", "供应产品类别"}, index = 3)
    private String productCategory;

    @ExcelProperty(value = {"供应商信息", "联系人"}, index = 4)
    private String contact;

    @ExcelProperty(value = {"供应商信息", "移动电话"}, index = 5)
    private String mobileTelephone;

    @ExcelProperty(value = {"供应商信息", "固定电话"}, index = 6)
    private String fixedTelephone;

    @ExcelProperty(value = {"供应商信息", "传真"}, index = 7)
    private String fax;

    @ExcelProperty(value = {"供应商信息", "邮件地址"}, index = 8)
    private String email;

    @ExcelProperty(value = {"供应商信息", "供应商地址"}, index = 9)
    private String address;

    @ExcelProperty(value = {"供应商信息", "材料交期"}, index = 10)
    private String deliveryTime;

    @ExcelProperty(value = {"供应商信息", "结款方式"}, index = 11)
    private String payment;

    @ExcelProperty(value = {"供应商信息", "备注"}, index = 12)
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SupplierModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", contact='" + contact + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", fixedTelephone='" + fixedTelephone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", payment='" + payment + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
