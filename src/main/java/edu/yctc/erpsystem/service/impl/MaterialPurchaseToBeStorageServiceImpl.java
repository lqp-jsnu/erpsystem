package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialPurchaseToBeStorageModel;
import edu.yctc.erpsystem.service.MaterialPurchaseToBeStorageInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialPurchaseToBeStorageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 原材料待入库逻辑接口实现
 *
 * @author wjd
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialPurchaseToBeStorageService")
public  class MaterialPurchaseToBeStorageServiceImpl implements MaterialPurchaseToBeStorageInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";
    /** 采购单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 发票抬头 */
    private static final String TITLE_NAME = "titleName";

    /** 供应商id */
    private static final String SUPPLIER_ID = "supplierId";

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Resource
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private InvoiceTitleDAO invoiceTitleDAO;

    @Override
    public ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorage(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialPurchaseToBeStorage", params.toString(), () -> {
            List<MaterialPurchaseToBeStorageVO> result = new ArrayList<>();

            // 得到所有原材料待入库信息
            List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageList = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorage(params);
            if (materialPurchaseToBeStorageList == null) {
                logger.error("getMaterialPurchaseToBeStorage exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == materialPurchaseToBeStorageList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有材料采购信息
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

            // 获得所有发票抬头信息
            List<InvoiceTitleDO> invoiceTitleList = invoiceTitleDAO.getInvoiceTitleByIds(materialPurchaseToBeStorageList.stream().map(MaterialPurchaseToBeStorageDO::getInvoiceTitleId).collect(Collectors.toList()));
            Map<String, InvoiceTitleDO> invoiceTitleMap = invoiceTitleList.stream().collect(Collectors.toMap(InvoiceTitleDO::getId, invoiceTitle-> invoiceTitle));

            for (MaterialPurchaseToBeStorageDO tempMaterialPurchaseToBeStorage : materialPurchaseToBeStorageList) {
                MaterialPurchaseToBeStorageVO temp = new MaterialPurchaseToBeStorageVO();

                String materialPurchaseId = tempMaterialPurchaseToBeStorage.getMaterialPurchaseId();
                String materialInfoId = materialPurchaseMap.get(materialPurchaseId).getMaterialInfoId();
                String materialInfoMasterId = materialInfoMap.get(materialInfoId).getMaterialInfoMasterId();
                String supplierId = materialInfoMap.get(materialInfoId).getSupplierId();

                temp.setInvoiceTitleId(tempMaterialPurchaseToBeStorage.getInvoiceTitleId());
                temp.setSupplierId(supplierId);
                temp.setId(tempMaterialPurchaseToBeStorage.getId());
                temp.setOrderNumber(tempMaterialPurchaseToBeStorage.getOrderNumber());
                temp.setCode(supplierMap.get(supplierId).getCode());
                temp.setItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setAmount(materialPurchaseMap.get(materialPurchaseId).getAmount());
                temp.setInAmount(tempMaterialPurchaseToBeStorage.getInAmount());
                temp.setUnit(materialInfoMasterMap.get(materialInfoMasterId).getUnit());
                temp.setUnitPrice(materialInfoMap.get(materialInfoId).getUnitPrice());
                temp.setHopeDeliveryDate(tempMaterialPurchaseToBeStorage.getHopeDeliveryDate());
                temp.setTitleName(invoiceTitleMap.get(tempMaterialPurchaseToBeStorage.getInvoiceTitleId()).getName());
                temp.setCreateTime(tempMaterialPurchaseToBeStorage.getCreateTime());
                temp.setRemark(tempMaterialPurchaseToBeStorage.getRemark());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialPurchaseToBeStorageDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorageByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialPurchaseToBeStorageByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> resultVO = getMaterialPurchaseToBeStorage(params);
            if (resultVO == null) {
                logger.error("getMaterialPurchaseToBeStorageByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialPurchaseToBeStorageVO> result = resultVO.getModule().getRows();

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

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(TITLE_NAME)) {
                result = result.stream().filter(item -> null != item.getTitleName() && item.getTitleName().toLowerCase().contains(params.get(TITLE_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> exportExcel(List<MaterialPurchaseToBeStorageVO> materialPurchaseToBeStorageVO) {
        if (null == materialPurchaseToBeStorageVO || 0 == materialPurchaseToBeStorageVO.size()) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("MaterialPurchaseToBeStorage exportExcel", materialPurchaseToBeStorageVO.toString(), () -> {

            SupplierDO supplierDO = supplierDAO.getSupplierById(materialPurchaseToBeStorageVO.get(0).getSupplierId());

            Map<String, Object> map = new HashMap<>(9);

            map.put("titleName", materialPurchaseToBeStorageVO.get(0).getTitleName());
            map.put("orderNumber", materialPurchaseToBeStorageVO.get(0).getOrderNumber());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            map.put("orderDate", simpleDateFormat.format(new Date()));

            map.put("code", supplierDO.getCode());
            map.put("name", supplierDO.getName());
            map.put("contact", supplierDO.getContact());
            map.put("fixedTelephone", supplierDO.getFixedTelephone());
            map.put("fax", supplierDO.getFax());

            List<MaterialPurchaseToBeStorageModel> materialPurchaseToBeStorageModelList = new ArrayList<>();

            int i = 0;
            for (MaterialPurchaseToBeStorageVO temp : materialPurchaseToBeStorageVO) {
                MaterialPurchaseToBeStorageModel materialPurchaseToBeStorageModel = new MaterialPurchaseToBeStorageModel();

                materialPurchaseToBeStorageModel.setIndex("" + i++);
                materialPurchaseToBeStorageModel.setItemName(temp.getItemName());
                materialPurchaseToBeStorageModel.setSpec(temp.getSpec());
                materialPurchaseToBeStorageModel.setAmount(temp.getAmount());
                materialPurchaseToBeStorageModel.setUnit(temp.getUnit());
                materialPurchaseToBeStorageModel.setUnitPrice(temp.getUnitPrice());
                materialPurchaseToBeStorageModel.setTotalPrice("" + Double.parseDouble(temp.getAmount()) * Double.parseDouble(temp.getUnitPrice()));
                materialPurchaseToBeStorageModel.setHopeDeliveryDate(simpleDateFormat.format(temp.getHopeDeliveryDate()));
                materialPurchaseToBeStorageModel.setRemark(temp.getRemark());

                materialPurchaseToBeStorageModelList.add(materialPurchaseToBeStorageModel);
            }

            map.put("materialPurchaseToBeStorage", materialPurchaseToBeStorageModelList);

            MyExcel.writeTemplate(ConstantHolder.MATERIAL_PURCHASE_STORED_FILE_NAME, "原材料采购单（发，亿，永）.xlsx", map);
        });
    }

    /**
     * 通过供应商代号获得所有材料信息
     */
    @Override
    public ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> getMaterialPurchaseToBeStorageBySearchSuppId(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialPurchaseToBeStorageBySearchSuppId", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialPurchaseToBeStorageVO>> resultVO = getMaterialPurchaseToBeStorage(params);
            if (resultVO == null) {
                logger.error("getMaterialPurchaseToBeStorageByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialPurchaseToBeStorageVO> result = resultVO.getModule().getRows();

            // 模糊查询spec
            String spec = params.get(SPEC).toString().toLowerCase();
            result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(spec)).collect(Collectors.toList());

            // 查询supplierId
            String supplierId = params.get(SUPPLIER_ID).toString();
            result = result.stream().filter(item -> null != item.getSupplierId() && item.getSupplierId().equals(supplierId)).collect(Collectors.toList());

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

}
