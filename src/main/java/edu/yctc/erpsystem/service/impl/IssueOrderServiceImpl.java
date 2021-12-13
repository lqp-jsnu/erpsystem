package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.IssueOrderModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.IssueOrderInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.IssueOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 生产补单表
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("issueOrderService")
public class IssueOrderServiceImpl implements IssueOrderInterService {

    /**订购单号*/
    private static final String ORDER_NUMBER = "orderNumber";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 客户料号*/
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 规格*/
    private static final String SPEC = "spec";

    private static final String END_DELIVERY_DATE_TIME = "endDeliveryDateTime";
    private static final String START_DELIVERY_DATE_TIME = "startDeliveryDateTime";
    private static final String END_TIME = "endTime";
    private static final String START_TIME = "startTime";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private IssueOrderDAO issueOrderDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Override
    public ResultDO<PageUtils<IssueOrderVO>> getIssueOrder(Map<String, Object> params) {
        return CallbackUtils.getCallback("getIssueOrder", params.toString(), () -> {
            List<IssueOrderVO> result = new ArrayList<>();

            // 得到所有补单信息
            List<IssueOrderDO> issueOrderList = issueOrderDAO.getIssueOrder(params);
            if (issueOrderList == null) {
                logger.error("getIssueOrder exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == issueOrderList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 获得所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrderByIds(issueOrderList.stream().map( IssueOrderDO::getCustomerOrderId).collect(Collectors.toList()));
            Map<String, CustomerOrderDO> customerOrderMap = customerOrderList.stream().collect(Collectors.toMap( CustomerOrderDO::getId, customerOrder -> customerOrder));

            // 获得所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds(customerOrderList.stream().map( CustomerOrderDO::getOriginalProductId).collect( Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap( OriginalProductDO::getId, originalProduct -> originalProduct));

            // 获得所有客户订单信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map( CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap( CustomerDO::getId, customer -> customer));

            // 得到所有录入者
            List<UserDO> userList = userDAO.getUserByIds(issueOrderList.stream().map( IssueOrderDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> userMap = userList.stream().collect(Collectors.toMap( UserDO::getId, user -> user));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(issueOrderList.stream().map( IssueOrderDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap( UserDO::getId, checker -> checker));

            for (IssueOrderDO tempIssueOrder : issueOrderList) {
                IssueOrderVO temp = new IssueOrderVO();

                String customerId = customerOrderMap.get(tempIssueOrder.getCustomerOrderId()).getCustomerId();
                String originalProductId = customerOrderMap.get(tempIssueOrder.getCustomerOrderId()).getOriginalProductId();

                temp.setOrderNumber(customerOrderMap.get(tempIssueOrder.getCustomerOrderId()).getOrderNumber());
                temp.setOrderAmount(customerOrderMap.get(tempIssueOrder.getCustomerOrderId()).getOrderAmount());
                temp.setOrderDate(customerOrderMap.get(tempIssueOrder.getCustomerOrderId()).getOrderDate());
                temp.setDeliveryDate(customerOrderMap.get(tempIssueOrder.getCustomerOrderId()).getDeliveryDate());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setName(customerMap.get(customerId).getName());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setUnit(originalProductMap.get(originalProductId).getUnit());
                temp.setProductNumber(originalProductMap.get(originalProductId).getProductNumber());
                temp.setIssueAmount(tempIssueOrder.getIssueAmount());
                temp.setUser(userMap.get(tempIssueOrder.getUser()).getName());
                if (tempIssueOrder.getChecker() != null) {
                    temp.setChecker(checkerMap.get(tempIssueOrder.getChecker()).getName());
                }
                temp.setId(tempIssueOrder.getId());
                temp.setCheckFlag(tempIssueOrder.getCheckFlag());
                temp.setIsGenerateManufacture(tempIssueOrder.getIsGeneraManufacture());
                temp.setCreateTime(tempIssueOrder.getCreateTime());
                temp.setRemark(tempIssueOrder.getRemark());
                temp.setIssueAmount(tempIssueOrder.getIssueAmount());
                temp.setCustomerId(customerId);
                temp.setCustomerOrderId(tempIssueOrder.getCustomerOrderId());
                temp.setOriginalProductId(originalProductId);

                result.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(issueOrderDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<IssueOrderVO>> getIssueOrderByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getIssueOrderByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<IssueOrderVO>> resultVO = getIssueOrder(params);
            if (resultVO == null) {
                logger.error("getIssueOrderByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<IssueOrderVO> result = resultVO.getModule().getRows();

            // 过滤
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

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (params.containsKey(END_DELIVERY_DATE_TIME)) {
                result = result.stream().filter( item -> {
                    try {
                        return  item.getDeliveryDate().before(format.parse( params.get( "endDeliveryDateTime" ).toString().replace( "T"," " )));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            if (params.containsKey(START_DELIVERY_DATE_TIME)) {
                result = result.stream().filter( item -> {
                    try {
                        return  item.getDeliveryDate().after(format.parse( params.get( "startDeliveryDateTime" ).toString().replace( "T"," " )));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            if (params.containsKey(END_TIME)) {
                result = result.stream().filter( item -> {
                    try {
                        return  item.getOrderDate().before(format.parse( params.get( "endTime" ).toString().replace( "T"," " )));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }

            if (params.containsKey(START_TIME)) {
                result = result.stream().filter( item -> {
                    try {
                        return  item.getOrderDate().after(format.parse( params.get( "startTime" ).toString().replace( "T"," " )));
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }


            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(IssueOrderDO issueOrderDO) {
        if (StringUtils.isBlank(issueOrderDO.getCustomerOrderId()) || StringUtils.isBlank(issueOrderDO.getIssueAmount()) || StringUtils.isBlank(issueOrderDO.getUser())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return CallbackUtils.insertCallback("IssueOrder", issueOrderDO.toString(), () -> issueOrderDAO.insert(issueOrderDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("IssueOrder", id, () -> issueOrderDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateIssueOrder(IssueOrderDO issueOrderDO) {
        if (StringUtils.isBlank(issueOrderDO.getId()) || StringUtils.isBlank(issueOrderDO.getIssueAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateIssueOrderById", issueOrderDO.toString(), () -> issueOrderDAO.updateIssueOrder(issueOrderDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(IssueOrderDO issueOrderDO) {
        if (StringUtils.isBlank(issueOrderDO.getId()) || StringUtils.isBlank(issueOrderDO.getChecker()) || StringUtils.isBlank(issueOrderDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("IssueOrder updateCheckerById", issueOrderDO.toString(), () -> issueOrderDAO.updateCheckerById(issueOrderDO));
    }

    @Override
    public ResultDO<Void> exportExcel(IssueOrderVO[] issueOrders) {
        if (null == issueOrders || 0 == issueOrders.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("IssueOrder exportExcel", Arrays.toString(issueOrders), () -> {
            List<IssueOrderModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (IssueOrderVO temp : issueOrders) {
                IssueOrderModel item = new IssueOrderModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setName(temp.getName());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setOrderAmount(temp.getOrderAmount());
                item.setUnit(temp.getUnit());
                item.setIssueAmount(temp.getIssueAmount());
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
                item.setUser(temp.getUser());
                item.setChecker(temp.getChecker());
                item.setCheckFlag(temp.getCheckFlag());
                item.setIsGenerateManufacture(temp.getIsGenerateManufacture());
                if (temp.getCreateTime() == null) {
                    item.setCreateTime(" ");
                } else {
                    item.setCreateTime(format.format(temp.getCreateTime()));
                }
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(17);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(26.14*256));
            columnWidth.put(2,(int)(10.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(23.43*256));
            columnWidth.put(5,(int)(23.43*256));
            columnWidth.put(6,(int)(23.43*256));
            columnWidth.put(7,(int)(23.43*256));
            columnWidth.put(8,(int)(9.0*256));
            columnWidth.put(9,(int)(10.86*256));
            columnWidth.put(10,(int)(26.14*256));
            columnWidth.put(11,(int)(26.14*256));
            columnWidth.put(12,(int)(26.14*256));
            columnWidth.put(13,(int)(26.14*256));
            columnWidth.put(14,(int)(10.86*256));
            columnWidth.put(15,(int)(26.14*256));
            columnWidth.put(16,(int)(26.14*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.ISSUE_ORDER_FILE_NAME, IssueOrderModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> make(IssueOrderVO issueOrder) {
        if ( StringUtils.isBlank(issueOrder.getId()) || StringUtils.isBlank(issueOrder.getIssueAmount()) || StringUtils.isBlank(issueOrder.getCustomerId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("IssueOrder make", issueOrder.toString(), () -> {
            List<ManufactureProcessSlaveDO> slaves = manufactureProcessSlaveDAO.getManufactureProcessSlaveBySome(issueOrder.getCustomerOrderId(), null);
            if ((slaves != null) && (slaves.size() > 0)) {
                Date newDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String serialnumberString = dateFormat.format(newDate);

                int orderNumber = 0;
                List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getJobTicketNumberBySerialnumberString(serialnumberString);
                if ((manufactureProcessSlaveList != null) && (manufactureProcessSlaveList.size() > 0)) {
                    HashSet<String> jobTicketNumberSet = new HashSet<>();
                    for (ManufactureProcessSlaveDO slave : manufactureProcessSlaveList) {
                        serialnumberString = slave.getJobTicketNumber();
                        serialnumberString = serialnumberString.split("-")[0];
                        jobTicketNumberSet.add(serialnumberString);
                    }
                    orderNumber = jobTicketNumberSet.size();
                }

                if (orderNumber < 9) {
                    serialnumberString = serialnumberString + "00" + (orderNumber + 1);
                } else if (orderNumber < 99) {
                    serialnumberString = serialnumberString + "0" + (orderNumber + 1);
                } else {
                    serialnumberString = serialnumberString + (orderNumber + 1);
                }

                List<ManufactureProcessSlaveDO> slaves2 = manufactureProcessSlaveDAO.getManufactureProcessSlaveBySome(issueOrder.getCustomerOrderId(), "是");
                if ((slaves2 != null) && (slaves2.size() > 0)) {
                    if (slaves2.size() + 1 < 10) {
                        serialnumberString = serialnumberString + "-00" + (slaves2.size() + 1) + "B";
                    } else if ((slaves2.size() + 1 >= 10) && (slaves2.size() < 100)) {
                        serialnumberString = serialnumberString + "-0" + (slaves2.size() + 1) + "B";
                    } else if (slaves2.size() + 1 >= 100) {
                        serialnumberString = serialnumberString + "-" + (slaves2.size() + 1) + "B";
                    } else {
                        serialnumberString = serialnumberString + "-001B";
                    }
                }

                ManufactureProcessSlaveDO manufactureProcessSlave = new ManufactureProcessSlaveDO();

                manufactureProcessSlave.setJobTicketNumber(serialnumberString);
                manufactureProcessSlave.setIsIssueOrder("是");
                manufactureProcessSlave.setManufactureProcessMasterId((slaves.get(0)).getManufactureProcessMasterId());
                manufactureProcessSlave.setEveryAmount(issueOrder.getIssueAmount());

                manufactureProcessSlaveDAO.insert(manufactureProcessSlave);
                issueOrderDAO.make( issueOrder.getId() );
            }
        });
    }
}
