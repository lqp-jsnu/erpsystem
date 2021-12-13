package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 仓库管理视图
 *
 * @author qiang
 */
public class WarehouseVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库类型
     */
    private String type;

    /**
     * 是否为空仓库
     */
    private String isZero;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 仓库用途键
     */
    private String use;

    /**
     * 仓库管理员 syuser的主键
     */
    private String syUserId;

    /**
     * 仓库管理员名字
     */
    private String houseManager;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsZero() {
        return isZero;
    }

    public void setIsZero(String isZero) {
        this.isZero = isZero;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getSyUserId() {
        return syUserId;
    }

    public void setSyUserId(String syUserId) {
        this.syUserId = syUserId;
    }

    public String getHouseManager() {
        return houseManager;
    }

    public void setHouseManager(String houseManager) {
        this.houseManager = houseManager;
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
        return "WarehouseVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isZero='" + isZero + '\'' +
                ", address='" + address + '\'' +
                ", use='" + use + '\'' +
                ", syUserId='" + syUserId + '\'' +
                ", houseManager='" + houseManager + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
