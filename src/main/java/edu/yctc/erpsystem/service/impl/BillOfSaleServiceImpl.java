package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.BillOfSaleImportModel;
import edu.yctc.erpsystem.excel.BillOfSaleModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.BillOfSaleInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.BillOfSaleVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 出货单信息逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("billOfSaleService")
public class BillOfSaleServiceImpl implements BillOfSaleInterService {

    /** excel文件名 */
    private static final String EXCEL_FILE_NAME = "billOfSale.xlsx";

    //过滤参数
    /** 客户订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 客户代码 */
    private static final String CODE = "code";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";
    /** 客户料号 */
    private static final String PRODUCT_NUMBER = "productNumber";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private BillOfSaleHistoryDAO billOfSaleHistoryDAO;

    @Resource
    private BillOfSaleDAO billOfSaleDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private ProductExportDAO productExportDAO;

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Resource
    private ProductStorageDAO productStorageDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Override
    public ResultDO<PageUtils<BillOfSaleVO>> getBillOfSale(Map<String, Object> params){
        return CallbackUtils.getCallback("getBillOfSale", params.toString(), () -> {
            List<BillOfSaleVO> result = new ArrayList<>();

            // 得到所有出货单历史信息
            List<BillOfSaleDO> billOfSaleList = billOfSaleDAO.getBillOfSale(params);
            if (billOfSaleList == null) {
                logger.error("getBillOfSale exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == billOfSaleList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

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

            for (BillOfSaleDO tempBillOfSale : billOfSaleList) {
                BillOfSaleVO temp = new BillOfSaleVO();

                String productInventoryId = productExportMap.get(tempBillOfSale.getProductExportId()).getProductInventoryId();
                String productStorageId = productInventoryMap.get(productInventoryId).getProductStorageId();
                String manufactureProcessSlaveId = productStorageMap.get(productStorageId).getManufactureProcessSlaveId();
                String manufactureProcessMasterId = manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getManufactureProcessMasterId();
                String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();

                temp.setId(tempBillOfSale.getId());
                temp.setOrderNumber(tempBillOfSale.getOrderNumber());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setProductNumber(tempBillOfSale.getProductNumber());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setExportAmount(productExportMap.get(tempBillOfSale.getProductExportId()).getAmount());
                temp.setUnit(originalProductMap.get(originalProductId).getUnit());
                temp.setUnitPrice(originalProductMap.get(originalProductId).getUnitPrice());
                temp.setRemark(tempBillOfSale.getRemark());

                result.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(billOfSaleDAO.count(params), result));
        });

    }

    @Override
    public ResultDO<PageUtils<BillOfSaleVO>> getBillOfSaleByConditions(Map<String, Object> params){
        return CallbackUtils.getCallback("getBillOfSaleByConditions", params.toString(), () -> {
            //获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");

            ResultDO<PageUtils<BillOfSaleVO>> resultVO = getBillOfSale(params);

            if (resultVO == null) {
                logger.error("getBillOfSaleByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<BillOfSaleVO> result = resultVO.getModule().getRows();

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

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(BillOfSaleHistoryDO billOfSaleHistoryDO) {
        if (StringUtils.isBlank(billOfSaleHistoryDO.getInvoiceTitleId()) || StringUtils.isBlank(billOfSaleHistoryDO.getNumber()) || StringUtils.isBlank(billOfSaleHistoryDO.getDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("BillOfSaleHistory", billOfSaleHistoryDO.toString(), () -> {
            billOfSaleHistoryDAO.insert(billOfSaleHistoryDO);
            billOfSaleDAO.changeExportStatusById(billOfSaleHistoryDO.getBillOfSaleId());
        });
    }

    @Override
    public  ResultDO<Void> updateRemarkById(BillOfSaleDO billOfSaleDO) {
        if (StringUtils.isBlank(billOfSaleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateRemarkById", billOfSaleDO.toString(), () -> billOfSaleDAO.updateRemarkById(billOfSaleDO));
    }

    @Override
    public ResultDO<Void> exportExcel(BillOfSaleVO[] billOfSalesVO) {
        if (null == billOfSalesVO || 0 == billOfSalesVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("BillOfSale exportExcel", Arrays.toString(billOfSalesVO), () ->{
            List<BillOfSaleModel> data = new ArrayList<>();

            int i = 1;
            for (BillOfSaleVO temp : billOfSalesVO) {
                BillOfSaleModel item = new BillOfSaleModel();

                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setProductNumber(temp.getProductNumber());
                item.setSpec(temp.getSpec());
                item.setExportAmount(temp.getExportAmount());
                item.setUnitPrice(temp.getUnitPrice());
                item.setMoney(temp.getMoney());
                item.setRemark(temp.getRemark());

                data.add(item);
            }
            Map<Integer,Integer> columnWidth = new HashMap<>(9);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(9.0*256));
            columnWidth.put(6,(int)(10.14*256));
            columnWidth.put(7,(int)(8.71*256));
            columnWidth.put(8,(int)(9.0*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.BILL_OF_SALE_FILE_NAME, BillOfSaleModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> importExcel() {
        ResultDO<List<Object>> resultDO = MyExcel.read(ConstantHolder.FILE_SAVE_PATH + EXCEL_FILE_NAME, BillOfSaleImportModel.class);

        if (resultDO.isSuccess()) {
            List<BillOfSaleDO> billOfSaleList = new ArrayList<>();

            for (Object temp : resultDO.getModule()) {
                BillOfSaleDO billOfSaleDO = new BillOfSaleDO();

                BillOfSaleImportModel billOfSaleImportModel = (BillOfSaleImportModel) (temp);
                if (StringUtils.isNumeric(billOfSaleImportModel.getIndex())) {

                    billOfSaleDO.setProductExportId(null);
                    billOfSaleDO.setRemark(billOfSaleImportModel.getRemark());
                    billOfSaleDO.setOrderNumber(billOfSaleImportModel.getOrderNumber());
                    billOfSaleDO.setProductNumber(billOfSaleImportModel.getProductNumber());

                    billOfSaleList.add(billOfSaleDO);
                }
            }

            billOfSaleDAO.insertAll(billOfSaleList);
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } else {
            return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
        }
    }

}
