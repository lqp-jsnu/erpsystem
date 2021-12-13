package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 角色视图
 *
 * @author xcg
 */
public class UserVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 照片
     */
    private String photo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 密码（未加密）
     */
    private String pwd;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    // 角色 组织

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
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
        return "UserVO{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", pwd='" + pwd + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
