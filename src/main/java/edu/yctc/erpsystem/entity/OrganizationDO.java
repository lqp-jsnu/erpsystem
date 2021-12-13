package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 组织表
 *
 * @author lqp
 */
public class OrganizationDO {

    /**
     * 主键
     */
    private String id;

    /**
     * syorganization的主键（自连接 排序）
     */
    private String syOrganizationId;

    /**
     * 组织名字
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 机构编码
     */
    private String code;

    /**
     * 菜单项显示的图标
     */
    private String icons;

    /**
     * 显示的列表顺序（可以设置负数）
     */
    private Integer seq;

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

    public String getSyOrganizationId() {
        return syOrganizationId;
    }

    public void setSyOrganizationId(String syOrganizationId) {
        this.syOrganizationId = syOrganizationId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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
        return "OrganizationDO{" +
                "id='" + id + '\'' +
                ", syOrganizationId='" + syOrganizationId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", code='" + code + '\'' +
                ", icons='" + icons + '\'' +
                ", seq=" + seq +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
