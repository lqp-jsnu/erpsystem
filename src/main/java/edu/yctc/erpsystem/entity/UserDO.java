package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 用户信息表
 *
 * @author lqp
 */
public class UserDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 密码（MD5加密）
     */
    private String pwd;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像（保存在C:\ERPSystem\img）
     */
    private String photo;

    /**
     * 性别（1：男 0：女）
     */
    private String sex;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        return "UserDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
