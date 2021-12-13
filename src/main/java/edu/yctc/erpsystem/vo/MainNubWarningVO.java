package edu.yctc.erpsystem.vo;

/**
 * 首页产品数量预警视图
 *
 * @author qiang
 */
public class MainNubWarningVO {

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
     * 检测结果
     * */
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MainNubWarningVO{" +
                "id='" + id + '\'' +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
