package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.BillOfSaleHistoryModel;
import edu.yctc.erpsystem.excel.BillOfSaleOrderModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.BillOfSaleHistoryInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleHistoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 出货单历史逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("billOfSaleHistoryService")
public class BillOfSaleHistoryServiceImpl implements BillOfSaleHistoryInterService {

    // 过滤参数
    /**客户订单号*/
    private static final String ORDER_NUMBER = "orderNumber";
    /**客户代码*/
    private static final String CODE = "code";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 规格*/
    private static final String SPEC = "spec";
    /**客户料号*/
    private static final String PRODUCT_NUMBER = "productNumber";
    /**出货单号*/
    private static final String BILL_OF_SALE_NUMBER = "billOfSaleNumber";
    /** 发票抬头*/
    private static final String TITLE_NAME = "titleName";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private BillOfSaleHistoryDAO billOfSaleHistoryDAO;

    @Resource
    private BillOfSaleDAO billOfSaleDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private ProductExportDAO productExportDAO;

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Resource
    private ProductStorageDAO productStorageDAO;

    @Resource
    private InvoiceTitleDAO invoiceTitleDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Override
    public ResultDO<PageUtils<BillOfSaleHistoryVO>> getBillOfSaleHistory(Map<String, Object> params) {
        return CallbackUtils.getCallback("getBillOfSaleHistory", params.toString(), () -> {
            List<BillOfSaleHistoryVO> result = new ArrayList<>();

            // 得到所有出货单历史信息
            List<BillOfSaleHistoryDO> billOfSaleHistoryList = billOfSaleHistoryDAO.getBillOfSaleHistory(params);
            if (billOfSaleHistoryList == null) {
                logger.error("getBillOfSaleHistory exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == billOfSaleHistoryList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有发票抬头信息
            List<InvoiceTitleDO> invoiceTitleList = invoiceTitleDAO.getInvoiceTitleByIds(billOfSaleHistoryList.stream().map(BillOfSaleHistoryDO::getInvoiceTitleId).collect(Collectors.toList()));
            Map<String, InvoiceTitleDO> invoiceTitleMap = invoiceTitleList.stream().collect(Collectors.toMap(InvoiceTitleDO::getId, invoiceTitle -> invoiceTitle));

            // 得到所有出货单信息
            List<BillOfSaleDO> billOfSaleList = billOfSaleDAO.getBillOfSaleByIds(billOfSaleHistoryList.stream().map(BillOfSaleHistoryDO::getBillOfSaleId).collect(Collectors.toList()));
            Map<String, BillOfSaleDO> billOfSaleMap = billOfSaleList.stream().collect(Collectors.toMap(BillOfSaleDO::getId, billOfSale -> billOfSale));

            // 得到所有成品出库信息
            List<ProductExportDO> productExportList = productExportDAO.getProductExportByIds(billOfSaleList.stream().map(BillOfSaleDO::getProductExportId).collect(Collectors.toList()));
            Map<String, ProductExportDO> productExportMap = productExportList.stream().collect(Collectors.toMap(ProductExportDO::getId, productExport -> productExport));

            // 得到所有成品入库信息
            List<ProductInventoryDO> productInventoryList = productInventoryDAO.getProductInventoryByIds(productExportList.stream().map(ProductExportDO::getProductInventoryId).collect(Collectors.toList()));
            Map<String, ProductInventoryDO> productInventoryMap = productInventoryList.stream().collect(Collectors.toMap(ProductInventoryDO::getId, productInventory -> productInventory));

            // 得到所有成品库存信息
            List<ProductStorageDO> productStorageList = productStorageDAO.getProductStorageByIds(productInventoryList.stream().map(ProductInventoryDO::getProductStorageId).collect(Collectors.toList()));
            Map<String, ProductStorageDO> productStorageMap = productStorageList.stream().collect(Collectors.toMap(ProductStorageDO::getId, productStorage -> productStorage));

            // 得到所有制造流程单副表
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(productStorageList.stream().map(ProductStorageDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
            Map<String, ManufactureProcessSlaveDO> manufactureProcessSlaveMap = manufactureProcessSlaveList.stream().collect(Collectors.toMap(ManufactureProcessSlaveDO::getId, manufactureProcessSlave -> manufactureProcessSlave));

            // 得到所有制造流程单主表
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap(ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            // 得到客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 得到所有客户管理信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map(CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap(CustomerDO::getId, customer -> customer));

            // 得到所有成品原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProductByIds(customerOrderList.stream().map(CustomerOrderDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap(OriginalProductDO::getId, originalProduct -> originalProduct));

            for (BillOfSaleHistoryDO tempBillOfSaleHistory : billOfSaleHistoryList) {
                BillOfSaleHistoryVO temp = new BillOfSaleHistoryVO();

                String productExportId = billOfSaleMap.get(tempBillOfSaleHistory.getBillOfSaleId()).getProductExportId();
                String productInventoryId = productExportMap.get(productExportId).getProductInventoryId();
                String productStorageId = productInventoryMap.get(productInventoryId).getProductStorageId();
                String manufactureProcessSlaveId = productStorageMap.get(productStorageId).getManufactureProcessSlaveId();
                String manufactureProcessMasterId = manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getManufactureProcessMasterId();
                String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();

                temp.setId(tempBillOfSaleHistory.getId());
                temp.setBillOfSaleId(tempBillOfSaleHistory.getBillOfSaleId());
                temp.setCustomerOrderId(customerOrderId);
                temp.setCustomerId(customerId);
                temp.setOriginalProductId(originalProductId);
                temp.setInvoiceTitleId(tempBillOfSaleHistory.getInvoiceTitleId());
                temp.setOrderNumber(billOfSaleMap.get(tempBillOfSaleHistory.getBillOfSaleId()).getOrderNumber());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setProductNumber(billOfSaleMap.get(tempBillOfSaleHistory.getBillOfSaleId()).getProductNumber());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setExportAmount(productExportMap.get(productExportId).getAmount());
                temp.setUnitPrice(originalProductMap.get(originalProductId).getUnitPrice());
                temp.setUnit(originalProductMap.get(originalProductId).getUnit());
                temp.setTitleName(invoiceTitleMap.get(tempBillOfSaleHistory.getInvoiceTitleId()).getName());
                temp.setBillOfSaleNumber(tempBillOfSaleHistory.getNumber());
                temp.setBillOfSaleDate(tempBillOfSaleHistory.getDate());
                temp.setRemark(billOfSaleMap.get(tempBillOfSaleHistory.getBillOfSaleId()).getRemark());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(billOfSaleHistoryDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<BillOfSaleHistoryVO>> getBillOfSaleHistoryByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getBillOfSaleHistoryByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");

            ResultDO<PageUtils<BillOfSaleHistoryVO>> resultVO = getBillOfSaleHistory(params);

            if (resultVO == null) {
                logger.error("getBillOfSaleHistoryByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<BillOfSaleHistoryVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(PRODUCT_NUMBER)) {
                result = result.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(TITLE_NAME)) {
                result = result.stream().filter(item -> null != item.getTitleName() && item.getTitleName().toLowerCase().contains(params.get(TITLE_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(BILL_OF_SALE_NUMBER)) {
                result = result.stream().filter(item -> null != item.getBillOfSaleNumber() && item.getBillOfSaleNumber().toLowerCase().contains(params.get(BILL_OF_SALE_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> exportExcel(BillOfSaleHistoryVO[] billOfSaleHistoryVO) {
        if (null == billOfSaleHistoryVO || 0 == billOfSaleHistoryVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return CallbackUtils.exportCallback("BillOfSaleHistory exportExcel", Arrays.toString(billOfSaleHistoryVO), () -> {
            List<BillOfSaleHistoryModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int i = 1;
            for (BillOfSaleHistoryVO temp : billOfSaleHistoryVO) {
                BillOfSaleHistoryModel item = new BillOfSaleHistoryModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setBillOfSaleNumber(temp.getBillOfSaleNumber());
                item.setCode(temp.getCode());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setExportAmount(temp.getExportAmount());
                item.setUnit(temp.getUnit());
                item.setUnitPrice(temp.getUnitPrice());
                item.setMoney(temp.getMoney());
                item.setBillOfSaleDate(format.format(temp.getBillOfSaleDate()));
                item.setRemark(temp.getRemark());
                data.add(item);
            }

            Map<Integer, Integer> columnWidth = new HashMap<>(13);
            columnWidth.put(0, (int) (4.57 * 256));
            columnWidth.put(1, (int) (10.86 * 256));
            columnWidth.put(2, (int) (26.14 * 256));
            columnWidth.put(3, (int) (23.43 * 256));
            columnWidth.put(4, (int) (24.86 * 256));
            columnWidth.put(5, (int) (9.0 * 256));
            columnWidth.put(6, (int) (10.14 * 256));
            columnWidth.put(7, (int) (8.71 * 256));
            columnWidth.put(8, (int) (9.0 * 256));
            columnWidth.put(9, (int) (10.14 * 256));
            columnWidth.put(10, (int) (8.71 * 256));
            columnWidth.put(11, (int) (23.43 * 256));
            columnWidth.put(12, (int) (23.43 * 256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.BILL_OF_SALE_HISTORY_FILE_NAME, BillOfSaleHistoryModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> exportExcelByModel(List<BillOfSaleHistoryVO> billOfSaleHistoryVO) {
        if (null == billOfSaleHistoryVO || 0 == billOfSaleHistoryVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("BillOfSaleHistory", billOfSaleHistoryVO.toString(), () -> {
            InvoiceTitleDO invoiceTitleDO = invoiceTitleDAO.getInvoiceTitleById(billOfSaleHistoryVO.get(0).getInvoiceTitleId());

            // 导出excel
            Map<String, Object> map = new HashMap<>(9);

            map.put("titleName", invoiceTitleDO.getName());
            map.put("billOfSaleNumber", billOfSaleHistoryVO.get(0).getBillOfSaleNumber());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            map.put("orderDate", simpleDateFormat.format(new Date()));

            List<BillOfSaleOrderModel> billOfSaleHistoryModelList = new ArrayList<>();

            for (BillOfSaleHistoryVO tempBillOfSaleHistoryVO : billOfSaleHistoryVO) {
                BillOfSaleOrderModel billOfSaleOrderModel = new BillOfSaleOrderModel();

                billOfSaleOrderModel.setCode(tempBillOfSaleHistoryVO.getCode());
                billOfSaleOrderModel.setOrderNumber(tempBillOfSaleHistoryVO.getOrderNumber());
                billOfSaleOrderModel.setProductNumber(tempBillOfSaleHistoryVO.getProductNumber());
                billOfSaleOrderModel.setSpec(tempBillOfSaleHistoryVO.getSpec());
                billOfSaleOrderModel.setExportAmount(tempBillOfSaleHistoryVO.getExportAmount());
                billOfSaleOrderModel.setUnitPrice(tempBillOfSaleHistoryVO.getUnitPrice());
                billOfSaleOrderModel.setMoney(tempBillOfSaleHistoryVO.getMoney());

                billOfSaleHistoryModelList.add(billOfSaleOrderModel);
            }

            map.put("billOfSale", billOfSaleHistoryModelList);
            MyExcel.writeTemplate(ConstantHolder.BILL_OF_SALE_ORDER_FILE_NAME, "送货单（发，亿，永）.xlsx", map);
        });
    }

}
