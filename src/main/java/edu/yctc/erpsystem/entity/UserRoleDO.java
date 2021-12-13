package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 用户角色关系
 *
 * @author xiaotao
 */
public class UserRoleDO {

    /**
     * 主键
     */
    private String id;

    /**
     * syuesr的主键
     */
    private String syUserId;

    /**
     * syrole的主键
     */
    private String syRoleId;

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

    public String getSyRoleId() {
        return syRoleId;
    }

    public void setSyRoleId(String syRoleId) {
        this.syRoleId = syRoleId;
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
        return "UserRoleDO{" +
                "id='" + id + '\'' +
                ", syUserId='" + syUserId + '\'' +
                ", syRoleId='" + syRoleId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
