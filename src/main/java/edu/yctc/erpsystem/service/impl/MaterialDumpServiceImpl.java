package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialDumpModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialDumpInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialDumpVO;
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
 * 原材料报废逻辑接口实现
 *
 * @author lqp
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialDumpService")
public class MaterialDumpServiceImpl implements MaterialDumpInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";

    private static final String CHECK_FLAG = "已通过";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private MaterialDumpDAO materialDumpDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private MaterialStorageDAO materialStorageDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Override
    public ResultDO<PageUtils<MaterialDumpVO>> getMaterialDump(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialDump", params.toString(), () -> {
            List<MaterialDumpVO> result = new ArrayList<>();

            // 得到所有原材料报废信息
            List<MaterialDumpDO> materialDumpList = materialDumpDAO.getMaterialDump(params);
            if (materialDumpList == null) {
                logger.error("getMaterialDump exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == materialDumpList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            List<MaterialInventoryDO> materialInventoryList = materialInventoryDAO.getMaterialInventoryByIds( materialDumpList.stream().map( MaterialDumpDO::getMaterialInventoryId).collect(Collectors.toList()) );
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
            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(materialDumpList.stream().map(MaterialDumpDO::getEnter).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(materialDumpList.stream().map(MaterialDumpDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            // 得到库存数量
            List<MaterialInventoryDO> leftAmountList = materialInventoryDAO.getMaterialInventoryByIds(materialDumpList.stream().map(MaterialDumpDO::getMaterialInventoryId).collect(Collectors.toList()));
            Map<String, MaterialInventoryDO> leftAmountMap = leftAmountList.stream().collect(Collectors.toMap(MaterialInventoryDO::getId, leftAmount -> leftAmount));

            for (MaterialDumpDO tempMaterialDump : materialDumpList) {
                MaterialDumpVO temp = new MaterialDumpVO();

                String materialStorageId = materialInventoryMap.get(tempMaterialDump.getMaterialInventoryId()).getMaterialStorageId();
                String materialPurchaseToBeStorageId = materialStorageMap.get(materialStorageId).getMaterialPurchaseToBeStorageId();
                String materialPurchaseId = materialPurchaseToBeStorageMap.get(materialPurchaseToBeStorageId).getMaterialPurchaseId();
                String materialInfoId = materialPurchaseMap.get(materialPurchaseId).getMaterialInfoId();
                String materialInfoMasterId = materialInfoMap.get(materialInfoId).getMaterialInfoMasterId();
                String supplierId = materialInfoMap.get(materialInfoId).getSupplierId();

                temp.setId(tempMaterialDump.getId());
                temp.setCode(supplierMap.get(supplierId).getCode());
                temp.setName(supplierMap.get(supplierId).getName());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setDate(tempMaterialDump.getDate());
                temp.setAmount(tempMaterialDump.getAmount());
                temp.setReason(tempMaterialDump.getReason());
                temp.setEnter(enterMap.get(tempMaterialDump.getEnter()).getName());
                if (null != tempMaterialDump.getChecker()) {
                    temp.setChecker(checkerMap.get(tempMaterialDump.getChecker()).getName());
                }
                temp.setCreateTime(tempMaterialDump.getCreateTime());
                temp.setCheckFlag(tempMaterialDump.getCheckFlag());
                temp.setMaterialInventoryId(tempMaterialDump.getMaterialInventoryId());
                temp.setLeftAmount(leftAmountMap.get(tempMaterialDump.getMaterialInventoryId()).getLeftAmount());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialDumpDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialDumpVO>> getMaterialDumpByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialDumpByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialDumpVO>> resultVO = getMaterialDump(params);
            if (resultVO == null) {
                logger.error("getMaterialDumpByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialDumpVO> result = resultVO.getModule().getRows();

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

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialDumpDO materialDumpDO) {
        if (StringUtils.isBlank(materialDumpDO.getMaterialInventoryId()) || StringUtils.isBlank(materialDumpDO.getAmount())
                || StringUtils.isBlank(materialDumpDO.getDate().toString()) || StringUtils.isBlank(materialDumpDO.getEnter())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialDump", materialDumpDO.toString(), () -> materialDumpDAO.insert(materialDumpDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialDump", id, () -> materialDumpDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateMaterialDump(MaterialDumpDO materialDumpDO) {
        if (StringUtils.isBlank(materialDumpDO.getId()) || StringUtils.isBlank(materialDumpDO.getAmount()) || StringUtils.isBlank(materialDumpDO.getDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateMaterialDump", materialDumpDO.toString(), () -> materialDumpDAO.updateMaterialDump(materialDumpDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(MaterialDumpDO materialDumpDO) {
        if (StringUtils.isBlank(materialDumpDO.getId()) || StringUtils.isBlank(materialDumpDO.getChecker())
                || StringUtils.isBlank(materialDumpDO.getCheckFlag())  || StringUtils.isBlank(materialDumpDO.getMaterialInventoryId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        if (CHECK_FLAG.equals(materialDumpDO.getCheckFlag())) {
            MaterialDumpDO temp = materialDumpDAO.getMaterialDumpById(materialDumpDO.getId());
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

        return CallbackUtils.updateCallback("MaterialDump updateCheckerById", materialDumpDO.toString(), () -> materialDumpDAO.updateCheckerById(materialDumpDO));
    }

    @Override
    public ResultDO<Void> exportExcel(MaterialDumpVO[] materialDumps) {
        if (null == materialDumps || 0 == materialDumps.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialDump exportExcel", Arrays.toString(materialDumps), () -> {
            List<MaterialDumpModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (MaterialDumpVO temp : materialDumps) {
                MaterialDumpModel item = new MaterialDumpModel();
                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setName(temp.getName());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setAmount(temp.getAmount());
                item.setDate(format.format(temp.getDate()));
                item.setReason(temp.getReason());
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(8);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(9.0*256));
            columnWidth.put(6,(int)(10.14*256));
            columnWidth.put(7,(int)(8.71*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_DUMP_FILE_NAME, MaterialDumpModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
