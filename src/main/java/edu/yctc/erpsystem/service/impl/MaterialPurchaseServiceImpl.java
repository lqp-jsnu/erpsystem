package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialPurchaseImportModel;
import edu.yctc.erpsystem.excel.MaterialPurchaseModel;
import edu.yctc.erpsystem.service.MaterialPurchaseInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInfoVO;
import edu.yctc.erpsystem.vo.MaterialPurchaseVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 原材料采购逻辑接口实现
 *
 * @author wjd
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialPurchaseService")
public  class MaterialPurchaseServiceImpl implements MaterialPurchaseInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";

    /** excel文件名 */
    private static final String EXCEL_FILE_NAME = "purchase.xlsx";

    private static final String EXCEL_INDEX = "序号";

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private InvoiceTitleDAO invoiceTitleDAO;

    @Override
    public ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchase(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialPurchase", params.toString(), () -> {
            List<MaterialPurchaseVO> result = new ArrayList<>();

            // 得到所有原材料采购信息
            List<MaterialPurchaseDO> materialPurchaseList = materialPurchaseDAO.getMaterialPurchase(params);
            if (materialPurchaseList == null) {
                logger.error("getMaterialPurchase exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == materialPurchaseList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有材料原始信息
            List<MaterialInfoDO> materialInfoList = materialInfoDAO.getMaterialInfoByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getMaterialInfoId).collect(Collectors.toList()));
            Map<String, MaterialInfoDO> materialInfoMap = materialInfoList.stream().collect(Collectors.toMap(MaterialInfoDO::getId, materialInfo -> materialInfo));

            // 获得所有材料原始信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInfoList.stream().map(MaterialInfoDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            // 获得所有供应商信息
            List<SupplierDO> supplierList = supplierDAO.getSupplierByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getSupplierId).collect(Collectors.toList()));
            Map<String, SupplierDO> supplierMap = supplierList.stream().collect(Collectors.toMap(SupplierDO::getId, supplier -> supplier));

            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            for (MaterialPurchaseDO tempMaterialPurchase : materialPurchaseList) {
                MaterialPurchaseVO temp = new MaterialPurchaseVO();

                temp.setId(tempMaterialPurchase.getId());
                temp.setCode(supplierMap.get(tempMaterialPurchase.getSupplierId()).getCode());
                temp.setSupplierId(tempMaterialPurchase.getSupplierId());

                String tempMaterialInfoMasterId = materialInfoMap.get(tempMaterialPurchase.getMaterialInfoId()).getMaterialInfoMasterId();

                temp.setItemName(materialInfoMasterMap.get(tempMaterialInfoMasterId).getItemName());
                temp.setSpec(materialInfoMasterMap.get(tempMaterialInfoMasterId).getSpec());
                temp.setAmount(tempMaterialPurchase.getAmount());
                temp.setUnit(materialInfoMasterMap.get(tempMaterialInfoMasterId).getUnit());
                temp.setUnitPrice(materialInfoMap.get(tempMaterialPurchase.getMaterialInfoId()).getUnitPrice());
                temp.setCreateTime(tempMaterialPurchase.getCreateTime());
                temp.setRemark(tempMaterialPurchase.getRemark());
                temp.setTotalMoney(""+Double.parseDouble(materialInfoMap.get(tempMaterialPurchase.getMaterialInfoId()).getUnitPrice())*Double.parseDouble(tempMaterialPurchase.getAmount()));
                temp.setEnter(enterMap.get(tempMaterialPurchase.getUser()).getName());
                if (null != tempMaterialPurchase.getChecker()) {
                    temp.setChecker(checkerMap.get(tempMaterialPurchase.getChecker()).getName());
                }
                temp.setHopeDeliveryDate(tempMaterialPurchase.getHopeDeliveryDate());
                temp.setCheckFlag(tempMaterialPurchase.getCheckFlag());
                temp.setIsExport(tempMaterialPurchase.getIsExport());

                result.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialPurchaseDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchaseByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialPurchaseByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialPurchaseVO>> resultVO = getMaterialPurchase(params);
            if (resultVO == null) {
                logger.error("getMaterialPurchaseByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }

            List<MaterialPurchaseVO> result = resultVO.getModule().getRows();
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

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialPurchaseDO materialPurchaseDO) {
        if (StringUtils.isBlank(materialPurchaseDO.getMaterialInfoId()) || StringUtils.isBlank(materialPurchaseDO.getSupplierId())
                || StringUtils.isBlank(materialPurchaseDO.getAmount()) || StringUtils.isBlank(materialPurchaseDO.getHopeDeliveryDate().toString())
                || StringUtils.isBlank(materialPurchaseDO.getUser())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialPurchase", materialPurchaseDO.toString(), () -> materialPurchaseDAO.insert(materialPurchaseDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialPurchase", id, () -> materialPurchaseDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateCheckerById(MaterialPurchaseDO materialPurchaseDO) {
        if (StringUtils.isBlank(materialPurchaseDO.getId()) || StringUtils.isBlank(materialPurchaseDO.getChecker()) || StringUtils.isBlank(materialPurchaseDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("MaterialPurchase updateCheckerById", materialPurchaseDO.toString(), () -> materialPurchaseDAO.updateCheckerById(materialPurchaseDO));
    }

    @Override
    public ResultDO<Void> updateMaterialPurchase(MaterialPurchaseDO materialPurchaseDO) {
        if (StringUtils.isBlank(materialPurchaseDO.getId()) || StringUtils.isBlank(materialPurchaseDO.getAmount()) || StringUtils.isBlank(materialPurchaseDO.getHopeDeliveryDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateMaterialPurchase", materialPurchaseDO.toString(), () -> materialPurchaseDAO.updateMaterialPurchase(materialPurchaseDO));
    }

    @Override
    public ResultDO<Void> insertToStored(List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageDO) {
        for (MaterialPurchaseToBeStorageDO item: materialPurchaseToBeStorageDO) {
            if (StringUtils.isBlank(item.getMaterialPurchaseId()) || StringUtils.isBlank(item.getInvoiceTitleId()) || StringUtils.isBlank(item.getOrderNumber())) {
                return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
            }
        }

        return CallbackUtils.updateCallback("insertToStored", materialPurchaseToBeStorageDO.toString(), () -> {
            List<MaterialPurchaseDO> materialPurchaseList = materialPurchaseDAO.getMaterialPurchaseByIds(materialPurchaseToBeStorageDO.stream().map(MaterialPurchaseToBeStorageDO::getMaterialPurchaseId).collect(Collectors.toList()));
            Map<String, MaterialPurchaseDO> materialPurchaseMap = materialPurchaseList.stream().collect(Collectors.toMap(MaterialPurchaseDO::getId, materialPurchase-> materialPurchase));

            List<MaterialInfoDO> materialInfoList = materialInfoDAO.getMaterialInfoByIds(materialPurchaseList.stream().map(MaterialPurchaseDO::getMaterialInfoId).collect(Collectors.toList()));
            Map<String, MaterialInfoDO> materialInfoMap = materialInfoList.stream().collect(Collectors.toMap(MaterialInfoDO::getId, materialInfo-> materialInfo));

            // 获得所有材料原始信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInfoList.stream().map(MaterialInfoDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            InvoiceTitleDO invoiceTitleDO = invoiceTitleDAO.getInvoiceTitleById(materialPurchaseToBeStorageDO.get(0).getInvoiceTitleId());

            SupplierDO supplierDO = supplierDAO.getSupplierById(materialPurchaseList.get(0).getSupplierId());

            for (MaterialPurchaseToBeStorageDO item: materialPurchaseToBeStorageDO) {
                MaterialPurchaseDO materialPurchaseDO = materialPurchaseMap.get(item.getMaterialPurchaseId());

                item.setCheckFlag(materialPurchaseDO.getCheckFlag());
                item.setHopeDeliveryDate(materialPurchaseDO.getHopeDeliveryDate());
                item.setRemark(materialPurchaseDO.getRemark());
            }

            materialPurchaseToBeStorageDAO.insertAll(materialPurchaseToBeStorageDO);
            materialPurchaseDAO.updateIsExportByIds(materialPurchaseToBeStorageDO.stream().map(MaterialPurchaseToBeStorageDO::getMaterialPurchaseId).collect(Collectors.toList()));

            // 导出excel
            Map<String, Object> map = new HashMap<>(9);

            map.put("titleName", invoiceTitleDO.getName());
            map.put("orderNumber", materialPurchaseToBeStorageDO.get(0).getOrderNumber());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            map.put("orderDate", simpleDateFormat.format(new Date()));

            map.put("code", supplierDO.getCode());
            map.put("name", supplierDO.getName());
            map.put("contact", supplierDO.getContact());
            map.put("fixedTelephone", supplierDO.getFixedTelephone());
            map.put("fax", supplierDO.getFax());

            List<MaterialPurchaseModel> materialPurchaseModelList = new ArrayList<>();

            for (int i = 0; i < materialPurchaseToBeStorageDO.size(); ++i) {
                MaterialPurchaseModel materialPurchaseModel = new MaterialPurchaseModel();
                MaterialPurchaseDO materialPurchaseDO = materialPurchaseMap.get(materialPurchaseToBeStorageDO.get(i).getMaterialPurchaseId());
                MaterialInfoDO materialInfoDO = materialInfoMap.get(materialPurchaseDO.getMaterialInfoId());

                materialPurchaseModel.setIndex("" + i);
                materialPurchaseModel.setItemName(materialInfoMasterMap.get(materialInfoDO.getMaterialInfoMasterId()).getItemName());
                materialPurchaseModel.setSpec(materialInfoMasterMap.get(materialInfoDO.getMaterialInfoMasterId()).getSpec());
                materialPurchaseModel.setAmount(materialPurchaseDO.getAmount());
                materialPurchaseModel.setUnit(materialInfoMasterMap.get(materialInfoDO.getMaterialInfoMasterId()).getUnit());
                materialPurchaseModel.setUnitPrice(materialInfoDO.getUnitPrice());
                materialPurchaseModel.setTotalPrice("" + Double.parseDouble(materialPurchaseDO.getAmount()) * Double.parseDouble(materialInfoDO.getUnitPrice()));
                materialPurchaseModel.setHopeDeliveryDate(simpleDateFormat.format(materialPurchaseDO.getHopeDeliveryDate()));
                materialPurchaseModel.setRemark(materialPurchaseDO.getRemark());

                materialPurchaseModelList.add(materialPurchaseModel);
            }

            map.put("materialPurchase", materialPurchaseModelList);

            MyExcel.writeTemplate(ConstantHolder.MATERIAL_PURCHASE_FILE_NAME, "原材料订购单（发，亿，永）.xlsx", map);
        });
    }

    @Override
    public ResultDO<Void> importExcel(String user) {
        try {
            ResultDO<List<Object>> resultDO = MyExcel.read(ConstantHolder.FILE_SAVE_PATH + EXCEL_FILE_NAME, MaterialPurchaseImportModel.class);
            if (resultDO.isSuccess()) {
                Date date = new Date();

                List<MaterialPurchaseImportModel> materialPurchaseImportModelList = resultDO.getModule().stream().map(item -> (MaterialPurchaseImportModel)(item)).collect(Collectors.toList());
                materialPurchaseImportModelList = materialPurchaseImportModelList.stream().filter(item -> null != item.getIndex() && !EXCEL_INDEX.equals(item.getIndex())).collect(Collectors.toList());

                // 获得所有供应商信息
                List<SupplierDO> supplierList = supplierDAO.getSupplierByCodes(materialPurchaseImportModelList.stream().map(MaterialPurchaseImportModel::getCode).collect(Collectors.toList()));
                Map<String, SupplierDO> supplierMap = supplierList.stream().collect(Collectors.toMap(SupplierDO::getCode, supplier -> supplier));

                List<MaterialInfoVO> materialInfoList = materialInfoDAO.getMaterialInfoBySome(supplierList.stream().map(SupplierDO::getId).collect(Collectors.toList()),
                        materialPurchaseImportModelList.stream().map(MaterialPurchaseImportModel::getSpec).collect(Collectors.toList()),
                        materialPurchaseImportModelList.stream().map(MaterialPurchaseImportModel::getItemName).collect(Collectors.toList()),
                        materialPurchaseImportModelList.stream().map(item -> Double.parseDouble(item.getUnitPrice())).collect(Collectors.toList()));

                List<MaterialPurchaseDO> materialPurchaseList = new ArrayList<>();
                for (MaterialPurchaseImportModel temp : materialPurchaseImportModelList) {
                    MaterialPurchaseDO materialPurchaseDO = new MaterialPurchaseDO();

                    if (StringUtils.isNumeric(temp.getIndex()) && !StringUtils.isBlank(temp.getItemName()) && !StringUtils.isBlank(temp.getSpec())) {
                        String supplierId = supplierMap.get(temp.getCode()).getId();

                        String materialInfoId = null;
                        for (MaterialInfoVO tempMaterialInfoVO : materialInfoList) {
                            if (supplierId.equals(tempMaterialInfoVO.getSupplierId()) && temp.getSpec().equals(tempMaterialInfoVO.getSpec())
                                    && temp.getItemName().equals(tempMaterialInfoVO.getItemName())
                                    && Double.parseDouble(temp.getUnitPrice()) == Double.parseDouble(tempMaterialInfoVO.getUnitPrice())) {
                                materialInfoId = tempMaterialInfoVO.getId();
                                break;
                            }
                        }
                        materialPurchaseDO.setMaterialInfoId(materialInfoId);

                        materialPurchaseDO.setSupplierId(supplierId);
                        materialPurchaseDO.setRemark(temp.getRemark());
                        materialPurchaseDO.setAmount(temp.getAmount());
                        materialPurchaseDO.setCreateTime(date);
                        if (!StringUtils.isBlank(temp.getHopeDeliveryDate())) {
                            materialPurchaseDO.setHopeDeliveryDate(HSSFDateUtil.getJavaDate(Double.parseDouble(temp.getHopeDeliveryDate())));
                        }
                        materialPurchaseDO.setUser(user);

                        materialPurchaseList.add(materialPurchaseDO);
                    }
                }

                return CallbackUtils.insertCallback("Customer importExcel", materialPurchaseList.toString(), () -> materialPurchaseDAO.insertAll(materialPurchaseList));
            } else {
                return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
            }
        } catch (Exception e) {
            logger.error("importExcel exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

}
