package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 客户信息表
 *
 * @author lqp
 */
public class CustomerDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 客户代号
     */
    private String code;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 付款条件
     */
    private String payType;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 固定电话
     */
    private String fixedTelephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 移动电话
     */
    private String mobileTelephone;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 删除标识
     */
    private String deleteFlag;

    /**
     * 客户交付时间
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

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CustomerDO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", name='" + name + '\'' +
                ", payType='" + payType + '\'' +
                ", contact='" + contact + '\'' +
                ", fixedTelephone='" + fixedTelephone + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", address='" + address + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
