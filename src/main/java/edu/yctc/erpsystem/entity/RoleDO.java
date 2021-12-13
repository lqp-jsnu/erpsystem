package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 角色分类表
 *
 * @author xiaotao
 */
public class RoleDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 菜单显示的图标
     */
    private String icons;

    /**
     * 角色权限描述
     */
    private String description;

    /**
     * 顺序
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "RoleDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icons='" + icons + '\'' +
                ", description='" + description + '\'' +
                ", seq=" + seq +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
