package edu.yctc.erpsystem.vo;

/**
 * 制造流程单
 *
 * @author zzy
 */
public class ManufactureProcessSlaveVO {

    /**
     * t_manufactprosslave的主键
     */
    private String id;

    /**
     * 工作传票号
     */
    private String jobTicketNumber;

    /**
     * 客户编号
     */
    private String code;

    /**
     * 每单投产
     */
    private String everyAmount;

    /**
     * 规格
     */
    private String spec;
    /**
     * 品名
     */
    private String itemName;

    /**
     * 订单数量
     */
    private String everyOrderAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEveryAmount() {
        return everyAmount;
    }

    public void setEveryAmount(String everyAmount) {
        this.everyAmount = everyAmount;
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

    public String getEveryOrderAmount() {
        return everyOrderAmount;
    }

    public void setEveryOrderAmount(String everyOrderAmount) {
        this.everyOrderAmount = everyOrderAmount;
    }

    @Override
    public String toString() {
        return "ManufactProcessSlaveVO{" +
                "id='" + id + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", code='" + code + '\'' +
                ", everyAmount='" + everyAmount + '\'' +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                '}';
    }

}
