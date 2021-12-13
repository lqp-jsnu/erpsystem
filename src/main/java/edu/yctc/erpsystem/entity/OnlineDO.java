package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 用户登陆表
 *
 * @author lqp
 */
public class OnlineDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 登陆名称（对应syuser的login_name）
     */
    private String loginName;

    /**
     * 登陆地址
     */
    private String ip;

    /**
     * 登陆类型（1：登陆 0：注销）
     */
    private String type;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OnlineDO{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", ip='" + ip + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
