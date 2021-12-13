package edu.yctc.erpsystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 出货单excel实体类
 *
 * @author zzy
 */
public class BillOfSaleModel extends BaseRowModel {

    @ExcelProperty(value = {"出货单信息", "序号"}, index = 0)
    private String index;

    @ExcelProperty(value = {"出货单信息", "客户代码"}, index = 1)
    private String code;

    @ExcelProperty(value = {"出货单信息", "订单号"}, index = 2)
    private String orderNumber;

    @ExcelProperty(value = {"出货单信息", "客户料号"}, index = 3)
    private String productNumber;

    @ExcelProperty(value = {"出货单信息", "规格"}, index = 4)
    private String spec;

    @ExcelProperty(value = {"出货单信息", "数量"}, index = 5)
    private String exportAmount;

    @ExcelProperty(value = {"出货单信息", "单价"}, index = 6)
    private String unitPrice;

    @ExcelProperty(value = {"出货单信息", "金额"}, index = 7)
    private String money;

    @ExcelProperty(value = {"出货单信息", "备注"}, index = 8)
    private String remark;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BillOfSaleModel{" +
                "index='" + index + '\'' +
                ", code='" + code + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", spec='" + spec + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
