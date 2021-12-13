package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.CustomerDAO;
import edu.yctc.erpsystem.dao.CustomerOrderDAO;
import edu.yctc.erpsystem.dao.OriginalProductDAO;
import edu.yctc.erpsystem.dao.UserDAO;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.OrderQueryModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.OrderQueryInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;
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
 * 订单查询
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("orderQueryService")
public class OrderQueryServiceImpl implements OrderQueryInterService {

    // 过滤参数
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 客户料号*/
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 客户代号*/
    private static final String CODE = "code";
    /** 规格*/
    private static final String SPEC = "spec";
    /** 是否生成制造流程单*/
    private static final String IS_GENERA_MANUFACTURE = "isGeneraManufacture";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public ResultDO<PageUtils<CustomerOrderVO>> getOrderQuery(Map<String, Object> params) {
        return CallbackUtils.getCallback("getOrderQuery", params.toString(), () -> {
            List<CustomerOrderVO> result = new ArrayList<>();

            // 得到所有订单查询信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrder(params);
            if (customerOrderList  == null) {
                logger.error("getCustomerOrder exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == customerOrderList .size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, null));
            }

            // 获得所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds(customerOrderList.stream().map(CustomerOrderDO::getOriginalProductId).collect( Collectors.toList()));
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect(Collectors.toMap( OriginalProductDO::getId, originalProduct -> originalProduct));

            // 获得所有客户订单信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(customerOrderList.stream().map( CustomerOrderDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap( CustomerDO::getId, customer -> customer));

            // 得到所有审核者
            List<UserDO> userList = userDAO.getUserByIds(customerOrderList.stream().map( CustomerOrderDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> userMap = userList.stream().collect(Collectors.toMap( UserDO::getId, checker -> checker));

            for (CustomerOrderDO tempCustomerOrder :customerOrderList) {
                CustomerOrderVO temp = new CustomerOrderVO();

                temp.setId(tempCustomerOrder.getId());
                temp.setOrderNumber(tempCustomerOrder.getOrderNumber());
                temp.setCode(customerMap.get(tempCustomerOrder.getCustomerId()).getCode());
                temp.setName(customerMap.get(tempCustomerOrder.getCustomerId()).getName());
                temp.setItemName(originalProductMap.get(tempCustomerOrder.getOriginalProductId()).getItemName());
                temp.setSpec(originalProductMap.get(tempCustomerOrder.getOriginalProductId()).getSpec());
                temp.setUnit(originalProductMap.get(tempCustomerOrder.getOriginalProductId()).getUnit());
                temp.setProductNumber(originalProductMap.get(tempCustomerOrder.getOriginalProductId()).getProductNumber());
                temp.setOrderAmount(tempCustomerOrder.getOrderAmount());
                temp.setEveryOrderAmount(tempCustomerOrder.getEveryOrderAmount());
                temp.setEveryProductAmount(tempCustomerOrder.getEveryProductAmount());
                temp.setOrderDate(tempCustomerOrder.getOrderDate());
                temp.setDeliveryDate(tempCustomerOrder.getDeliveryDate());
                temp.setPlanArrivalDate(tempCustomerOrder.getPlanArrivalDate());
                temp.setActualArrivalDate(tempCustomerOrder.getActualArrivalDate());
                temp.setIsArrival(tempCustomerOrder.getIsArrival());
                temp.setProductAmount(tempCustomerOrder.getProductAmount());
                temp.setName(userMap.get(tempCustomerOrder.getUser()).getName());
                temp.setCheckFlag(tempCustomerOrder.getCheckFlag());
                temp.setFinalCheckFlag(tempCustomerOrder.getFinalCheckFlag());
                temp.setIsGeneraManufacture(tempCustomerOrder.getIsGeneraManufacture());
                temp.setCreateTime(tempCustomerOrder.getCreateTime());
                temp.setRemark(tempCustomerOrder.getRemark());

                result.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(customerOrderDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<CustomerOrderVO>> getOrderQueryByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getOrderQueryByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<CustomerOrderVO>> resultVO = getOrderQuery(params);
            if (resultVO == null) {
                logger.error("getCustomerOrderByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<CustomerOrderVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(IS_GENERA_MANUFACTURE)) {
                result = result.stream().filter(item -> null != item.getIsGeneraManufacture() && item.getIsGeneraManufacture().equals(params.get(IS_GENERA_MANUFACTURE).toString())).collect(Collectors.toList());
            }

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
    public ResultDO<Void> exportExcel(CustomerOrderVO[] customerOrders) {
        if (null == customerOrders || 0 == customerOrders.length) {
            return new ResultDO<>( false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null );
        }

        return CallbackUtils.exportCallback( "CustomerOrder exportExcel", Arrays.toString( customerOrders ), () -> {
            List<OrderQueryModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
            int i = 1;
            for (CustomerOrderVO temp : customerOrders) {
                OrderQueryModel item = new OrderQueryModel();
                item.setIndex( Integer.toString( i++ ) );
                item.setOrderNumber( temp.getOrderNumber() );
                item.setCode( temp.getCode() );
                item.setName( temp.getName() );
                item.setItemName( temp.getItemName() );
                item.setSpec( temp.getSpec() );
                item.setUnit( temp.getUnit() );
                item.setIsArrival( temp.getIsArrival());
                item.setOrderAmount( temp.getOrderAmount() );
                item.setProductAmount( temp.getProductAmount() );
                item.setEveryOrderAmount( temp.getEveryOrderAmount() );
                item.setEveryProductAmount( temp.getEveryProductAmount() );
                if (temp.getOrderDate() == null) {
                    item.setOrderDate( " " );
                } else {
                    item.setOrderDate( format.format( temp.getOrderDate() ) );
                }
                if (temp.getDeliveryDate() == null) {
                    item.setDeliveryDate( " " );
                } else {
                    item.setDeliveryDate( format.format( temp.getDeliveryDate() ) );
                }
                if (temp.getPlanArrivalDate() == null) {
                    item.setPlanArrivalDate( " " );
                } else {
                    item.setPlanArrivalDate( format.format( temp.getPlanArrivalDate() ) );
                }
                if (temp.getActualArrivalDate() == null) {
                    item.setActualArrivalDate( " " );
                } else {
                    item.setActualArrivalDate( format.format( temp.getActualArrivalDate() ) );
                }
                item.setIsArrival( temp.getIsArrival() );
                item.setRemark( temp.getRemark());
                item.setCheckFlag( temp.getCheckFlag() );
                item.setFinalCheckFlag( temp.getFinalCheckFlag() );

                data.add( item );
            }

            // 设置列宽
            Map<Integer, Integer> columnWidth = new HashMap<>( 21 );
            columnWidth.put( 0, (int) (4.57 * 256) );
            columnWidth.put( 1, (int) (26.14 * 256) );
            columnWidth.put( 2, (int) (10.14 * 256) );
            columnWidth.put( 3, (int) (23.43 * 256) );
            columnWidth.put( 4, (int) (23.43 * 256) );
            columnWidth.put( 5, (int) (24.86 * 256) );
            columnWidth.put( 6, (int) (9.0 * 256) );
            columnWidth.put( 7, (int) (10.86 * 256) );
            columnWidth.put( 8, (int) (10.86 * 256) );
            columnWidth.put( 9, (int) (10.86 * 256) );
            columnWidth.put( 10, (int) (10.86 * 256) );
            columnWidth.put( 11, (int) (26.14 * 256) );
            columnWidth.put( 12, (int) (26.14 * 256) );
            columnWidth.put( 13, (int) (26.14 * 256) );
            columnWidth.put( 14, (int) (26.14 * 256) );
            columnWidth.put( 15, (int) (10.86 * 256) );
            columnWidth.put( 16, (int) (10.86 * 256) );
            columnWidth.put( 17, (int) (23.43 * 256) );
            columnWidth.put( 18, (int) (23.43 * 256) );
            columnWidth.put( 19, (int) (23.43 * 256) );
            columnWidth.put( 20, (int) (26.14 * 256) );

            return MyExcel.write( ConstantHolder.FILE_SAVE_PATH + ConstantHolder.CUSTOMER_QUERY_FILE_NAME, OrderQueryModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler() );
        } );
    }

}
