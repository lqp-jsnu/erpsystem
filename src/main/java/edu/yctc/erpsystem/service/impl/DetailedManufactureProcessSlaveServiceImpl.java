package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.DetailedManufactureProcessSlaveModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.DetailedManufactureProcessSlaveInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.DetailedManufactureProcessSlaveVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;
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
 * 制造流程单详细
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("detailedManufactureProcessSlaveService")
public class DetailedManufactureProcessSlaveServiceImpl implements DetailedManufactureProcessSlaveInterService {

    // 过滤参数
    /** 客户订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 客户代号*/
    private static final String CODE = "code";
    /** 产品料号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 规格*/
    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Resource
    private MaterialInventoryMasterDAO materialInventoryMasterDAO;

    @Resource
    private MaterialInventoryDAO materialInventoryDAO;

    @Resource
    private MaterialExportDAO materialExportDAO;

    @Override
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getDetailedManufactureProcessSlaves(Map<String, Object> params) {
        return CallbackUtils.getCallback("getDetailedManufactureProcessSlaves", params.toString(), () -> {
            List<DetailedManufactureProcessSlaveVO> result = new ArrayList<>();

            // 得到所有制造流程单详细信息
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlave(params);
            if (manufactureProcessSlaveList == null) {
                logger.error("getMaterialDump exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == manufactureProcessSlaveList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有成品原始信息
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map( ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect( Collectors.toList()));
            Map<String, ManufactureProcessMasterDO> manufactureProcessMasterMap = manufactureProcessMasterList.stream().collect(Collectors.toMap( ManufactureProcessMasterDO::getId, manufactureProcessMaster -> manufactureProcessMaster));

            // 得到所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map( ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap( CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 获得所有客户信息管理信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map( CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap( CustomerDO::getId, customer -> customer));

            // 获得所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds(customerOrderList.stream().map( CustomerOrderDO::getOriginalProductId).collect( Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap( OriginalProductDO::getId, originalProduct -> originalProduct));

            // 得到所有材料主信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(originalProductList.stream().map( OriginalProductDO::getMaterialInfoMasterId).collect( Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap( MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            List<MainProductNumberVO> mainProductExpiryDateList = productInventoryDAO.getMainProductNumber(null);
            List<MaterialInventoryVO> materialInventoryList = materialInventoryDAO.getMaterialInventory(null);
            List<MaterialInventoryMasterDO> materialInventoryMasterList = materialInventoryMasterDAO.getMaterialInventoryMaster(null);

            for (ManufactureProcessSlaveDO tempManufactureProcessSlave : manufactureProcessSlaveList) {
                DetailedManufactureProcessSlaveVO temp = new DetailedManufactureProcessSlaveVO();

                String customerOrderId = manufactureProcessMasterMap.get(tempManufactureProcessSlave.getManufactureProcessMasterId()).getCustomerOrderId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String materialInfoMasterId = originalProductMap.get(originalProductId).getMaterialInfoMasterId();

                temp.setMaterialInfoMasterId(materialInfoMasterId);

                List<MaterialInventoryMasterDO> tempMaterialInventoryMasterList = materialInventoryMasterList.stream().filter(item -> materialInfoMasterId.equals(item.getMaterialInfoMasterId())).collect( Collectors.toList());
                if (tempMaterialInventoryMasterList.size() > 0) {
                    temp.setMaterialInventoryMasterId(tempMaterialInventoryMasterList.get(0).getId());
                }

                List<MaterialInventoryVO> tempMaterialInventoryList = materialInventoryList.stream().filter(item -> materialInfoMasterId.equals(item.getMaterialInfoMasterId())).collect( Collectors.toList());
                if (tempMaterialInventoryList.size() > 0) {
                    temp.setLeftAmount(tempMaterialInventoryList.get(0).getLeftAmount());

                    if (Double.parseDouble(temp.getLeftAmount()) < Double.parseDouble(customerOrderMap.get(customerOrderId).getProductAmount())) {
                        List<MainProductNumberVO> mainProductNumber = mainProductExpiryDateList.stream().filter(item -> originalProductId.equals(item.getOriginalProductId())).collect( Collectors.toList());
                        if (mainProductNumber.size() > 0) {
                            temp.setStorageAmount(mainProductNumber.get(0).getStorageAmount());
                        }
                    }
                }

                temp.setJobTicketNumber(tempManufactureProcessSlave.getJobTicketNumber());
                temp.setOrderNumber(customerOrderMap.get(customerOrderId).getOrderNumber());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setProductNumber(originalProductMap.get(originalProductId).getProductNumber());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setMasterItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setMasterSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setMaterialInfoMasterId(materialInfoMasterId);
                temp.setOriginalProductId(originalProductId);
                temp.setCustomerOrderId(customerOrderId);
                temp.setCustomerId(customerId);
                temp.setIsIssueOrder(tempManufactureProcessSlave.getIsIssueOrder());
                temp.setIsInHouse(tempManufactureProcessSlave.getIsInHouse());
                temp.setIsIntoMake(tempManufactureProcessSlave.getIsIntoMake());
                temp.setIsGenerateManufacture(tempManufactureProcessSlave.getIsGenerateManufacture());
                temp.setIsMaterialExport(tempManufactureProcessSlave.getIsMaterialExport());
                temp.setIsExportCheckPass(tempManufactureProcessSlave.getIsExportCheckPass());
                temp.setOrderAmount(customerOrderMap.get(customerOrderId).getOrderAmount());
                temp.setProductAmount(customerOrderMap.get(customerOrderId).getProductAmount());
                temp.setEveryAmount(tempManufactureProcessSlave.getEveryAmount());
                temp.setOrderDate(customerOrderMap.get(customerOrderId).getOrderDate());
                temp.setDeliveryDate(customerOrderMap.get(customerOrderId).getDeliveryDate());
                temp.setId(tempManufactureProcessSlave.getId());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(manufactureProcessSlaveDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getDetailedManufactureProcessSlaveByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getDetailedManufactureProcessSlaveByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> resultVO = getDetailedManufactureProcessSlaves(params);
            if (resultVO == null) {
                logger.error("getDetailedManufactureProcessSlaveByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<DetailedManufactureProcessSlaveVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
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

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> updateMaterialExport(DetailedManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getMaterialInventoryMasterId()) || StringUtils.isBlank(manufactureProcessSlaveVO.getMaterialInfoMasterId())
                || StringUtils.isBlank(manufactureProcessSlaveVO.getExportNumber()) || StringUtils.isBlank(manufactureProcessSlaveVO.getExportDate().toString())
                || StringUtils.isBlank(manufactureProcessSlaveVO.getUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialExport", manufactureProcessSlaveVO.toString(), () -> {
            MaterialExportDO materialExportDO = new MaterialExportDO();

            materialExportDO.setMaterialInventoryMasterId(manufactureProcessSlaveVO.getMaterialInventoryMasterId());
            materialExportDO.setManufactureProcessSlaveId(manufactureProcessSlaveVO.getId());
            materialExportDO.setIssueNumber(manufactureProcessSlaveVO.getIssueNumber());
            materialExportDO.setNumber(manufactureProcessSlaveVO.getExportNumber());
            materialExportDO.setUser(manufactureProcessSlaveVO.getUserId());
            materialExportDO.setRemark(manufactureProcessSlaveVO.getRemark());

            materialExportDAO.insert(materialExportDO);

            manufactureProcessSlaveDAO.updateIsMaterialExport(manufactureProcessSlaveVO.getId(), "是");
        });
    }

    @Override
    public ResultDO<Void> updateIsIntoMake(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateIsIntoMake", id, () -> manufactureProcessSlaveDAO.updateIsIntoMake(id));
    }

    @Override
    public ResultDO<Void> exportExcel(DetailedManufactureProcessSlaveVO[] detailedManufactureProcessSlave) {
        if (null == detailedManufactureProcessSlave || 0 == detailedManufactureProcessSlave.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return CallbackUtils.exportCallback("DetailedManufactureProcessSlave exportExcel", Arrays.toString(detailedManufactureProcessSlave), () -> {
            List<DetailedManufactureProcessSlaveModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (DetailedManufactureProcessSlaveVO temp : detailedManufactureProcessSlave) {
                DetailedManufactureProcessSlaveModel item = new DetailedManufactureProcessSlaveModel();

                item.setIndex(Integer.toString(i++));
                item.setJobTicketNumber(temp.getJobTicketNumber());
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setMasterItemName(temp.getMasterItemName());
                item.setMasterSpec(temp.getMasterSpec());
                item.setOrderAmount(temp.getOrderAmount());
                item.setEveryOrderAmount(temp.getEveryOrderAmount());
                item.setProductAmount(temp.getProductAmount());
                item.setOrderDate(format.format(temp.getOrderDate()));
                item.setDeliveryDate(format.format(temp.getDeliveryDate()));
                item.setIsIssueOrder(temp.getIsIssueOrder());
                item.setIsInHouse(temp.getIsInHouse());
                item.setIsIntoMake(temp.getIsIntoMake());
                item.setIsGenerateManufacture(temp.getIsGenerateManufacture());
                item.setIsMaterialExport(temp.getIsMaterialExport());
                item.setIsExportCheckPass(temp.getIsExportCheckPass());

                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(21);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(23.43*256));
            columnWidth.put(2,(int)(23.43*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(23.43*256));
            columnWidth.put(5,(int)(23.43*256));
            columnWidth.put(6,(int)(23.43*256));
            columnWidth.put(7,(int)(23.43*256));
            columnWidth.put(8,(int)(23.43*256));
            columnWidth.put(9,(int)(10.86*256));
            columnWidth.put(10,(int)(10.86*256));
            columnWidth.put(11,(int)(10.86*256));
            columnWidth.put(12,(int)(24.86*256));
            columnWidth.put(13,(int)(26.14*256));
            columnWidth.put(14,(int)(26.14*256));
            columnWidth.put(15,(int)(23.43*256));
            columnWidth.put(16,(int)(23.43*256));
            columnWidth.put(17,(int)(23.43*256));
            columnWidth.put(18,(int)(23.43*256));
            columnWidth.put(19,(int)(23.43*256));
            columnWidth.put(20,(int)(23.43*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.DETAILED_MANUAL_FACE_PROCESS_NAME, DetailedManufactureProcessSlaveModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> exportExcelDianZu(ManufactureProcessSlaveDO manufactureProcessSlaveDO) {
        if (StringUtils.isBlank(manufactureProcessSlaveDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateIsGeneralManufactureProcess", manufactureProcessSlaveDO.toString(), () -> {
            MaterialExportDO materialExportDO = materialExportDAO.getMaterialExportByManufactureProcessSlaveId(manufactureProcessSlaveDO.getId());
            ManufactureProcessSlaveDO tempManufactureProcessSlaveDO = manufactureProcessSlaveDAO.getManufactureProcessSlaveById(manufactureProcessSlaveDO.getId());
            ManufactureProcessMasterDO manufactureProcessMasterDO = manufactureProcessMasterDAO.getManufactureProcessMasterById(tempManufactureProcessSlaveDO.getManufactureProcessMasterId());
            CustomerOrderDO customerOrderDO = customerOrderDAO.getCustomerOrderById(manufactureProcessMasterDO.getCustomerOrderId());
            CustomerDO customerDO = customerDAO.getCustomerById(customerOrderDO.getCustomerId());
            OriginalProductDO originalProductDO = originalproductDAO.getOriginalProductById(customerOrderDO.getOriginalProductId());
            MaterialInfoMasterDO materialInfoMasterDO = materialInfoMasterDAO.getMaterialInfoMasterById(originalProductDO.getMaterialInfoMasterId());
            MainProductNumberVO mainProductNumber = productInventoryDAO.getMainProductNumber(null).stream().filter(item -> originalProductDO.getId().equals(item.getOriginalProductId())).collect(Collectors.toList()).get(0);

            // 导出excel
            Map<String, Object> map = new HashMap<>(15);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            map.put("makeDate", simpleDateFormat.format(new Date()));
            map.put("jobTicketNumber", tempManufactureProcessSlaveDO.getJobTicketNumber());
            map.put("code", customerDO.getCode());
            map.put("label", originalProductDO.getLabel());
            map.put("productSpec", originalProductDO.getSpec());
            map.put("productNumber", originalProductDO.getProductNumber());
            map.put("colorCode", originalProductDO.getColorCode());
            map.put("orderNumber", customerOrderDO.getOrderNumber());
            map.put("orderDate", customerOrderDO.getOrderDate());
            map.put("itemName", materialInfoMasterDO.getItemName());
            map.put("spec", materialInfoMasterDO.getSpec());
            map.put("issueNumber", Math.round(Double.parseDouble(materialExportDO.getIssueNumber())));
            map.put("everyAmount",  Math.round(Double.parseDouble(customerOrderDO.getOrderAmount())) + originalProductDO.getUnit());
            map.put("requireAmount", Math.round(Double.parseDouble(tempManufactureProcessSlaveDO.getEveryOrderAmount())) + originalProductDO.getUnit());
            map.put("inventoryAmount", Math.round(Double.parseDouble(mainProductNumber.getStorageAmount())) + originalProductDO.getUnit());
            map.put("specRequire", originalProductDO.getRemark());

            MyExcel.writeTemplate(ConstantHolder.FLOW_SHEET_PRINTED_SERVICE_IMPL, "制造流程单（电阻）（亿）.xlsx", map);

            manufactureProcessSlaveDAO.updateIsGeneralManufactureProcess(manufactureProcessSlaveDO.getId());
        });
    }

    @Override
    public ResultDO<Void> exportExcelTangHuang(ManufactureProcessSlaveDO manufactureProcessSlaveDO) {
        if (StringUtils.isBlank(manufactureProcessSlaveDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateIsGeneralManufactureProcess", manufactureProcessSlaveDO.toString(), () -> {
            MaterialExportDO materialExportDO = materialExportDAO.getMaterialExportByManufactureProcessSlaveId(manufactureProcessSlaveDO.getId());
            ManufactureProcessSlaveDO tempManufactureProcessSlaveDO = manufactureProcessSlaveDAO.getManufactureProcessSlaveById(manufactureProcessSlaveDO.getId());
            ManufactureProcessMasterDO manufactureProcessMasterDO = manufactureProcessMasterDAO.getManufactureProcessMasterById(tempManufactureProcessSlaveDO.getManufactureProcessMasterId());
            CustomerOrderDO customerOrderDO = customerOrderDAO.getCustomerOrderById(manufactureProcessMasterDO.getCustomerOrderId());
            CustomerDO customerDO = customerDAO.getCustomerById(customerOrderDO.getCustomerId());
            OriginalProductDO originalProductDO = originalproductDAO.getOriginalProductById(customerOrderDO.getOriginalProductId());
            MaterialInfoMasterDO materialInfoMasterDO = materialInfoMasterDAO.getMaterialInfoMasterById(originalProductDO.getMaterialInfoMasterId());
            MainProductNumberVO mainProductNumber = productInventoryDAO.getMainProductNumber(null).stream().filter(item -> originalProductDO.getId().equals(item.getOriginalProductId())).collect(Collectors.toList()).get(0);

            // 导出excel
            Map<String, Object> map = new HashMap<>(15);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            map.put("makeDate", simpleDateFormat.format(new Date()));
            map.put("jobTicketNumber", tempManufactureProcessSlaveDO.getJobTicketNumber());
            map.put("label", originalProductDO.getLabel());
            map.put("code", customerDO.getCode());
            map.put("productSpec", originalProductDO.getSpec());
            map.put("productNumber", originalProductDO.getProductNumber());
            map.put("orderNumber", customerOrderDO.getOrderNumber());
            map.put("orderDate", customerOrderDO.getOrderDate());
            map.put("itemName", materialInfoMasterDO.getItemName());
            map.put("spec", materialInfoMasterDO.getSpec());
            map.put("issueNumber", Math.round(Double.parseDouble(materialExportDO.getIssueNumber())));
            map.put("everyAmount",  Math.round(Double.parseDouble(tempManufactureProcessSlaveDO.getEveryAmount())) + originalProductDO.getUnit());
            map.put("requireAmount", Math.round(Double.parseDouble(tempManufactureProcessSlaveDO.getEveryOrderAmount())) + originalProductDO.getUnit());
            map.put("inventoryAmount",Math.round(Double.parseDouble(mainProductNumber.getStorageAmount())) + originalProductDO.getUnit());

            MyExcel.writeTemplate(ConstantHolder.FLOW_SHEET_TAN_HUANG_SERVICE_IMPL, "制造流程单（弹簧）（永）.xlsx", map);

            manufactureProcessSlaveDAO.updateIsGeneralManufactureProcess(manufactureProcessSlaveDO.getId());
        });
    }

}
