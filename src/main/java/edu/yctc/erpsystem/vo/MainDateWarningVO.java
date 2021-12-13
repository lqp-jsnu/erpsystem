package edu.yctc.erpsystem.vo;

/**
 * 首页保质期视图
 *
 * @author qiang
 */
public class MainDateWarningVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 规格
     */
    private String spec;

    /**
     * 品名，可以重复
     */
    private String itemName;

    /**
     * 过期/剩余天数(整型)
     * */
    private Integer leaveDate;

    /**
     * 过期/剩余天数
     * */
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Integer leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MainDateWarningVO{" +
                "id='" + id + '\'' +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", leaveDate=" + leaveDate +
                ", date='" + date + '\'' +
                '}';
    }
}
