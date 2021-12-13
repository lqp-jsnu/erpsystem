package edu.yctc.erpsystem.vo;

import java.util.Date;

/**
 * 首页材料保质期监控视图
 *
 * @author qiang
 */
public class MainMaterialExpiryDateVO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_materialinfomaster的主键
     */
    private String materialInfoMasterId;

    /**
     * 入库时间
     */
    private Date storageDate;

    /**
     * 规格
     */
    private String spec;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 过期/剩余天数
     */
    private String date;

    /**
     * 过期/剩余天数(整型)
     */
    private Integer leaveDate;

    /**
     * 检测结果
     */
    private String result;

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

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Integer leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MainMaterialExpiryDateVO{" +
                "id='" + id + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", storageDate=" + storageDate +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", date='" + date + '\'' +
                ", leaveDate=" + leaveDate +
                ", result='" + result + '\'' +
                '}';
    }

}
