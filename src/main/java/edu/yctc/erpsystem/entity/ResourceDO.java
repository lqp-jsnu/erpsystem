package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 资源表
 *
 * @author lqp
 */
public class ResourceDO {

    /**
     * 主键
     */
    private String id;

    /**
     * syresource的主键（自连接 排序）
     */
    private String syResourceId;

    /**
     * syresourcetype的主键（资源类型分类 主页面显示导航栏）
     */
    private String syResourceTypeId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 菜单项显示的图标
     */
    private String icons;

    /**
     * 资源链接（资源请求路径）
     */
    private String url;

    /**
     * 资源功能描述
     */
    private String description;

    /**
     * 资源管理对应的列表顺序
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

    public String getSyResourceId() {
        return syResourceId;
    }

    public void setSyResourceId(String syResourceId) {
        this.syResourceId = syResourceId;
    }

    public String getSyResourceTypeId() {
        return syResourceTypeId;
    }

    public void setSyResourceTypeId(String syResourceTypeId) {
        this.syResourceTypeId = syResourceTypeId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "ResourceDO{" +
                "id='" + id + '\'' +
                ", syResourceId='" + syResourceId + '\'' +
                ", syResourceTypeId='" + syResourceTypeId + '\'' +
                ", name='" + name + '\'' +
                ", icons='" + icons + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", seq=" + seq +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
