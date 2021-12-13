package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.service.FlowSheetPrintedInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.DetailedManufactureProcessSlaveVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 已列印流程单表
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("flowSheetPrintedService")
public class FlowSheetPrintedServiceImpl implements FlowSheetPrintedInterService {

    // 过滤参数
    /** 工作传票号码 */
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";
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
    private CustomerDAO customerDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Override
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getFlowSheetPrinted(Map<String, Object> params) {
        return CallbackUtils.getCallback("getFlowSheetPrinted", params.toString(), () -> {
            List<DetailedManufactureProcessSlaveVO> result = new ArrayList<>();

            // 得到所有已列印流程单信息
            List<ManufactureProcessSlaveDO> flowSheetPrintedList = manufactureProcessSlaveDAO.getManufactureProcessSlave(params);
            if (flowSheetPrintedList == null) {
                logger.error("getFlowSheetPrintedInterServices exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == flowSheetPrintedList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有制造流程单主表
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(flowSheetPrintedList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
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

            // 得到所有材料主信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(originalProductList.stream().map(OriginalProductDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap( MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            List<MainProductNumberVO> mainProductExpiryDateList = productInventoryDAO.getMainProductNumber(null);

            for (ManufactureProcessSlaveDO tempManufactureProcessSlave : flowSheetPrintedList) {
                DetailedManufactureProcessSlaveVO temp = new DetailedManufactureProcessSlaveVO();

                String customerOrderId = manufactureProcessMasterMap.get(tempManufactureProcessSlave.getManufactureProcessMasterId()).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();
                String materialInfoMasterId = originalProductMap.get(originalProductId).getMaterialInfoMasterId();

                List<MainProductNumberVO> mainProductNumber = mainProductExpiryDateList.stream().filter(item -> originalProductId.equals(item.getOriginalProductId())).collect( Collectors.toList());
                if (mainProductNumber.size() > 0) {
                    temp.setStorageAmount(mainProductNumber.get(0).getStorageAmount());
                }

                temp.setJobTicketNumber(tempManufactureProcessSlave.getJobTicketNumber());
                temp.setOrderNumber(customerOrderMap.get(customerOrderId).getOrderNumber());
                temp.setOrderAmount(customerOrderMap.get(customerOrderId).getOrderAmount());
                temp.setProductAmount(customerOrderMap.get(customerOrderId).getProductAmount());
                temp.setEveryAmount(tempManufactureProcessSlave.getEveryAmount());
                temp.setOrderDate(customerOrderMap.get(customerOrderId).getOrderDate());
                temp.setDeliveryDate(customerOrderMap.get(customerOrderId).getDeliveryDate());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setProductNumber(originalProductMap.get(originalProductId).getProductNumber());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setMasterItemName(materialInfoMasterMap.get(materialInfoMasterId).getItemName());
                temp.setMasterSpec(materialInfoMasterMap.get(materialInfoMasterId).getSpec());
                temp.setIsIssueOrder(tempManufactureProcessSlave.getIsIssueOrder());
                temp.setIsInHouse(tempManufactureProcessSlave.getIsInHouse());
                temp.setIsIntoMake(tempManufactureProcessSlave.getIsIntoMake());
                temp.setIsGenerateManufacture(tempManufactureProcessSlave.getIsGenerateManufacture());
                temp.setIsMaterialExport(tempManufactureProcessSlave.getIsMaterialExport());
                temp.setIsExportCheckPass(tempManufactureProcessSlave.getIsExportCheckPass());
                temp.setId(tempManufactureProcessSlave.getId());
                temp.setCustomerId(customerId);
                temp.setOriginalProductId(originalProductId);
                temp.setCustomerOrderId(customerOrderId);

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(manufactureProcessSlaveDAO.count(params), result));
        });

    }

    @Override
    public ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getFlowSheetPrintedByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getFlowSheetPrintedByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> resultVO = getFlowSheetPrinted(params);
            if (resultVO == null) {
                logger.error("getFlowSheetPrintedByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<DetailedManufactureProcessSlaveVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(JOB_TICKET_NUMBER)) {
                result = result.stream().filter(item -> null != item.getJobTicketNumber() && item.getJobTicketNumber().toLowerCase().contains(params.get(JOB_TICKET_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

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

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

}
