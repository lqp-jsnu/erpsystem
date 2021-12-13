package edu.yctc.erpsystem.vo;


import java.util.Date;

/**
 * 制造流程单详细 已列印流程单
 *
 * @author smile
 */
public class DetailedManufactureProcessSlaveVO {

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

    /**
     * t_customerorder的主键
     */
    private String customerOrderId;

    /**
     * t_originalproduct的主键
     */
    private String originalProductId;

    /**
     * t_customer的主键
     */
    private String customerId;

    /**
     * 客户订单号
     */
    private String orderNumber;

    /**
     * 产品料号，不可以重复
     */
    private String productNumber;

    /**
     * '品名／磁棒／尺寸(材质)'
     */
    private String masterItemName;

    /**
     * '规格/初值/电阻线(线径)'
     */
    private String masterSpec;

    /**
     * 订购数量
     */
    private String orderAmount;

    /**
     * 投产数量
     */
    private String productAmount;

    /**
     * 采购日期
     */
    private Date orderDate;

    /**
     * 交货日期
     */
    private Date deliveryDate;

    /**
     * 是否补单
     */
    private String isIssueOrder;

    /**
     * 是否入库
     */
    private String isInHouse;

    /**
     * 是否进入生成
     */
    private  String isIntoMake;

    /**
     * 是否生成流程单
     */
    private String isGenerateManufacture;

    /**
     * 是否出库
     */
    private String isMaterialExport;

    /**
     * 出库是否审核通过
     */
    private String isExportCheckPass;

    /**
     * 库存数量
     */
    private String storageAmount;

    /**
     * 发料数量
     */
    private String issueNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * t_materialinventorymaster的主键
     */
    private String materialInventoryMasterId;

    /**
     * t_materialinfomaster的主键
     */
    private String materialInfoMasterId;

    /**
     * 库存数量
     */
    private String leftAmount;

    /**
     * 出库日期
     */
    private Date exportDate;

    /**
     * 出库单号
     */
    private String exportNumber;

    /**
     * syuser的主键
     */
    private String userId;

    /**
     * 审核标记
     */
    private String checkFlag;

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

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(String originalProductId) {
        this.originalProductId = originalProductId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getMasterItemName() {
        return masterItemName;
    }

    public void setMasterItemName(String masterItemName) {
        this.masterItemName = masterItemName;
    }

    public String getMasterSpec() {
        return masterSpec;
    }

    public void setMasterSpec(String masterSpec) {
        this.masterSpec = masterSpec;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getIsIssueOrder() {
        return isIssueOrder;
    }

    public void setIsIssueOrder(String isIssueOrder) {
        this.isIssueOrder = isIssueOrder;
    }

    public String getIsInHouse() {
        return isInHouse;
    }

    public void setIsInHouse(String isInHouse) {
        this.isInHouse = isInHouse;
    }

    public String getIsIntoMake() {
        return isIntoMake;
    }

    public void setIsIntoMake(String isIntoMake) {
        this.isIntoMake = isIntoMake;
    }

    public String getIsGenerateManufacture() {
        return isGenerateManufacture;
    }

    public void setIsGenerateManufacture(String isGenerateManufacture) {
        this.isGenerateManufacture = isGenerateManufacture;
    }

    public String getIsMaterialExport() {
        return isMaterialExport;
    }

    public void setIsMaterialExport(String isMaterialExport) {
        this.isMaterialExport = isMaterialExport;
    }

    public String getIsExportCheckPass() {
        return isExportCheckPass;
    }

    public void setIsExportCheckPass(String isExportCheckPass) {
        this.isExportCheckPass = isExportCheckPass;
    }

    public String getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(String storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaterialInventoryMasterId() {
        return materialInventoryMasterId;
    }

    public void setMaterialInventoryMasterId(String materialInventoryMasterId) {
        this.materialInventoryMasterId = materialInventoryMasterId;
    }

    public String getMaterialInfoMasterId() {
        return materialInfoMasterId;
    }

    public void setMaterialInfoMasterId(String materialInfoMasterId) {
        this.materialInfoMasterId = materialInfoMasterId;
    }

    public String getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(String leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public String getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(String exportNumber) {
        this.exportNumber = exportNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    @Override
    public String toString() {
        return "DetailedManufactureProcessSlaveVO{" +
                "id='" + id + '\'' +
                ", jobTicketNumber='" + jobTicketNumber + '\'' +
                ", code='" + code + '\'' +
                ", everyAmount='" + everyAmount + '\'' +
                ", spec='" + spec + '\'' +
                ", itemName='" + itemName + '\'' +
                ", everyOrderAmount='" + everyOrderAmount + '\'' +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", originalProductId='" + originalProductId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", masterItemName='" + masterItemName + '\'' +
                ", masterSpec='" + masterSpec + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", productAmount='" + productAmount + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", isIssueOrder='" + isIssueOrder + '\'' +
                ", isInHouse='" + isInHouse + '\'' +
                ", isIntoMake='" + isIntoMake + '\'' +
                ", isGenerateManufacture='" + isGenerateManufacture + '\'' +
                ", isMaterialExport='" + isMaterialExport + '\'' +
                ", isExportCheckPass='" + isExportCheckPass + '\'' +
                ", storageAmount='" + storageAmount + '\'' +
                ", issueNumber='" + issueNumber + '\'' +
                ", remark='" + remark + '\'' +
                ", materialInventoryMasterId='" + materialInventoryMasterId + '\'' +
                ", materialInfoMasterId='" + materialInfoMasterId + '\'' +
                ", leftAmount='" + leftAmount + '\'' +
                ", exportDate=" + exportDate +
                ", exportNumber='" + exportNumber + '\'' +
                ", userId='" + userId + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                '}';
    }

}
