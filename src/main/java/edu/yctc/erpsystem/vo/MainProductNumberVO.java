package edu.yctc.erpsystem.vo;

/**
 * 首页产品数量监控视图
 *
 * @author qiang
 */
public class MainProductNumberVO {

    /**
     * 主键
     */
    private String id;

    /**
     * t_originalproduct的主键
     */
    private String originalProductId;

    /**
     * 库存数量
     */
    private String storageAmount;

    /**
     * 产品料号，不可以重复
     */
    private String productNumber;

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
     */
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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
        return "MainProductNumberVO{" +
                "id='" + id + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

}
