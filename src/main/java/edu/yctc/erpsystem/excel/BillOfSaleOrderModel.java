package edu.yctc.erpsystem.excel;

/**
 * 送货单excel实体类
 *
 * @author zzy
 */
public class BillOfSaleOrderModel {

    /** 客户代号 */
    private String code;

    /** 客户订单号 */
    private String orderNumber;

    /** 客户料号 */
    private String productNumber;

    /** 规格 */
    private String spec;

    /** 数量 */
    private String exportAmount;

    /** 单价 */
    private String unitPrice;

    /** 金额*/
    private String money;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getExportAmount() {
        return exportAmount;
    }

    public void setExportAmount(String exportAmount) {
        this.exportAmount = exportAmount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "BillOfSaleOrderModel{" +
                "code='" + code + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", spec='" + spec + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

}
