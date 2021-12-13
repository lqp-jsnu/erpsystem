package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.ManualExportModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.ManualExportInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManualExportVO;
import org.apache.commons.lang3.StringUtils;
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
 * 手动出库表
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("manualExportService")
public class ManualExportServiceImpl implements ManualExportInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 出库仓库*/
    private static final String HOUSE_NAME = "houseName";

    private static final String CHECK_FLAG = "已通过";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ManualExportDAO manualExportDAO;

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private WarehouseDAO warehouseDAO;

    @Resource
    private MaterialStorageDAO materialStorageDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Override
    public ResultDO<PageUtils<ManualExportVO>> getManualExport(Map<String, Object> params) {
        return CallbackUtils.getCallback("getManualExport", params.toString(), () -> {
            List<ManualExportVO> result = new ArrayList<>();

            // 得到所有手动出库信息
            List<ManualExportDO> manualExportList = manualExportDAO.getManualExport(params);
            if (manualExportList == null) {
                logger.error("getManualExport exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == manualExportList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, null));
            }

            List<MaterialInventoryDO> materialInventoryList = materialInventoryDAO.getMaterialInventoryByIds( manualExportList.stream().map( ManualExportDO::getMaterialInventoryId).collect(Collectors.toList()) );
            Map<String, MaterialInventoryDO> materialInventoryMap = materialInventoryList.stream().collect(Collectors.toMap( MaterialInventoryDO::getId, materialInventory -> materialInventory));

            List<MaterialStorageDO> materialStorageList = materialStorageDAO.getMaterialStorageByIds(materialInventoryList.stream().map( MaterialInventoryDO::getMaterialStorageId ).collect(Collectors.toList()));
            Map<String, MaterialStorageDO> materialStorageMap = materialStorageList.stream().collect(Collectors.toMap(MaterialStorageDO::getId, materialStorage -> materialStorage));

            List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageList = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageByIds(materialStorageList.stream().map(MaterialStorageDO::getMaterialPurchaseToBeStorageId).collect(Collectors.toList()));
            Map<String, MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageMap = materialPurchaseToBeStorageList.stream().collect(Collectors.toMap(MaterialPurchaseToBeStorageDO::getId, materialPurchaseToBeStorage -> materialPurchaseToBeStorage));

            List<MaterialPurchaseDO> materialPurchaseList = materialPurchaseDAO.getMaterialPurchaseByIds(materialPurchaseToBeStorageList.stream().map(MaterialPurchaseToBeStorageDO::getMaterialPurchaseId).collect(Collectors.toList()));
            Map<String, MaterialPurchaseDO> materialPurchaseMap = materialPurchaseList.stream().collect(Collectors.toMap(MaterialPurchaseDO::getId, materialPurchase -> materialPurchase));

            List<MaterialInfoDO> materialInfoList = materialInfoDAO.getMaterialInfoByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getMaterialInfoId).collect(Collectors.toList()));
            Map<String, MaterialInfoDO> materialInfoMap = materialInfoList.stream().collect(Collectors.toMap(MaterialInfoDO::getId, materialInfo-> materialInfo));

            // 获得所有材料原始信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInfoList.stream().map(MaterialInfoDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            // 获得所有供应商信息
            List<SupplierDO> supplierList = supplierDAO.getSupplierByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getSupplierId).collect(Collectors.toList()));
            Map<String, SupplierDO> supplierMap = supplierList.stream().collect(Collectors.toMap(SupplierDO::getId, supper-> supper));

            // 得到所有仓库信息
            List<WarehouseDO> warehouseList = warehouseDAO.getWarehouseByIds(materialStorageList.stream().map(MaterialStorageDO::getWareHouseId).collect(Collectors.toList()));
            Map<String, WarehouseDO> warehouseMap = warehouseList.stream().collect(Collectors.toMap(WarehouseDO::getId, warehouse -> warehouse));

            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(manualExportList.stream().map( ManualExportDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap( UserDO::getId, enter -> enter));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(manualExportList.stream().map( ManualExportDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap( UserDO::getId, checker -> checker));

            for (ManualExportDO tempManualExport : manualExportList) {
                ManualExportVO temp = new ManualExportVO();

                String materialInventoryId = tempManualExport.getMaterialInventoryId();
                String materialStorageId = materialInventoryMap.get(materialInventoryId).getMaterialStorageId();
                String materialPurchaseToBeStorageId = materialStorageMap.get(materialStorageId).getMaterialPurchaseToBeStorageId();
                String warehouseId = materialStorageMap.get(materialStorageId).getWareHouseId();
                String materialPurchaseId = materialPurchaseToBeStorageMap.get(materialPurchaseToBeStorageId).getMaterialPurchaseId();
                String materialInfoId = materialPurchaseMap.get(materialPurchaseId).getMaterialInfoId();
                String materialInfoMasterId = materialInfoMap.get(materialInfoId).getMaterialInfoMasterId();
                String supplierId = materialInfoMap.get(materialInfoId).getSupplierId();

                temp.setId(tempManualExport.getId());
                if (null != supplierId) {
                    temp.setCode(supplierMap.get(supplierId).getCode());
                }
                temp.setMaterialInventoryId(materialInventoryId);
                temp.setExportNumber(tempManualExport.getNumber());
                temp.setLeftAmount(materialInventoryMap.get(materialInventoryId).getLeftAmount());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setAmount(tempManualExport.getAmount());
                temp.setExportDate(tempManualExport.getDate());
                temp.setCheckFlag(tempManualExport.getCheckFlag());
                temp.setCreateTime(tempManualExport.getCreateTime());
                temp.setRemark(tempManualExport.getRemark());
                temp.setHouseName(warehouseMap.get(warehouseId).getName());
                temp.setUser(enterMap.get(tempManualExport.getUser()).getName());
                if (null != tempManualExport.getChecker()){
                    temp.setChecker(checkerMap.get(tempManualExport.getChecker()).getName());
                }

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(manualExportDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<ManualExportVO>> getManualExportByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getManualExportByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ManualExportVO>> resultVO = getManualExport(params);
            if (resultVO == null) {
                logger.error("getManualExportByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ManualExportVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(HOUSE_NAME)) {
                result = result.stream().filter(item -> null != item.getHouseName() && item.getHouseName().toLowerCase().contains(params.get(HOUSE_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(ManualExportDO manualExportDO) {
        if (StringUtils.isBlank(manualExportDO.getUser()) || StringUtils.isBlank(manualExportDO.getAmount())
                || StringUtils.isBlank(manualExportDO.getDate().toString()) || StringUtils.isBlank(manualExportDO.getMaterialInventoryId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("ManualExport", manualExportDO.toString(), () -> {
            // 获取订单号：时间
            Date dateTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String serialnumberString = sdf.format(dateTime);

            manualExportDO.setNumber(serialnumberString);

            manualExportDAO.insert(manualExportDO);
        });
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("ManualExport", id, () -> manualExportDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateManualExport(ManualExportDO manualExportDO) {
        if (StringUtils.isBlank(manualExportDO.getId()) || StringUtils.isBlank(manualExportDO.getAmount()) || StringUtils.isBlank(manualExportDO.getDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateManualExport", manualExportDO.toString(), () -> manualExportDAO.updateManualExport(manualExportDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(ManualExportDO manualExportDO) {
        if (StringUtils.isBlank(manualExportDO.getId()) || StringUtils.isBlank(manualExportDO.getChecker())
                || StringUtils.isBlank(manualExportDO.getCheckFlag()) || StringUtils.isBlank(manualExportDO.getMaterialInventoryId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        if (CHECK_FLAG.equals(manualExportDO.getCheckFlag())) {
            ManualExportDO temp = manualExportDAO.getManualExportById(manualExportDO.getId());
            MaterialInventoryDO result = materialInventoryDAO.getMaterialInventoryById(temp.getMaterialInventoryId());

            if (Double.parseDouble(result.getLeftAmount()) - Double.parseDouble(temp.getAmount()) < 0) {
                return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
            }

            // 减少库存数量
            MaterialInventoryDO materialInventoryDO = new MaterialInventoryDO();
            materialInventoryDO.setId(result.getId());
            materialInventoryDO.setLeftAmount("" + (Double.parseDouble(result.getLeftAmount()) - Double.parseDouble(temp.getAmount())));
            materialInventoryDAO.updateLeftAmountById(materialInventoryDO);
        }

        return CallbackUtils.updateCallback("ManualExport updateCheckerById", manualExportDO.toString(), () -> manualExportDAO.updateCheckerById(manualExportDO));
    }

    @Override
    public ResultDO<Void> exportExcel(ManualExportVO[] manualExports) {
        if (null == manualExports || 0 == manualExports.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("ManualExport exportExcel", Arrays.toString(manualExports), () -> {
            List<ManualExportModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (ManualExportVO temp : manualExports) {
                ManualExportModel item = new ManualExportModel();
                item.setIndex(Integer.toString(i++));
                item.setExportNumber(temp.getExportNumber());
                item.setCode(temp.getCode());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setAmount(temp.getAmount());
                item.setHouseName(temp.getHouseName());
                if (temp.getExportDate() == null){//判断时间是否为空
                    item.setExportDate(" ");
                } else {
                    item.setExportDate(format.format(temp.getExportDate()));
                }
                item.setUser(temp.getUser());
                item.setChecker(temp.getChecker());
                if (temp.getCreateTime() == null) {//判断时间是否为空
                    item.setCreateTime(" ");
                } else {
                    item.setCreateTime(format.format(temp.getCreateTime()));
                }
                item.setCheckFlag(temp.getCheckFlag());
                item.setRemark(temp.getRemark());
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(12);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(24.86*256));
            columnWidth.put(2,(int)(23.43*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(9.0*256));
            columnWidth.put(6,(int)(23.43*256));
            columnWidth.put(7,(int)(24.86*256));
            columnWidth.put(8,(int)(9.0*256));
            columnWidth.put(9,(int)(9.0*256));
            columnWidth.put(10,(int)(24.86*256));
            columnWidth.put(11,(int)(9.0*256));
            columnWidth.put(12,(int)(9.0*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MANUAL_EXPORT_FILE_NAME, ManualExportModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
