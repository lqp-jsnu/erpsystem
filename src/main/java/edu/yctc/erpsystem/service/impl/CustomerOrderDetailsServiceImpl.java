package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.CustomerOrderDetailsModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.CustomerOrderDetailsInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;
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
 * 客户订单明细
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("customerOrderDetailsService")
public class CustomerOrderDetailsServiceImpl implements CustomerOrderDetailsInterService {

    // 过滤参数
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 客户料号*/
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 客户代号*/
    private static final String CODE = "code";
    /** 规格*/
    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Override
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderDetails(Map<String, Object> params) {
        return CallbackUtils.getCallback("getCustomerOrderDetails", params.toString(), () -> {
            List<CustomerOrderVO> result = new ArrayList<>();

            // 得到所有客户订单明细信息
            List<CustomerOrderDO> customerOrderDetailsList = customerOrderDAO.getCustomerOrder(params);
            if (customerOrderDetailsList == null) {
                logger.error("getCustomerOrderDetails exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == customerOrderDetailsList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds(customerOrderDetailsList.stream().map( CustomerOrderDO::getOriginalProductId).collect( Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap( OriginalProductDO::getId, originalProduct -> originalProduct));

            // 获得所有客户订单信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderDetailsList.stream().map( CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap( CustomerDO::getId, customer -> customer));

            // 得到所有审核者
            List<UserDO> userList = userDAO.getUserByIds(customerOrderDetailsList.stream().map( CustomerOrderDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> userMap = userList.stream().collect(Collectors.toMap( UserDO::getId, checker -> checker));

            for (CustomerOrderDO tempCustomerOrderDetails :customerOrderDetailsList) {
                CustomerOrderVO temp = new CustomerOrderVO();

                temp.setId(tempCustomerOrderDetails.getId());
                temp.setOrderNumber(tempCustomerOrderDetails.getOrderNumber());
                temp.setCode(customerMap.get(tempCustomerOrderDetails.getCustomerId()).getCode());
                temp.setName(customerMap.get(tempCustomerOrderDetails.getCustomerId()).getName());
                temp.setItemName(originalProductMap.get(tempCustomerOrderDetails.getOriginalProductId()).getItemName());
                temp.setSpec(originalProductMap.get(tempCustomerOrderDetails.getOriginalProductId()).getSpec());
                temp.setUnit(originalProductMap.get(tempCustomerOrderDetails.getOriginalProductId()).getUnit());
                temp.setProductNumber(originalProductMap.get(tempCustomerOrderDetails.getOriginalProductId()).getProductNumber());
                temp.setOrderAmount(tempCustomerOrderDetails.getOrderAmount());
                temp.setEveryOrderAmount(tempCustomerOrderDetails.getEveryOrderAmount());
                temp.setEveryProductAmount(tempCustomerOrderDetails.getEveryProductAmount());
                temp.setOrderDate(tempCustomerOrderDetails.getOrderDate());
                temp.setDeliveryDate(tempCustomerOrderDetails.getDeliveryDate());
                temp.setPlanArrivalDate(tempCustomerOrderDetails.getPlanArrivalDate());
                temp.setActualArrivalDate(tempCustomerOrderDetails.getActualArrivalDate());
                temp.setIsArrival(tempCustomerOrderDetails.getIsArrival());
                temp.setProductAmount(tempCustomerOrderDetails.getProductAmount());
                temp.setName(userMap.get(tempCustomerOrderDetails.getUser()).getName());
                temp.setCheckFlag(tempCustomerOrderDetails.getCheckFlag());
                temp.setFinalCheckFlag(tempCustomerOrderDetails.getFinalCheckFlag());
                temp.setIsGeneraManufacture(tempCustomerOrderDetails.getIsGeneraManufacture());
                temp.setCreateTime(tempCustomerOrderDetails.getCreateTime());
                temp.setRemark(tempCustomerOrderDetails.getRemark());
                temp.setCustomerId(tempCustomerOrderDetails.getCustomerId());
                temp.setOriginalProductId(tempCustomerOrderDetails.getOriginalProductId());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(customerOrderDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderDetailsByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getCustomerOrderDetailsByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<CustomerOrderVO>> resultVO = getCustomerOrderDetails(params);
            if (resultVO == null) {
                logger.error("getCustomerOrderDetailsByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<CustomerOrderVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(PRODUCT_NUMBER)) {
                result = result.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
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

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> updateCustomerOrderDetails(CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId())|| StringUtils.isBlank(customerOrderDO.getEveryOrderAmount())
                || StringUtils.isBlank(customerOrderDO.getProductAmount()) || StringUtils.isBlank(customerOrderDO.getEveryProductAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateCustomerOrderById", customerOrderDO.toString(), () -> customerOrderDAO.updateCustomerOrderDetailsById(customerOrderDO));
    }

    @Override
    public ResultDO<Void> updateFinalCheckFlagById(CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId()) || StringUtils.isBlank(customerOrderDO.getFinalCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("CustomerOrder updateFinalCheckFlagById", customerOrderDO.toString(), () -> customerOrderDAO.updateFinalCheckFlagById(customerOrderDO));
    }

    @Override
    public ResultDO<Void> finish(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("CustomerOrderFinish", id, () -> customerOrderDAO.finish(id));
    }

    @Override
    public ResultDO<Void> make(CustomerOrderVO customerOrder) {
        if (StringUtils.isBlank(customerOrder.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("make", customerOrder.toString(), () -> {
             customerOrderDAO.make(customerOrder.getId());

             ManufactureProcessMasterDO manufactureProcessMasterDO = new ManufactureProcessMasterDO();

             manufactureProcessMasterDO.setCustomerOrderId( customerOrder.getId());
             manufactureProcessMasterDO.setUser(customerOrder.getUser());

             manufactureProcessMasterDAO.insert(manufactureProcessMasterDO);

            int everyOrderAmount = Integer.parseInt(customerOrder.getEveryOrderAmount());
            int orderAmount = Integer.parseInt(customerOrder.getOrderAmount());
            int splitNumber = orderAmount % everyOrderAmount == 0 ? orderAmount / everyOrderAmount : orderAmount / everyOrderAmount + 1;

            int everyProductAmount = Integer.parseInt(customerOrder.getEveryProductAmount());
            int productAmount = Integer.parseInt(customerOrder.getProductAmount());
            int lastEveryProductAmount = 0;
            int lastEveryOrderAmount = 0;
            if (everyProductAmount * splitNumber != everyProductAmount) {
                lastEveryProductAmount = productAmount - everyProductAmount * (splitNumber - 1);
                lastEveryOrderAmount = orderAmount - everyOrderAmount * (splitNumber - 1);
            }
            if (splitNumber == 1) {
                lastEveryProductAmount = productAmount;
                lastEveryOrderAmount = orderAmount;
            }

            Date date = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
            String serialnumberString = dateFormatter.format(date);

            int orderNumber = 0;
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getJobTicketNumberBySerialnumberString(serialnumberString);
            if ((manufactureProcessSlaveList != null) && (manufactureProcessSlaveList.size() > 0)) {
                HashSet<String> jobTicketNumberSet = new HashSet<>();
                for (ManufactureProcessSlaveDO slave : manufactureProcessSlaveList) {
                    String jobTicketNumber = slave.getJobTicketNumber();
                    jobTicketNumber = jobTicketNumber.split("-")[0];
                    jobTicketNumberSet.add(jobTicketNumber);
                }
                orderNumber = jobTicketNumberSet.size();
            }

            List<ManufactureProcessSlaveDO> manufactureProcessSlaves = new ArrayList<>();
            for (int i = 0; i < splitNumber; ++i) {
                ManufactureProcessSlaveDO manufactureProcessSlaveDO = new ManufactureProcessSlaveDO();

                String jobTicketNumber = dateFormatter.format(date);
               if (orderNumber < 9) {
                   jobTicketNumber = jobTicketNumber + "00" + (orderNumber + 1);
               } else if (orderNumber < 99) {
                   jobTicketNumber = jobTicketNumber + "0" + (orderNumber + 1);
               } else {
                   jobTicketNumber = jobTicketNumber + (orderNumber + 1);
               }

               if (i < 10) {
                   jobTicketNumber = jobTicketNumber + "-00" + i;
               } else if (i < 100) {
                   jobTicketNumber = jobTicketNumber + "-0" + i;
               } else {
                   jobTicketNumber = jobTicketNumber + "-" + i;
               }
               manufactureProcessSlaveDO.setJobTicketNumber(jobTicketNumber);

               manufactureProcessSlaveDO.setManufactureProcessMasterId(manufactureProcessMasterDO.getId());

               if (i == splitNumber - 1) {
                   manufactureProcessSlaveDO.setEveryAmount(lastEveryProductAmount + "");
                   manufactureProcessSlaveDO.setEveryOrderAmount(lastEveryOrderAmount + "");
               } else {
                   manufactureProcessSlaveDO.setEveryAmount(customerOrder.getEveryProductAmount());
                   manufactureProcessSlaveDO.setEveryOrderAmount(customerOrder.getEveryOrderAmount());
               }

               manufactureProcessSlaveDO.setIsIssueOrder("否");
               manufactureProcessSlaves.add(manufactureProcessSlaveDO);
            }

            manufactureProcessSlaveDAO.insertAll(manufactureProcessSlaves);
        });
    }

    @Override
    public ResultDO<Void> exportExcel(CustomerOrderVO[] customerOrderVO) {
        if (null == customerOrderVO || 0 == customerOrderVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("CustomerOrder exportExcel", Arrays.toString(customerOrderVO), () -> {
            List<CustomerOrderDetailsModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (CustomerOrderVO temp : customerOrderVO) {
                CustomerOrderDetailsModel item = new CustomerOrderDetailsModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setUnit(temp.getUnit());
                item.setName(temp.getName());
                item.setOrderAmount(temp.getOrderAmount());
                item.setProductAmount(temp.getProductAmount());
                item.setEveryOrderAmount(temp.getEveryOrderAmount());
                item.setEveryProductAmount(temp.getEveryProductAmount());
                if (temp.getOrderDate() == null) {
                    item.setOrderDate(" ");
                } else {
                    item.setOrderDate(format.format(temp.getOrderDate()));
                }
                if (temp.getDeliveryDate() == null) {
                    item.setDeliveryDate(" ");
                } else {
                    item.setDeliveryDate(format.format(temp.getDeliveryDate()));
                }
                if (temp.getPlanArrivalDate() == null) {
                    item.setPlanArrivalDate(" ");
                } else {
                    item.setPlanArrivalDate(format.format(temp.getPlanArrivalDate()));
                }
                if (temp.getActualArrivalDate() == null) {
                    item.setActualArrivalDate(" ");
                } else {
                    item.setActualArrivalDate(format.format(temp.getActualArrivalDate()));
                }
                item.setIsArrival(temp.getIsArrival());
                item.setRemark(temp.getRemark());
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(18);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(26.14*256));
            columnWidth.put(2,(int)(10.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(23.43*256));
            columnWidth.put(5,(int)(24.86*256));
            columnWidth.put(6,(int)(10.86*256));
            columnWidth.put(7,(int)(9.0*256));
            columnWidth.put(8,(int)(10.86*256));
            columnWidth.put(9,(int)(10.86*256));
            columnWidth.put(10,(int)(10.86*256));
            columnWidth.put(11,(int)(26.14*256));
            columnWidth.put(12,(int)(26.14*256));
            columnWidth.put(13,(int)(26.14*256));
            columnWidth.put(14,(int)(26.14*256));
            columnWidth.put(15,(int)(10.86*256));
            columnWidth.put(16,(int)(10.86*256));
            columnWidth.put(17,(int)(10.86*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.CUSTOMER_DETAILS_FILE_NAME, CustomerOrderDetailsModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
