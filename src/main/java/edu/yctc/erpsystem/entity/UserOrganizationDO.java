package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 用户机构关系
 *
 * @author xiaotao
 */
public class UserOrganizationDO {

    /**
     * 主键
     */
    private String id;

    /**
     * syuesr的主键
     */
    private String syUserId;

    /**
     * syorganization的主键
     */
    private String syOrganizationId;

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

    public String getSyUserId() {
        return syUserId;
    }

    public void setSyUserId(String syUserId) {
        this.syUserId = syUserId;
    }

    public String getSyOrganizationId() {
        return syOrganizationId;
    }

    public void setSyOrganizationId(String syOrganizationId) {
        this.syOrganizationId = syOrganizationId;
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
        return "UserOrganizationDO{" +
                "id='" + id + '\'' +
                ", syUserId='" + syUserId + '\'' +
                ", syOrganizationId='" + syOrganizationId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
