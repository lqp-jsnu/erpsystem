package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.CustomerOrderModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.CustomerOrderInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
 * 客户订单
 *
 * @author smile
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("customerOrderService")
public class CustomerOrderServiceImpl implements CustomerOrderInterService {

    /** excel文件名 */
    private static final String EXCEL_FILE_NAME = "customerOrder.xlsx";

    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 客户料号*/
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 客户代号*/
    private static final String CODE = "code";
    /** 规格*/
    private static final String SPEC = "spec";
    /** 品名／磁棒／尺寸(材质)*/
    private static final String MASTER_ITEM_NAME = "masterItemName";
    /** 规格/初值/电阻线(线径)*/
    private static final String MASTER_SPEC = "masterSpec";

    private static final String EXCEL_INDEX = "序号";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private OriginalProductDAO originalproductDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrder(Map<String, Object> params) {
        return CallbackUtils.getCallback("getCustomerOrder", params.toString(), () -> {
            List<CustomerOrderVO> result = new ArrayList<>();

            // 得到所有客户订单信息
            List<CustomerOrderDO> customerOrderList = customerOrderDAO.getCustomerOrder( params );
            if (customerOrderList == null) {
                logger.error( "getCustomerOrder exception, db error, params={}", params );
                return new ResultDO<>( false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null );
            }
            // 如果没有记录直接返回
            if (0 == customerOrderList.size()) {
                return new ResultDO<>( true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>( 0, result ) );
            }

            // 获得所有成品原始信息
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProductByIds( customerOrderList.stream().map( CustomerOrderDO::getOriginalProductId ).collect( Collectors.toList() ) );
            Map<String, OriginalProductDO> originalProductMap = originalProductList.stream().collect( Collectors.toMap( OriginalProductDO::getId, originalProduct -> originalProduct ) );

            // 获得所有客户订单信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds( customerOrderList.stream().map( CustomerOrderDO::getCustomerId ).collect( Collectors.toList() ) );
            Map<String, CustomerDO> customerMap = customerList.stream().collect( Collectors.toMap( CustomerDO::getId, customer -> customer ) );

            // 获得所有材料信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds( originalProductList.stream().map( OriginalProductDO::getMaterialInfoMasterId ).collect( Collectors.toList() ) );
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect( Collectors.toMap( MaterialInfoMasterDO::getId, customer -> customer ) );

            // 得到所有审核者
            List<UserDO> userList = userDAO.getUserByIds( customerOrderList.stream().map( CustomerOrderDO::getUser ).collect( Collectors.toList() ) );
            Map<String, UserDO> userMap = userList.stream().collect( Collectors.toMap( UserDO::getId, checker -> checker ) );

            for (CustomerOrderDO tempCustomerOrder : customerOrderList) {
                CustomerOrderVO temp = new CustomerOrderVO();

                String materialInfoMasterId = originalProductMap.get(tempCustomerOrder.getOriginalProductId()).getMaterialInfoMasterId();

                temp.setId( tempCustomerOrder.getId() );
                temp.setOrderNumber( tempCustomerOrder.getOrderNumber() );
                temp.setCode( customerMap.get( tempCustomerOrder.getCustomerId() ).getCode() );
                temp.setName( customerMap.get( tempCustomerOrder.getCustomerId() ).getName() );
                temp.setItemName( originalProductMap.get( tempCustomerOrder.getOriginalProductId() ).getItemName() );
                temp.setSpec( originalProductMap.get( tempCustomerOrder.getOriginalProductId() ).getSpec() );
                temp.setMaterialItemName( materialInfoMasterMap.get( materialInfoMasterId ).getItemName() );
                temp.setMaterialSpec( materialInfoMasterMap.get( materialInfoMasterId ).getSpec() );
                temp.setUnit( originalProductMap.get( tempCustomerOrder.getOriginalProductId() ).getUnit() );
                temp.setProductNumber( originalProductMap.get( tempCustomerOrder.getOriginalProductId() ).getProductNumber() );
                temp.setOrderAmount( tempCustomerOrder.getOrderAmount() );
                temp.setOrderDate( tempCustomerOrder.getOrderDate() );
                temp.setDeliveryDate( tempCustomerOrder.getDeliveryDate() );
                temp.setPlanArrivalDate( tempCustomerOrder.getPlanArrivalDate() );
                temp.setActualArrivalDate( tempCustomerOrder.getActualArrivalDate() );
                if (null != tempCustomerOrder.getUser()) {
                    temp.setName( userMap.get( tempCustomerOrder.getUser() ).getName() );
                }
                temp.setCheckFlag( tempCustomerOrder.getCheckFlag() );
                temp.setFinalCheckFlag( tempCustomerOrder.getFinalCheckFlag() );
                temp.setCreateTime( tempCustomerOrder.getCreateTime() );
                temp.setRemark( tempCustomerOrder.getRemark() );
                temp.setIsArrival(tempCustomerOrder.getIsArrival());

                result.add( temp );
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(customerOrderDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<PageUtils<CustomerOrderVO>> getCustomerOrderByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getCustomerOrderByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<CustomerOrderVO>> resultVO = getCustomerOrder(params);
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
            if (params.containsKey(ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(SPEC)) {
                result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(MASTER_ITEM_NAME)) {
                result = result.stream().filter(item -> null != item.getMaterialItemName() && item.getMaterialItemName().toLowerCase().contains(params.get(MASTER_ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(MASTER_SPEC)) {
                result = result.stream().filter(item -> null != item.getMaterialSpec() && item.getMaterialSpec().toLowerCase().contains(params.get(MASTER_SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(PRODUCT_NUMBER)) {
                result = result.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> updateCheckerById(CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId()) || StringUtils.isBlank(customerOrderDO.getChecker()) || StringUtils.isBlank(customerOrderDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("CustomerOrder updateCheckerById", customerOrderDO.toString(), () -> customerOrderDAO.updateCheckerById(customerOrderDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("CustomerOrder", id, () -> customerOrderDAO.delete(id));
    }

    @Override
    public ResultDO<Void> insert(CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getOriginalProductId() ) || StringUtils.isBlank(customerOrderDO.getOrderAmount())
                || StringUtils.isBlank(customerOrderDO.getCustomerId()) || StringUtils.isBlank(customerOrderDO.getOrderNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("CustomerOrder", customerOrderDO.toString(), () -> customerOrderDAO.insert(customerOrderDO));
    }

    @Override
    public ResultDO<Void> updateCustomerOrder(CustomerOrderDO customerOrderDO) {
        if (StringUtils.isBlank(customerOrderDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateCustomerOrder", customerOrderDO.toString(), () -> customerOrderDAO.updateCustomerOrder(customerOrderDO));
    }

    @Override
    public ResultDO<Void> exportExcel(CustomerOrderVO[] customerOrderVO) {
        if (null == customerOrderVO || 0 == customerOrderVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("CustomerOrder exportExcel", Arrays.toString(customerOrderVO), () -> {
            List<CustomerOrderModel> data = new ArrayList<>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (CustomerOrderVO temp : customerOrderVO) {
                CustomerOrderModel item = new CustomerOrderModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setUnit(temp.getUnit());
                item.setIsArrival(temp.getIsArrival());
                item.setOrderAmount(temp.getOrderAmount());
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
                item.setName(temp.getName());
                item.setRemark(temp.getRemark());
                if (temp.getPlanArrivalDate() == null) {
                    item.setPlanArrivalDate(" ");
                } else {
                    item.setPlanArrivalDate(format.format(temp.getPlanArrivalDate()));
                }
                if (temp.getActualArrivalDate()== null) {
                    item.setActualArrivalDate(" ");
                } else {
                    item.setActualArrivalDate(format.format(temp.getActualArrivalDate()));
                }
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(14);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(24.86*256));
            columnWidth.put(2,(int)(10.14*256));
            columnWidth.put(3,(int)(26.14*256));
            columnWidth.put(4,(int)(10.14*256));
            columnWidth.put(5,(int)(23.43*256));
            columnWidth.put(6,(int)(10.14*256));
            columnWidth.put(7,(int)(9.0*256));
            columnWidth.put(8,(int)(26.14*256));
            columnWidth.put(9,(int)(26.14*256));
            columnWidth.put(10,(int)(26.14*256));
            columnWidth.put(11,(int)(26.14*256));
            columnWidth.put(12,(int)(10.14*256));
            columnWidth.put(13,(int)(10.14*256));


            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.CUSTOMER_ORDER_FILE_NAME, CustomerOrderModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<Void> importExcel(CustomerOrderDO customerOrderUserId) {
        try {
            ResultDO<List<Object>> resultDO = MyExcel.read(ConstantHolder.FILE_SAVE_PATH + EXCEL_FILE_NAME, CustomerOrderModel.class);
            List<CustomerDO> customerList = customerDAO.getCustomer(null);
            List<OriginalProductDO> originalProductList = originalproductDAO.getOriginalProduct(null);

            if (resultDO.isSuccess()) {
                List<CustomerOrderDO> customerOrderList = new ArrayList<>();

                List<CustomerOrderModel> materialPurchaseImportModelList = resultDO.getModule().stream().map(item -> (CustomerOrderModel)(item)).collect(Collectors.toList());
                materialPurchaseImportModelList = materialPurchaseImportModelList.stream().filter(item -> null != item.getIndex() && !EXCEL_INDEX.equals(item.getIndex())).collect(Collectors.toList());

                for (CustomerOrderModel temp : materialPurchaseImportModelList) {
                    CustomerOrderDO customerOrderDO = new CustomerOrderDO();

                    if (StringUtils.isNumeric(temp.getIndex()) && !StringUtils.isBlank(temp.getCode()) && !StringUtils.isBlank(temp.getName())
                            && !StringUtils.isBlank(temp.getItemName()) && !StringUtils.isBlank(temp.getSpec())&& !StringUtils.isBlank(temp.getOrderNumber())) {

                        customerOrderDO.setOrderNumber(temp.getOrderNumber());
                        CustomerDO customerDO = new CustomerDO();
                        OriginalProductDO originalProductDO = new OriginalProductDO();
                        customerDO.setCode( temp.getCode() );
                        customerDO.setName( temp.getName());
                        originalProductDO.setItemName( temp.getItemName() );
                        originalProductDO.setSpec(temp.getSpec());
                        originalProductDO.setUnit(temp.getUnit());

                        List<CustomerDO> customer = customerList.stream().filter(item -> item.getCode().equals(temp.getCode())).collect(Collectors.toList());
                        if (0 != customer.size() ) {
                            customerOrderDO.setCustomerId(customer.get(0).getId());
                            originalProductDO.setCustomerId(customer.get(0).getId());
                        } else {
                            customerDAO.insert(customerDO);
                            customerOrderDO.setCustomerId(customerDO.getId());
                        }

                        List<OriginalProductDO> originalProduct = originalProductList.stream().filter(item -> {
                            if (null != temp.getUnit()) {
                                return item.getSpec().equals(temp.getSpec()) && item.getItemName().equals(temp.getItemName()) && temp.getUnit().equals(item.getUnit());
                            } else {
                                return item.getSpec().equals(temp.getSpec()) && item.getItemName().equals(temp.getItemName());
                            }
                        }).collect(Collectors.toList());
                        if ( 0 != originalProduct.size()) {
                            customerOrderDO.setOriginalProductId( originalProduct.get(0).getId());
                        } else {
                            originalproductDAO.insert(originalProductDO);
                            customerOrderDO.setOriginalProductId(originalProductDO.getId());
                        }

                        customerOrderDO.setOrderAmount(temp.getOrderAmount());
                        if (!StringUtils.isBlank(temp.getOrderDate())) {
                            customerOrderDO.setOrderDate(HSSFDateUtil.getJavaDate(Double.parseDouble(temp.getOrderDate())));
                        }

                        if (!StringUtils.isBlank(temp.getDeliveryDate())) {
                            customerOrderDO.setDeliveryDate(HSSFDateUtil.getJavaDate(Double.parseDouble(temp.getDeliveryDate())));
                        }

                        if (!StringUtils.isBlank(temp.getPlanArrivalDate())) {
                            customerOrderDO.setPlanArrivalDate(HSSFDateUtil.getJavaDate(Double.parseDouble(temp.getPlanArrivalDate())));
                        }

                        if (!StringUtils.isBlank(temp.getActualArrivalDate())) {
                            customerOrderDO.setActualArrivalDate(HSSFDateUtil.getJavaDate(Double.parseDouble(temp.getActualArrivalDate())));
                        }

                        customerOrderDO.setIsArrival(temp.getIsArrival());
                        customerOrderDO.setRemark(temp.getRemark());
                        customerOrderDO.setUser(customerOrderUserId.getUser());

                        customerOrderList.add(customerOrderDO);
                    }
                }

                customerOrderDAO.insertAll(customerOrderList);
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
            } else {
                return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
            }
        } catch (Exception e) {
            logger.error("CustomerOrder importExcel exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

}
