package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 供应商信息表
 *
 * @author xiaotao
 */
public class SupplierDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 供应商代号
     */
    private String code;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应产品类别
     */
    private String productCategory;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 固定电话
     */
    private String fixedTelephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 联系方式
     */
    private String mobileTelephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 结款方式
     */
    private String payment;

    /**
     * 删除标识
     */
    private String deleteFlag;

    /**
     * 材料交期
     */
    private String deliveryTime;

    /**
     * 客户说明
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

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SupplierDO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", contact='" + contact + '\'' +
                ", fixedTelephone='" + fixedTelephone + '\'' +
                ", fax='" + fax + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", payment='" + payment + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
