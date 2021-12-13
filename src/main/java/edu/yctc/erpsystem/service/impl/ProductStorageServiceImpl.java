package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.ProductStorageModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.ProductStorageInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductStorageVO;
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
 * 成品入库信息逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("productStorageService")
public class ProductStorageServiceImpl implements ProductStorageInterService {

    //过滤参数
    /** 订单号 */
    private static final String ORDER_NUMBER = "orderNumber";
    /** 客户代码 */
    private static final String CODE = "code";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";
    /** 工作传票号 */
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";
    /** 客户料号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 存放仓库 */
    private static final String HOUSE_NAME = "houseName";

    private static final String CHECK_FLAG = "已通过";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ProductStorageDAO productStorageDAO;

    @Resource
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private CustomerOrderDAO customerOrderDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private WarehouseDAO warehouseDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public ResultDO<PageUtils<ProductStorageVO>> getProductStorage(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductStorage", params.toString(), () -> {
            List<ProductStorageVO> result = new ArrayList<>();

            // 得到所有成品入库
            List<ProductStorageDO>  productStorageList = productStorageDAO.getProductStorage(params);
            productStorageList = productStorageList.stream().filter(item -> !CHECK_FLAG.equals(item.getCheckFlag())).collect(Collectors.toList());

            if (productStorageList == null) {
                logger.error("getProductStorage exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == productStorageList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有制造流程单副表
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(productStorageList.stream().map(ProductStorageDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
            Map<String, ManufactureProcessSlaveDO> manufactureProcessSlaveMap = manufactureProcessSlaveList.stream().collect(Collectors.toMap(ManufactureProcessSlaveDO::getId, manufactureProcessSlave -> manufactureProcessSlave));

            // 得到所有制造流程单主表
            List<ManufactureProcessMasterDO> manufactureProcessMasterList = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(manufactureProcessSlaveList.stream().map(ManufactureProcessSlaveDO::getManufactureProcessMasterId).collect(Collectors.toList()));
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

            // 得到仓库信息
            List<WarehouseDO>  warehouseList= warehouseDAO.getWarehouseByIds(productStorageList.stream().map(ProductStorageDO::getWareHouseId).collect(Collectors.toList()));
            Map<String, WarehouseDO> warehouseMap =warehouseList.stream().collect(Collectors.toMap(WarehouseDO::getId, warehouse->warehouse));

            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(productStorageList.stream().map(ProductStorageDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            for (ProductStorageDO tempProductStorage : productStorageList) {
                ProductStorageVO temp = new ProductStorageVO();

                String manufactureProcessMasterId = manufactureProcessSlaveMap.get(tempProductStorage.getManufactureProcessSlaveId()).getManufactureProcessMasterId();
                String customerOrderId = manufactureProcessMasterMap.get(manufactureProcessMasterId).getCustomerOrderId();
                String customerId = customerOrderMap.get(customerOrderId).getCustomerId();
                String originalProductId = customerOrderMap.get(customerOrderId).getOriginalProductId();

                temp.setId(tempProductStorage.getId());
                temp.setManufactureProcessSlaveId(tempProductStorage.getManufactureProcessSlaveId());
                temp.setEnter(enterMap.get(tempProductStorage.getUser()).getName());
                temp.setHouseName(warehouseMap.get(tempProductStorage.getWareHouseId()).getName());
                temp.setSpec(originalProductMap.get(originalProductId).getSpec());
                temp.setItemName(originalProductMap.get(originalProductId).getItemName());
                temp.setProductNumber(originalProductMap.get(originalProductId).getProductNumber());
                temp.setOrderNumber(customerOrderMap.get(customerOrderId).getOrderNumber());
                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(tempProductStorage.getManufactureProcessSlaveId()).getJobTicketNumber());
                temp.setCode(customerMap.get(customerId).getCode());
                temp.setCheckFlag(tempProductStorage.getCheckFlag());
                temp.setStorageAmount(tempProductStorage.getStorageAmount());
                temp.setStorageDate(tempProductStorage.getStorageDate());
                temp.setCreateTime(tempProductStorage.getCreateTime());
                temp.setIsZeroProductStorage(tempProductStorage.getIsZeroProductStorage());
                temp.setProductQuantity(tempProductStorage.getProductQuantity());
                temp.setWareHouseId(tempProductStorage.getWareHouseId());
                temp.setRemark(tempProductStorage.getRemark());


                result.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productStorageDAO.count(params), result));
        });

    }

    @Override
    public ResultDO<PageUtils<ProductStorageVO>> getProductStorageByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProdStorageByConditions", params.toString(), () -> {
            //获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ProductStorageVO>> resultVO = getProductStorage(params);

            if (resultVO == null) {
                logger.error("getProductStorageByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ProductStorageVO> result = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(ORDER_NUMBER)) {
                result = result.stream().filter(item -> null != item.getOrderNumber() && item.getOrderNumber().toLowerCase().contains(params.get(ORDER_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(CODE)) {
                result = result.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(JOB_TICKET_NUMBER)) {
                result = result.stream().filter(item -> null != item.getJobTicketNumber() && item.getJobTicketNumber().toLowerCase().contains(params.get(JOB_TICKET_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
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

            if (params.containsKey(HOUSE_NAME)) {
                result = result.stream().filter(item -> null != item.getHouseName() && item.getHouseName().toLowerCase().contains(params.get(HOUSE_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));

        });
    }

    @Override
    public ResultDO<Void> insert(ProductStorageDO productStorageDO) {
        if (StringUtils.isBlank(productStorageDO.getManufactureProcessSlaveId()) || StringUtils.isBlank(productStorageDO.getProductQuantity())
                || StringUtils.isBlank(productStorageDO.getStorageAmount()) || StringUtils.isBlank(productStorageDO.getWareHouseId())
                || StringUtils.isBlank(productStorageDO.getStorageDate().toString()) || StringUtils.isBlank(productStorageDO.getIsZeroProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("ProductStorage", productStorageDO.toString(), () -> productStorageDAO.insert(productStorageDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("ProductStorage", id, () -> productStorageDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateProductStorage(ProductStorageDO productStorageDO) {
        if (StringUtils.isBlank(productStorageDO.getId()) || StringUtils.isBlank(productStorageDO.getProductQuantity())
                || StringUtils.isBlank(productStorageDO.getStorageAmount()) || StringUtils.isBlank(productStorageDO.getWareHouseId())
                || StringUtils.isBlank(productStorageDO.getIsZeroProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateProductStorage", productStorageDO.toString(), () -> productStorageDAO.updateProductStorage(productStorageDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(ProductStorageDO productStorageDO) {
        if (StringUtils.isBlank(productStorageDO.getId()) || StringUtils.isBlank(productStorageDO.getChecker()) || StringUtils.isBlank(productStorageDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateCheckerById", productStorageDO.toString(), () -> productStorageDAO.updateCheckerById(productStorageDO));

    }

    @Override
    public ResultDO<Void> exportExcel(ProductStorageVO[] productStorageVO) {
        if (null == productStorageVO || 0 == productStorageVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("ProductStorage exportExcel", Arrays.toString(productStorageVO), () -> {
            List<ProductStorageModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int i = 1;
            for (ProductStorageVO temp : productStorageVO) {
                ProductStorageModel item=new ProductStorageModel();
                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setJobTicketNumber(temp.getJobTicketNumber());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setProductQuantity(temp.getProductQuantity());
                item.setStorageAmount(temp.getStorageAmount());
                item.setHouseName(temp.getHouseName());
                item.setIsZeroProductStorage(temp.getIsZeroProductStorage());
                item.setEnter(temp.getEnter());
                item.setCheckFlag(temp.getCheckFlag());
                item.setStorageDate(format.format(temp.getStorageDate()));
                item.setCreateTime(format.format(temp.getCreateTime()));
                data.add(item);
            }

            Map<Integer,Integer> columnWidth = new HashMap<>(15);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(9.0*256));
            columnWidth.put(6,(int)(10.14*256));
            columnWidth.put(7,(int)(8.71*256));
            columnWidth.put(8,(int)(9.0*256));
            columnWidth.put(9,(int)(10.14*256));
            columnWidth.put(10,(int)(8.71*256));
            columnWidth.put(11,(int)(23.43*256));
            columnWidth.put(12,(int)(23.43*256));
            columnWidth.put(13,(int)(10.0*256));
            columnWidth.put(14,(int)(10.0*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.PRODUCT_STORAGE_FILE_NAME, ProductStorageModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
