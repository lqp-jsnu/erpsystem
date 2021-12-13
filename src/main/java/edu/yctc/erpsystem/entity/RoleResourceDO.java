package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 角色资源关系
 *
 * @author lqp
 */
public class RoleResourceDO {

    /**
     * 主键
     */
    private String id;

    /**
     * syrole的主键
     */
    private String syRoleId;

    /**
     * syresource的主键
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

    public String getSyRoleId() {
        return syRoleId;
    }

    public void setSyRoleId(String syRoleId) {
        this.syRoleId = syRoleId;
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
        return "RoleResourceDO{" +
                "id='" + id + '\'' +
                ", syRoleId='" + syRoleId + '\'' +
                ", syResourceId='" + syResourceId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
