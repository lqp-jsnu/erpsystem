package edu.yctc.erpsystem.service.impl;


import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.service.ManufactureProcessMasterInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.util.ZipUtils;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.ManufactureProcessMasterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static edu.yctc.erpsystem.util.FileUtils.deleteDir;
import static edu.yctc.erpsystem.util.FileUtils.isDirExists;

/**
 * 制造流程单主信息逻辑接口
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("manufactureProcessMasterService")
public class ManufactureProcessMasterServiceImpl implements ManufactureProcessMasterInterService {

    //过滤参数
    /** 客户订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 客户代号 */
    private static final String CODE = "code";
    /** 品名 */
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";
    /** 客户料号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 客户名称 */
    private static final String NAME = "name";

    private static final String IS_IN_HOUSE = "是";

    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";
    private static final String START_DELIVERY_DATE_TIME = "startDeliveryDateTime";
    private static final String END_DELIVERY_DATE_TIME = "endDeliveryDateTime";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private MaterialExportDAO materialExportDAO;

    @Override
    public ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMaster(Map<String, Object> params){
        return CallbackUtils.getCallback("getManufactureProcessMaster", params.toString(), () -> {
            List<ManufactureProcessMasterVO> result = new ArrayList<>();

            // 得到所有制造流程单信息
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMaster(params);
            if (manufactureProcessMasterList == null) {
                logger.error("getManufactureProcessMaster exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == manufactureProcessMasterList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap(CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 获得所有客户信息管理
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map(CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap( CustomerDO::getId, customer -> customer));

            // 得到所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds(customerOrderList.stream().map(CustomerOrderDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap( OriginalProductDO::getId, originalProduct-> originalProduct));

            // 获得所有生产者信息
            List<UserDO> userList = userDAO.getUserByIds(manufactureProcessMasterList.stream().map(ManufactureProcessMasterDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> userMap = userList.stream().collect(Collectors.toMap(UserDO::getId, user -> user));

            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlave(null);

            for (ManufactureProcessMasterDO tempManufactureProcessMaster : manufactureProcessMasterList) {
                ManufactureProcessMasterVO temp = new ManufactureProcessMasterVO();

                String customerId = customerOrderMap.get(tempManufactureProcessMaster.getCustomerOrderId()).getCustomerId();
                String originalProductId = customerOrderMap.get(tempManufactureProcessMaster.getCustomerOrderId()).getOriginalProductId();
                List<ManufactureProcessSlaveDO> tempManufactureProcessSlaveList = manufactureProcessSlaveList.stream().filter(item -> tempManufactureProcessMaster.getId().equals(item.getManufactureProcessMasterId())).collect(Collectors.toList());

                double everyAmount = 0;
                for (ManufactureProcessSlaveDO tempManufactureProcessSlaveDO : tempManufactureProcessSlaveList) {
                    everyAmount += Double.parseDouble(tempManufactureProcessSlaveDO.getEveryAmount());
                }

                temp.setId(tempManufactureProcessMaster.getId());
                temp.setOrderNumber(customerOrderMap.get(tempManufactureProcessMaster.getCustomerOrderId()).getOrderNumber());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setName(customerMap.get(customerId).getName());
                temp.setProductNumber(originalProductMap.get(originalProductId).getProductNumber());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setEveryAmount("" + everyAmount);
                temp.setProductAmount(customerOrderMap.get(tempManufactureProcessMaster.getCustomerOrderId()).getProductAmount());
                temp.setSplitNumber("" + tempManufactureProcessSlaveList.size());
                temp.setInHouseNumber("" + tempManufactureProcessSlaveList.stream().filter(item -> IS_IN_HOUSE.equals(item.getIsInHouse())).count());
                temp.setOrderDate(customerOrderMap.get(tempManufactureProcessMaster.getCustomerOrderId()).getOrderDate());
                temp.setDeliveryDate(customerOrderMap.get(tempManufactureProcessMaster.getCustomerOrderId()).getDeliveryDate());
                temp.setUser(userMap.get(tempManufactureProcessMaster.getUser()).getName());
                temp.setCreateTime(tempManufactureProcessMaster.getCreateTime());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(manufactureProcessMasterDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMasterByConditions(Map<String, Object> params){
        return CallbackUtils.getCallback("getManufactureProcessMasterByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ManufactureProcessMasterVO>> resultVO = getManufactureProcessMaster(params);
            if (resultVO == null) {
                logger.error("getManufactureProcessMasterByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ManufactureProcessMasterVO> result = resultVO.getModule().getRows();

            //过滤
            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(PRODUCT_NUMBER)) {
                result = result.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(NAME)) {
                result = result.stream().filter(item -> null != item.getName() && item.getName().toLowerCase().contains(params.get(NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (params.containsKey(START_TIME)) {
                result = result.stream().filter(item -> {
                    try {
                        return item.getOrderDate().after(format.parse(params.get("startTime").toString().replace("T"," ")));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            if (params.containsKey(END_TIME)) {
                result = result.stream().filter(item -> {
                    try {
                        return item.getOrderDate().before(format.parse(params.get("endTime").toString().replace("T"," ")));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            if (params.containsKey(START_DELIVERY_DATE_TIME)) {
                result = result.stream().filter(item -> {
                    try {
                        return item.getOrderDate().after(format.parse(params.get("startDeliveryDateTime").toString().replace("T"," ")));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            if (params.containsKey(END_DELIVERY_DATE_TIME)) {
                result = result.stream().filter(item -> {
                    try {
                        return item.getOrderDate().before(format.parse(params.get("endDeliveryDateTime").toString().replace("T"," ")));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> exportAllDianZuExcel(ManufactureProcessSlaveDO[] manufactureProcessSlaveDO) {
        if (null == manufactureProcessSlaveDO || 0 == manufactureProcessSlaveDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        isDirExists("C:/ERPSystem/file/制造流程单");

        return CallbackUtils.updateCallback("exportAllDianZuExcel", Arrays.toString(manufactureProcessSlaveDO), () -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 制定输出格式

            for (ManufactureProcessSlaveDO temp : manufactureProcessSlaveDO) {
                MaterialExportDO materialExportDO = materialExportDAO.getMaterialExportByManufactureProcessSlaveId(temp.getId());
                ManufactureProcessSlaveDO tempManufactureProcessSlaveDO = manufactureProcessSlaveDAO.getManufactureProcessSlaveById(temp.getId());
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

                MyExcel.writeTemplate(ConstantHolder.FLOW_SHEET_PRINTED_SERVICE_IMPL, "/制造流程单/制造流程单（电阻）（亿）" + sdf.format(new Date()) + ".xlsx", map);
            }

            ZipUtils.toZip("C:/ERPSystem/file/制造流程单", "C:/ERPSystem/file/制造流程单.zip", true);
            deleteDir("C:/ERPSystem/file/制造流程单");
        });
    }

    @Override
    public ResultDO<Void> exportAllTanHuangExcel(ManufactureProcessSlaveDO[] manufactureProcessSlaveDO) {
        if (null == manufactureProcessSlaveDO || 0 == manufactureProcessSlaveDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        isDirExists("C:/ERPSystem/file/制造流程单");

        return CallbackUtils.updateCallback("exportAllTanHuangExcel", Arrays.toString(manufactureProcessSlaveDO), () -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); //制定输出格式

            for (ManufactureProcessSlaveDO temp : manufactureProcessSlaveDO) {
                MaterialExportDO materialExportDO = materialExportDAO.getMaterialExportByManufactureProcessSlaveId(temp.getId());
                ManufactureProcessSlaveDO tempManufactureProcessSlaveDO = manufactureProcessSlaveDAO.getManufactureProcessSlaveById(temp.getId());
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

                MyExcel.writeTemplate(ConstantHolder.FLOW_SHEET_PRINTED_SERVICE_IMPL, "/制造流程单/制造流程单（弹簧）（永）" + sdf.format(new Date()) + ".xlsx", map);
            }

            ZipUtils.toZip("C:/ERPSystem/file/制造流程单", "C:/ERPSystem/file/制造流程单.zip", true);
            deleteDir("C:/ERPSystem/file/制造流程单");
        });
    }

}
