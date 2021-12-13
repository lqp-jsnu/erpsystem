package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 数据库备份表
 *
 * @author lqp
 */
public class DbBackUpDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 备份路径
     */
    private String fileUrl;

    /**
     * 数据库名称
     */
    private String fileName;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DbBackUpDO{" +
                "id='" + id + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileName='" + fileName + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
