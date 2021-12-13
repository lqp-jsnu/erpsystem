package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialStorageModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialStorageInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialStorageVO;
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
 * 原材料入库逻辑接口实现
 *
 * @author wjd
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialStorageService")
public  class MaterialStorageServiceImpl implements MaterialStorageInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 采购单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 入库类型 */
    private static final String DICT_VALUE = "dictValue";
    /** 仓库名称 */
    private static final String HOUSE_NAME = "houseName";

    private static final String CHECK_FLAG = "已通过";

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private WarehouseDAO warehouseDAO;

    @Resource
    private MaterialStorageDAO materialStorageDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private DictDAO dictDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private MaterialInventoryMasterDAO materialInventoryMasterDAO;

    @Override
    public ResultDO<PageUtils<MaterialStorageVO>> getMaterialStorage(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialStorage", params.toString(), () -> {
            List<MaterialStorageVO> result = new ArrayList<>();

            // 得到所有原材料入库信息
            List<MaterialStorageDO> materialStorageList = materialStorageDAO.getMaterialStorage(params);
            if (materialStorageList == null) {
                logger.error("getMaterialStorage exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == materialStorageList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

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

            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(materialStorageList.stream().map(MaterialStorageDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(materialStorageList.stream().map(MaterialStorageDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            // 得到所有入库类型
            List<DictDO> dictList = dictDAO.getDictByIds(materialStorageList.stream().map(MaterialStorageDO::getDictId).collect(Collectors.toList()));
            Map<String, DictDO> dictMap = dictList.stream().collect(Collectors.toMap(DictDO::getId, dict -> dict));

            // 得到所有仓库信息
            List<WarehouseDO> warehouseList = warehouseDAO.getWarehouseByIds(materialStorageList.stream().map(MaterialStorageDO::getWareHouseId).collect(Collectors.toList()));
            Map<String, WarehouseDO> warehouseMap = warehouseList.stream().collect(Collectors.toMap(WarehouseDO::getId, warehouse -> warehouse));

            for (MaterialStorageDO tempMaterialStorage : materialStorageList) {
                MaterialStorageVO temp = new MaterialStorageVO();

                String materialPurchaseToBeStorageId = tempMaterialStorage.getMaterialPurchaseToBeStorageId();
                String materialPurchaseId = materialPurchaseToBeStorageMap.get(materialPurchaseToBeStorageId).getMaterialPurchaseId();
                String materialInfoId = materialPurchaseMap.get(materialPurchaseId).getMaterialInfoId();
                String materialInfoMasterId = materialInfoMap.get(materialInfoId).getMaterialInfoMasterId();
                String supplierId = materialPurchaseMap.get(materialPurchaseId).getSupplierId();

                temp.setId(tempMaterialStorage.getId());
                temp.setCreateTime(tempMaterialStorage.getCreateTime());
                if (null != tempMaterialStorage.getChecker()) {
                    temp.setChecker(checkerMap.get(tempMaterialStorage.getChecker()).getName());
                }
                temp.setUser(enterMap.get(tempMaterialStorage.getUser()).getName());
                temp.setCheckFlag(tempMaterialStorage.getCheckFlag());
                temp.setDictValue(dictMap.get(tempMaterialStorage.getDictId()).getValue());
                temp.setHouseName(warehouseMap.get(tempMaterialStorage.getWareHouseId()).getName());
                temp.setInAmount(tempMaterialStorage.getInAmount());
                temp.setInDate(tempMaterialStorage.getInDate());
                temp.setAmount(materialPurchaseMap.get(materialPurchaseId).getAmount());
                temp.setInStorageDate(tempMaterialStorage.getInStorageDate());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setOrderNumber(materialPurchaseToBeStorageMap.get(materialPurchaseToBeStorageId).getOrderNumber());
                temp.setRemark(tempMaterialStorage.getRemark());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setCode(supplierMap.get(supplierId).getCode());
                temp.setName(supplierMap.get(supplierId).getName());
                temp.setUnit(materialInfoMasterMap.get(materialInfoMasterId).getUnit());
                temp.setUnitPrice(materialInfoMap.get(materialInfoId).getUnitPrice());
                temp.setTotalMoney("" + Double.parseDouble(temp.getUnitPrice())* Double.parseDouble(tempMaterialStorage.getInAmount()));
                temp.setMaterialInfoId(materialInfoId);
                temp.setMaterialPurchaseToBeStorageId(tempMaterialStorage.getMaterialPurchaseToBeStorageId());
                temp.setWareHouseId(tempMaterialStorage.getWareHouseId());
                temp.setDictId(tempMaterialStorage.getDictId());
                temp.setSupplierId(supplierId);
                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialStorageDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialStorageVO>> getMaterialStorageByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialStorageByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialStorageVO>> resultVO = getMaterialStorage(params);
            if (resultVO == null) {
                logger.error("getMaterialStorageByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialStorageVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(DICT_VALUE)) {
                result = result.stream().filter(item -> null != item.getDictValue() && item.getDictValue().toLowerCase().contains(params.get(DICT_VALUE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(HOUSE_NAME)) {
                result = result.stream().filter(item -> null != item.getHouseName() && item.getHouseName().toLowerCase().contains(params.get(HOUSE_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialStorageDO materialStorageDO) {
        if (StringUtils.isBlank(materialStorageDO.getMaterialPurchaseToBeStorageId()) || StringUtils.isBlank(materialStorageDO.getInAmount())
                || StringUtils.isBlank(materialStorageDO.getInDate().toString()) || StringUtils.isBlank(materialStorageDO.getUser())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return CallbackUtils.insertCallback("MaterialStorage insert", materialStorageDO.toString(), () -> materialStorageDAO.insert(materialStorageDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialStorage delete", id, () -> materialStorageDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateCheckerById(MaterialStorageDO materialStorageDO) {
        if (StringUtils.isBlank(materialStorageDO.getId()) || StringUtils.isBlank(materialStorageDO.getChecker()) || StringUtils.isBlank(materialStorageDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        DecimalFormat df = new DecimalFormat("0.00");
        MaterialStorageDO materialStorage = materialStorageDAO.getMaterialStorageById(materialStorageDO.getId());
        MaterialPurchaseToBeStorageDO materialPurchaseToBeStorageDO = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageById(materialStorage.getMaterialPurchaseToBeStorageId());
        MaterialPurchaseDO materialPurchaseDO = materialPurchaseDAO.getMaterialPurchaseById(materialPurchaseToBeStorageDO.getMaterialPurchaseId());
        MaterialInfoDO materialInfoDO = materialInfoDAO.getMaterialInfoById(materialPurchaseDO.getMaterialInfoId());

        // 获取t_materialInventoryMaster中信息
        MaterialInventoryMasterDO materialInventoryMasterDO = new MaterialInventoryMasterDO();
        materialInventoryMasterDO.setMaterialInfoMasterId(materialInfoDO.getMaterialInfoMasterId());
        MaterialInventoryMasterDO result = materialInventoryMasterDAO.getMaterialInventoryMasterByMaterialInfoMasterId(materialInfoDO.getMaterialInfoMasterId());

        if (CHECK_FLAG.equals(materialStorageDO.getCheckFlag())) {
            // 判断t_materialInventoryMaster中是否存在相同masterInfoMasterId数据，没有则插入
            if (result == null) {
                materialInventoryMasterDO.setMaterialInfoMasterId(materialInfoDO.getMaterialInfoMasterId());
                materialInventoryMasterDAO.insert(materialInventoryMasterDO);
            }

            // 获取插入材料库存中的信息
            MaterialInventoryDO materialInventoryDO = new MaterialInventoryDO();

            materialInventoryDO.setMaterialInventoryMasterId(materialInventoryMasterDAO.getMaterialInventoryMasterByMaterialInfoMasterId(materialInfoDO.getMaterialInfoMasterId()).getId());
            materialInventoryDO.setMaterialStorageId(materialStorage.getId());
            materialInventoryDO.setInDate(materialStorage.getInDate());
            materialInventoryDO.setInStorageDate(materialStorage.getInStorageDate());
            materialInventoryDO.setLeftAmount(materialStorage.getInAmount());
            materialInventoryDO.setDictId(materialStorage.getDictId());

            // 插入至材料库存
            materialInventoryDAO.insert(materialInventoryDO);

            // 获取改变待入库中数据的值（原材料入库审核通过后，改变待入库中的值）
            MaterialPurchaseToBeStorageDO tempMaterialPurchaseToBeStorage = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageById(materialStorage.getMaterialPurchaseToBeStorageId());
            tempMaterialPurchaseToBeStorage.setInAmount(df.format( Float.parseFloat(materialStorage.getInAmount()) + Float.parseFloat(materialStorage.getInAmount())));
            materialPurchaseToBeStorageDAO.updateInAmountByMaterialPurchaseToBeStorageId(tempMaterialPurchaseToBeStorage);
        }

        // 判断待入库中入库数量和采购数量是否一致，若一致allIn置为1
        MaterialPurchaseToBeStorageDO results = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageById(materialStorage.getMaterialPurchaseToBeStorageId());
        if (Float.parseFloat(materialPurchaseDO.getAmount()) == Float.parseFloat(results.getInAmount())) {
            materialPurchaseToBeStorageDAO.updateAllInByMaterialPurchaseToBeStorageId(results);
        }
        return CallbackUtils.updateCallback("MaterialStorage updateCheckerById", materialStorageDO.toString(), () -> materialStorageDAO.updateCheckerById(materialStorageDO));
    }

    @Override
    public ResultDO<Void> exportExcel(MaterialStorageVO[] materialStorageVO) {
        if (null == materialStorageVO || 0 == materialStorageVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return CallbackUtils.exportCallback("MaterialStorage exportExcel", Arrays.toString(materialStorageVO), () -> {
            List<MaterialStorageModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (MaterialStorageVO temp : materialStorageVO) {
                MaterialStorageModel item = new MaterialStorageModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setCode(temp.getCode());
                item.setName(temp.getName());
                item.setInDate(format.format(temp.getInDate()));
                item.setInStorageDate(format.format(temp.getInStorageDate()));
                item.setAmount(temp.getAmount());
                item.setInAmount(temp.getInAmount());
                item.setUnit(temp.getUnit());
                item.setUnitPrice(temp.getUnitPrice());
                item.setHouseName(temp.getHouseName());
                item.setCheckFlag(temp.getCheckFlag());
                item.setRemark(temp.getRemark());
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(8);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(25.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(25.0*256));
            columnWidth.put(6,(int)(25.14*256));
            columnWidth.put(7,(int)(25.71*256));
            columnWidth.put(8,(int)(26.14*256));
            columnWidth.put(9,(int)(23.43*256));
            columnWidth.put(10,(int)(24.86*256));
            columnWidth.put(11,(int)(20.0*256));
            columnWidth.put(12,(int)(20.14*256));
            columnWidth.put(13,(int)(10.71*256));
            columnWidth.put(14,(int)(10.71*256));
            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_STORED_FILE_NAME, MaterialStorageModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
