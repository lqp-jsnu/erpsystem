package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialExportDetailModel;
import edu.yctc.erpsystem.excel.MaterialExportModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialExportInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 材料出库信息逻辑接口实现
 *
 * @author wjd
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialExportService")
public  class MaterialExportServiceImpl implements MaterialExportInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    // 过滤参数
    /** 供应商代号 */
    private static final String SUPPLIER_CODE = "supplierCode";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 审核标志 */
    private static final String CHECk_FLAG = "checkFlag";
    /** 流程单号 */
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";
    /** 客户订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 出库单号 */
    private static final String EXPORT_NUMBER = "exportNumber";
    /** 客户代号 */
    private static final String CODE = "code";

    private static final String CHECK_FLAG = "已通过";

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private MaterialExportDAO materialExportDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private MaterialExportDetailDAO materialExportDetailDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private ScheduleDAO scheduleDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private MaterialStorageDAO materialStorageDAO;

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Override
    public ResultDO<PageUtils<MaterialExportVO>> getMaterialExport(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialExport", params.toString(), () -> {
            List<MaterialExportVO> result = new ArrayList<>();

            // 得到所有材料出库信息
            List<MaterialExportDO> materialExportList = materialExportDAO.getMaterialExport(params);
            if (materialExportList == null) {
                logger.error("getMaterialExport exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == materialExportList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有制造流程单副表信息
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(materialExportList.stream().map(MaterialExportDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
            Map<String, ManufactureProcessSlaveDO> manufactureProcessSlaveMap = manufactureProcessSlaveList.stream().collect(Collectors.toMap(ManufactureProcessSlaveDO::getId, manufactureProcessSlave -> manufactureProcessSlave));

            // 获得所有制造流程单主表信息
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap(ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            // 获得所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 获得所有成品原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProductByIds( customerOrderList.stream().map( CustomerOrderDO::getOriginalProductId ).collect( Collectors.toList() ) );
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect( Collectors.toMap( OriginalProductDO::getId, originalProduct -> originalProduct ) );

            // 获得所有客户订单信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds( customerOrderList.stream().map( CustomerOrderDO::getCustomerId ).collect( Collectors.toList() ) );
            Map<String, CustomerDO> customerMap = customerList.stream().collect( Collectors.toMap( CustomerDO::getId, customer -> customer ) );

            // 获得所有材料信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds( originalProductList.stream().map( OriginalProductDO::getMaterialInfoMasterId ).collect( Collectors.toList() ) );
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect( Collectors.toMap( MaterialInfoMasterDO::getId, customer -> customer ) );

            // 获得所有录入者信息
            List<UserDO> enterList = userDAO.getUserByIds(materialExportList.stream().map(MaterialExportDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 获得所有审核者信息
            List<UserDO> checkerList = userDAO.getUserByIds(materialExportList.stream().map(MaterialExportDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            for (MaterialExportDO tempMaterialExport : materialExportList) {
                MaterialExportVO temp = new MaterialExportVO();

                String manufactureProcessMasterId = manufactureProcessSlaveMap.get(tempMaterialExport.getManufactureProcessSlaveId()).getManufactureProcessMasterId();
                String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();
                String materialInfoMasterId = originalProductMap.get(originalProductId).getMaterialInfoMasterId();

                temp.setId(tempMaterialExport.getId());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(tempMaterialExport.getManufactureProcessSlaveId()).getJobTicketNumber());
                temp.setExportNumber(tempMaterialExport.getNumber());
                temp.setExportDate(tempMaterialExport.getDate());
                temp.setCreateTime(tempMaterialExport.getCreateTime());
                temp.setOrderNumber(customerOrderMap.get(customerOrderId).getOrderNumber());
                temp.setName(customerMap.get(customerId).getName());
                temp.setUser(enterMap.get(tempMaterialExport.getUser()).getName());
                temp.setIssueNumber(tempMaterialExport.getIssueNumber());
                temp.setCheckFlag(tempMaterialExport.getCheckFlag());
                temp.setCode(customerMap.get(customerId).getCode());
                if (null != checkerMap.get(tempMaterialExport.getChecker())) {
                    temp.setChecker(checkerMap.get(tempMaterialExport.getChecker()).getName());
                }

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialExportDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialExportVO>> getMaterialExportByConditions(Map<String, Object> params){
        return CallbackUtils.getCallback("getMaterialExportByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialExportVO>> resultVO = getMaterialExport(params);
            if (resultVO == null) {
                logger.error("getMaterialExportByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialExportVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(SUPPLIER_CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(SUPPLIER_CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CHECk_FLAG)) {
                result = result.stream().filter(item -> null != item.getCheckFlag() && item.getCheckFlag().equals(params.get(CHECk_FLAG).toString())).collect(Collectors.toList());
            }

            if (params.containsKey(JOB_TICKET_NUMBER)) {
                result = result.stream().filter(item -> null != item.getJobTicketNumber() && item.getJobTicketNumber().toLowerCase().contains(params.get(JOB_TICKET_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(EXPORT_NUMBER)) {
                result = result.stream().filter(item -> null != item.getExportNumber() && item.getExportNumber().toLowerCase().contains(params.get(EXPORT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> delete(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.deleteCallback("MaterialExport delete", id, () -> {
            DecimalFormat df = new DecimalFormat("0.00");
            List<MaterialInventoryDO> materialInventoryList = new ArrayList<>();
            MaterialExportDO materialExport = materialExportDAO.getMaterialExportById(id);
            // 删除前更新制造流程单详细数据
            manufactureProcessSlaveDAO.updateIsMaterialExport(materialExport.getManufactureProcessSlaveId(), "否");
            // 删除前更新库存数量,并删除详细信息
            List<MaterialExportDetailDO> materialExportDetail = materialExportDetailDAO.getMaterialExportDetailByMaterialExportId(id);
            List<MaterialInventoryDO> materialInventoryDO = materialInventoryDAO.getMaterialInventoryByIds(materialExportDetail.stream().map(MaterialExportDetailDO::getMaterialInventoryId).collect(Collectors.toList()));
            for (MaterialExportDetailDO tempMaterialExportDetail : materialExportDetail) {
                for (MaterialInventoryDO tempMaterialInventoryDO : materialInventoryDO) {
                    if (tempMaterialExportDetail.getMaterialInventoryId().equals(tempMaterialInventoryDO.getId())) {
                        MaterialInventoryDO materialInventory = new MaterialInventoryDO();

                        materialInventory.setLeftAmount(df.format(Float.parseFloat(tempMaterialInventoryDO.getLeftAmount()) + Float.parseFloat(tempMaterialExportDetail.getAmount())));
                        materialInventory.setId(tempMaterialInventoryDO.getId());

                        materialInventoryList.add(materialInventory);
                    }
                }
            }
            materialExportDetailDAO.deleteAll(materialExportDetail.stream().map(MaterialExportDetailDO::getId).collect(Collectors.toList()));
            materialInventoryDAO.updateAllLeftAmountByIds(materialInventoryList);

            // 删除出库主记录
            materialExportDAO.delete(id);
        });
    }

    @Override
    public ResultDO<Void> updateCheckerById(MaterialExportDO materialExportDO) {
        if (StringUtils.isBlank(materialExportDO.getId()) || StringUtils.isBlank(materialExportDO.getChecker()) || StringUtils.isBlank(materialExportDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("MaterialExport updateCheckerById", materialExportDO.toString(), () -> {
            MaterialExportDO materialExport = materialExportDAO.getMaterialExportById(materialExportDO.getId());

            if (CHECK_FLAG.equals(materialExportDO.getCheckFlag())) {
                // 更新制造流程单数据
                manufactureProcessSlaveDAO.updateIsIntoMake(materialExport.getManufactureProcessSlaveId());
                manufactureProcessSlaveDAO.updateIsExportCheckPass(materialExport.getManufactureProcessSlaveId());

                ManufactureProcessSlaveDO manufactureProcessSlave = manufactureProcessSlaveDAO.getManufactureProcessSlaveById(materialExport.getManufactureProcessSlaveId());
                ManufactureProcessMasterDO manufactureProcessMasterDO = manufactureProcessMasterDAO.getManufactureProcessMasterById(manufactureProcessSlave.getManufactureProcessMasterId());
                CustomerOrderDO customerOrderDO = customerOrderDAO.getCustomerOrderById(manufactureProcessMasterDO.getCustomerOrderId());

                ScheduleDO scheduleDO = new ScheduleDO();
                scheduleDO.setDeliveryDate(customerOrderDO.getDeliveryDate());
                scheduleDO.setMaterialExportId(materialExportDO.getId());
                scheduleDAO.insert(scheduleDO);
            }

            materialExportDAO.updateCheckerById(materialExportDO);
        });
    }

    @Override
    public ResultDO<Void> exportExcel(MaterialExportVO[] materialExportVO) {
        if (null == materialExportVO || 0 == materialExportVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialExport exportExcel", Arrays.toString(materialExportVO), () -> {
            List<MaterialExportModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (MaterialExportVO temp : materialExportVO) {
                MaterialExportModel item = new MaterialExportModel();
                item.setIndex(Integer.toString(i++));
                item.setJobTicketNumber(temp.getJobTicketNumber());
                item.setExportNumber(temp.getExportNumber());
                item.setOrderNumber(temp.getOrderNumber());
                item.setExportDate(format.format(temp.getExportDate()));
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setIssueNumber(temp.getIssueNumber());
                item.setName(temp.getName());
                item.setCheckFlag(temp.getCheckFlag());
                item.setRemark(temp.getRemark());
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(8);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(26.14*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(24.86*256));
            columnWidth.put(6,(int)(10.14*256));
            columnWidth.put(7,(int)(24.86*256));
            columnWidth.put(8,(int)(24.14*256));
            columnWidth.put(9,(int)(10.71*256));
            columnWidth.put(10,(int)(5.71*256));
            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_EXPORT_FILE_NAME, MaterialExportModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> exportDetailExcel(List<MaterialExportVO> materialExportVO) {
        if (null == materialExportVO || 0 == materialExportVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        List<MaterialExportDetailDO> materialExportDetailList = materialExportDetailDAO.getMaterialExportDetailByMaterialExportIds(materialExportVO.stream().map(MaterialExportVO::getId).collect(Collectors.toList()));

        List<MaterialExportDetailModel> data = new ArrayList<>();
        int k = 1;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 获得所有材料库存信息
        List<MaterialInventoryDO> materialInventoryList = materialInventoryDAO.getMaterialInventoryByIds(materialExportDetailList.stream().map(MaterialExportDetailDO::getMaterialInventoryId).collect(Collectors.toList()));
        Map<String, MaterialInventoryDO> materialInventoryListMap = materialInventoryList.stream().collect(Collectors.toMap(MaterialInventoryDO::getId, materialInventory -> materialInventory));

        // 获得所有材料库存信息
        List<MaterialExportDO> materialExportList = materialExportDAO.getMaterialExportByIds(materialExportDetailList.stream().map(MaterialExportDetailDO::getMaterialExportId).collect(Collectors.toList()));
        Map<String, MaterialExportDO> materialExportListMap = materialExportList.stream().collect(Collectors.toMap(MaterialExportDO::getId, materialExport -> materialExport));

        // 获得所有制造流程单副表信息
        List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(materialExportList.stream().map(MaterialExportDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
        Map<String, ManufactureProcessSlaveDO> manufactureProcessSlaveMap = manufactureProcessSlaveList.stream().collect(Collectors.toMap(ManufactureProcessSlaveDO::getId, manufactureProcessSlave -> manufactureProcessSlave));

        // 获得所有制造流程单主表信息
        List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
        Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap(ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

        // 获得所有客户订单信息
        List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
        Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

        // 获得所有材料入库信息
        List<MaterialStorageDO> materialStorageList = materialStorageDAO.getMaterialStorageByIds(materialInventoryList.stream().map(MaterialInventoryDO::getMaterialStorageId).collect(Collectors.toList()));
        Map<String, MaterialStorageDO> materialStorageListMap = materialStorageList.stream().collect(Collectors.toMap(MaterialStorageDO::getId, materialStorage -> materialStorage));

        // 获得所有原材料待入库信息
        List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageList = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageByIds(materialStorageList.stream().map(MaterialStorageDO::getMaterialPurchaseToBeStorageId).collect(Collectors.toList()));
        Map<String, MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageMap = materialPurchaseToBeStorageList.stream().collect(Collectors.toMap(MaterialPurchaseToBeStorageDO::getId, materialPurchaseToBeStorage -> materialPurchaseToBeStorage));

        // 获得所有原材料采购信息
        List<MaterialPurchaseDO> materialPurchaseList = materialPurchaseDAO.getMaterialPurchaseByIds(materialPurchaseToBeStorageList.stream().map(MaterialPurchaseToBeStorageDO::getMaterialPurchaseId).collect(Collectors.toList()));
        Map<String, MaterialPurchaseDO> materialPurchaseMap = materialPurchaseList.stream().collect(Collectors.toMap(MaterialPurchaseDO::getId, materialPurchase -> materialPurchase));

        // 获得所有材料原始信息
        List<MaterialInfoDO> materialInfoList = materialInfoDAO.getMaterialInfoByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getMaterialInfoId).collect(Collectors.toList()));
        Map<String, MaterialInfoDO> materialInfoMap = materialInfoList.stream().collect(Collectors.toMap(MaterialInfoDO::getId, materialInfo-> materialInfo));

        // 获得所有材料原始信息
        List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInfoList.stream().map(MaterialInfoDO::getMaterialInfoMasterId).collect(Collectors.toList()));
        Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

        // 获得所有供应商信息
        List<SupplierDO> supplierList = supplierDAO.getSupplierByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getSupplierId).collect(Collectors.toList()));
        Map<String, SupplierDO> supplierMap = supplierList.stream().collect(Collectors.toMap(SupplierDO::getId, supper-> supper));

        for (MaterialExportDetailDO temp : materialExportDetailList) {
            MaterialExportDetailModel item = new MaterialExportDetailModel();

            String manufactureProcessSlaveId = materialExportListMap.get(temp.getMaterialExportId()).getManufactureProcessSlaveId();
            String manufactureProcessMasterId = manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getManufactureProcessMasterId();
            String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
            String materialStorageId = materialInventoryListMap.get(temp.getMaterialInventoryId()).getMaterialStorageId();
            String materialPurchaseToBeStorageId = materialStorageListMap.get(materialStorageId).getMaterialPurchaseToBeStorageId();
            String materialPurchaseId = materialPurchaseToBeStorageMap.get(materialPurchaseToBeStorageId).getMaterialPurchaseId();
            String materialInfoId = materialPurchaseMap.get(materialPurchaseId).getMaterialInfoId();
            String materialInfoMasterId = materialInfoMap.get(materialInfoId).getMaterialInfoMasterId();
            String supplierId = materialPurchaseMap.get(materialPurchaseId).getSupplierId();

            item.setIndex(Integer.toString(k++));
            item.setExportNumber(materialExportListMap.get(temp.getMaterialExportId()).getNumber());
            item.setExportDate(format.format(materialExportListMap.get(temp.getMaterialExportId()).getDate()));
            item.setIssueNumber(temp.getAmount());
            item.setJobTicketNumber(manufactureProcessSlaveMap.get(manufactureProcessSlaveId).getJobTicketNumber());
            item.setOrderNumber(customerOrderMap.get(customerOrderId).getOrderNumber());
            item.setCode(supplierMap.get(supplierId).getCode());
            item.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
            item.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
            item.setUnit(materialInfoMasterMap.get(materialInfoMasterId).getUnit());
            item.setUnitPrice(materialInfoMap.get(materialInfoId).getUnitPrice());

            data.add(item);
        }

        // 设置列宽
        Map<Integer,Integer> columnWidth = new HashMap<>(8);
        columnWidth.put(0,(int)(5.57*256));
        columnWidth.put(1,(int)(26.14*256));
        columnWidth.put(2,(int)(26.14*256));
        columnWidth.put(3,(int)(23.43*256));
        columnWidth.put(4,(int)(24.86*256));
        columnWidth.put(5,(int)(24.86*256));
        columnWidth.put(6,(int)(10.14*256));
        columnWidth.put(7,(int)(24.86*256));
        columnWidth.put(8,(int)(10.14*256));
        columnWidth.put(9,(int)(10.71*256));
        columnWidth.put(10,(int)(10.71*256));

        return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_EXPORT_DETAIL_FILE_NAME, MaterialExportDetailModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
    }

}
