package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 组织资源关系
 *
 * @author lqp
 */
public class OrganizationResourceDO {

    /**
     * 主键
     */
    private String id;

    /**
     * sy_organization的主键
     */
    private String syOrganizationId;

    /**
     * sy_resource的主键
     */
    private String syResourceId;

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

    public String getSyResourceId() {
        return syResourceId;
    }

    public void setSyResourceId(String syResourceId) {
        this.syResourceId = syResourceId;
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
        return "OrganizationResource{" +
                "id='" + id + '\'' +
                ", syOrganizationId='" + syOrganizationId + '\'' +
                ", syResourceId='" + syResourceId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
