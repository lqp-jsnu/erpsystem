package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.service.MaterialExportDetailInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 材料出库明细逻辑接口实现
 *
 * @author wjd
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialExportDetailService")
public  class MaterialExportDetailServiceImpl implements MaterialExportDetailInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 审核标志 */
    private static final String CHECK_FLAG = "checkFlag";
    /** 流程单号 */
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";
    /** 客户订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 出库单号 */
    private static final String EXPORT_NUMBER = "exportNumber";

    @Resource
    private MaterialStorageDAO materialStorageDAO;

    @Resource
    private MaterialInfoDAO materialInfoDAO;

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
    private SupplierDAO supplierDAO;

    @Resource
    private WarehouseDAO warehouseDAO;

    @Resource
    private MaterialExportDetailDAO materialExportDetailDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Override
    public ResultDO<PageUtils<MaterialExportDetailVO>> getMaterialExportDetail(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialExportDetail", params.toString(), () -> {
            List<MaterialExportDetailVO> result = new ArrayList<>();

            // 得到所有材料出库明细信息
            List<MaterialExportDO> materialExportDetailList = materialExportDAO.getMaterialExportDetail(params);
            if (materialExportDetailList == null) {
                logger.error("getMaterialExportDetail exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == materialExportDetailList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有制造流程单副表信息
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(materialExportDetailList.stream().map(MaterialExportDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
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
            List<UserDO> enterList = userDAO.getUserByIds(materialExportDetailList.stream().map(MaterialExportDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 获得所有审核者信息
            List<UserDO> checkerList = userDAO.getUserByIds(materialExportDetailList.stream().map(MaterialExportDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            for (MaterialExportDO tempMaterialExportDetail : materialExportDetailList) {
                MaterialExportDetailVO temp = new MaterialExportDetailVO();

                String manufactureProcessMasterId = manufactureProcessSlaveMap.get(tempMaterialExportDetail.getManufactureProcessSlaveId()).getManufactureProcessMasterId();
                String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();
                String materialInfoMasterId = originalProductMap.get(originalProductId).getMaterialInfoMasterId();

                temp.setId(tempMaterialExportDetail.getId());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(tempMaterialExportDetail.getManufactureProcessSlaveId()).getJobTicketNumber());
                temp.setExportNumber(tempMaterialExportDetail.getNumber());
                temp.setExportDate(tempMaterialExportDetail.getDate());
                temp.setCreateTime(tempMaterialExportDetail.getCreateTime());
                temp.setOrderNumber(customerOrderMap.get(customerOrderId).getOrderNumber());
                temp.setName(customerMap.get(customerId).getName());
                temp.setUser(enterMap.get(tempMaterialExportDetail.getUser()).getName());
                temp.setChecker(checkerMap.get(tempMaterialExportDetail.getChecker()).getName());
                temp.setIssueNumber(tempMaterialExportDetail.getIssueNumber());
                temp.setCheckFlag(tempMaterialExportDetail.getCheckFlag());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialExportDAO.counts(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialExportDetailVO>> getMaterialExportDetailByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialExportDetailByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialExportDetailVO>> resultVO = getMaterialExportDetail(params);
            if (resultVO == null) {
                logger.error("getMaterialExportDetailByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }

            List<MaterialExportDetailVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CHECK_FLAG)) {
                result = result.stream().filter(item -> null != item.getCheckFlag() && item.getCheckFlag().equals(params.get(CHECK_FLAG).toString())).collect(Collectors.toList());
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
    public ResultDO<PageUtils<MaterialExportDetailVO>> getView(Map<String, Object> params) {
        return CallbackUtils.getCallback("getView", params.toString(), () -> {
            List<MaterialExportDetailVO> result = new ArrayList<>();

            // 得到所有材料出库明细信息
            List<MaterialExportDetailDO> viewList = materialExportDetailDAO.getMaterialExportDetail(params);
            if (viewList == null) {
                logger.error("getView exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == viewList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有材料库存信息
            List<MaterialInventoryDO> materialInventoryList = materialInventoryDAO.getMaterialInventoryByIds(viewList.stream().map(MaterialExportDetailDO::getMaterialInventoryId).collect(Collectors.toList()));
            Map<String, MaterialInventoryDO> materialInventoryListMap = materialInventoryList.stream().collect(Collectors.toMap(MaterialInventoryDO::getId, materialInventory -> materialInventory));

            // 获得所有材料库存信息
            List<MaterialExportDO> materialExportList = materialExportDAO.getMaterialExportByIds(viewList.stream().map(MaterialExportDetailDO::getMaterialExportId).collect(Collectors.toList()));
            Map<String, MaterialExportDO> materialExportListMap = materialExportList.stream().collect(Collectors.toMap(MaterialExportDO::getId, materialExport -> materialExport));

            // 获得所有材料入库信息
            List<MaterialStorageDO> materialStorageList = materialStorageDAO.getMaterialStorageByIds(materialInventoryList.stream().map(MaterialInventoryDO::getMaterialStorageId).collect(Collectors.toList()));
            Map<String, MaterialStorageDO> materialStorageListMap = materialStorageList.stream().collect(Collectors.toMap(MaterialStorageDO::getId, materialStorage -> materialStorage));

            // 得到所有仓库信息
            List<WarehouseDO> warehouseList = warehouseDAO.getWarehouseByIds(materialStorageList.stream().map(MaterialStorageDO::getWareHouseId).collect(Collectors.toList()));
            Map<String, WarehouseDO> warehouseMap = warehouseList.stream().collect(Collectors.toMap(WarehouseDO::getId, warehouse -> warehouse));

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

            for (MaterialExportDetailDO tempMaterialExportDetail : viewList) {
                MaterialExportDetailVO temp = new MaterialExportDetailVO();

                String materialStorageId = materialInventoryListMap.get(tempMaterialExportDetail.getMaterialInventoryId()).getMaterialStorageId();
                String warehouseId = materialStorageListMap.get(materialStorageId).getWareHouseId();
                String materialPurchaseToBeStorageId = materialStorageListMap.get(materialStorageId).getMaterialPurchaseToBeStorageId();
                String materialPurchaseId = materialPurchaseToBeStorageMap.get(materialPurchaseToBeStorageId).getMaterialPurchaseId();
                String materialInfoId = materialPurchaseMap.get(materialPurchaseId).getMaterialInfoId();
                String materialInfoMasterId = materialInfoMap.get(materialInfoId).getMaterialInfoMasterId();
                String supplierId = materialPurchaseMap.get(materialPurchaseId).getSupplierId();

                temp.setId(tempMaterialExportDetail.getId());
                temp.setCode(supplierMap.get(supplierId).getCode());
                temp.setHouseName(warehouseMap.get(warehouseId).getName());
                temp.setExportDate(materialExportListMap.get(tempMaterialExportDetail.getMaterialExportId()).getDate());
                temp.setIssueNumber(tempMaterialExportDetail.getAmount());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setUnit(materialInfoMasterMap.get(materialInfoMasterId).getUnit());
                temp.setUnitPrice(materialInfoMap.get(materialInfoId).getUnitPrice());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialExportDetailDAO.count(params), result));
        });
    }

}
