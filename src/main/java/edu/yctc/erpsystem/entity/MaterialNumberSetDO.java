package edu.yctc.erpsystem.entity;

import java.util.Date;

/**
 * 原材料数量设置
 *
 * @author lqp
 */
public class MaterialNumberSetDO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialinfomaster的主键（原材料信息）
     */
    private String materialInfoMasterId;

    /**
     * 告警最小值
     */
    private String minNumber;

    /**
     * 告警最大值
     */
    private String maxNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    public String getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "MaterialNumberSetDO{" +
                "id='" + id + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", minNumber='" + minNumber + '\'' +
                ", maxNumber='" + maxNumber + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
