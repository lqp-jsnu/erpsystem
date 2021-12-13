package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.ZeroProdStorageModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.ZeroProductStorageInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ZeroProductStorageVO;
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
 * 零品入库逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("zeroProdStorageService")
public class ZeroProductStorageServiceImpl implements ZeroProductStorageInterService {

    // 过滤参数
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

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ZeroProdStorageDAO zeroProdStorageDAO;

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
    private WarehouseDAO wareHouseDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public ResultDO<PageUtils<ZeroProductStorageVO>> getZeroProductStorage(Map<String, Object> params) {
        return CallbackUtils.getCallback("getZeroProductStorage", params.toString(), () -> {
            List<ZeroProductStorageVO> result = new ArrayList<>();

            // 得到所有零品入库信息
            List<ZeroProductStorageDO> zeroProdStorageList = zeroProdStorageDAO.getZeroProdStorage(params);
            if (zeroProdStorageList == null) {
                logger.error("getZeroProductStorage exception, db error params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == zeroProdStorageList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, result));
            }

            // 得到所有制造流程单副表
            List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(zeroProdStorageList.stream().map(ZeroProductStorageDO::getManufactureProcessSlaveId).collect(Collectors.toList()));
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
            List<WarehouseDO> warehouseList= wareHouseDAO.getWarehouseByIds(zeroProdStorageList.stream().map(ZeroProductStorageDO::getWarehouseId).collect(Collectors.toList()));
            Map<String,WarehouseDO> warehouseMap = warehouseList.stream().collect(Collectors.toMap(WarehouseDO::getId, warehouse -> warehouse));

            // 得到所有录入者
            List<UserDO> enterList = userDAO.getUserByIds(zeroProdStorageList.stream().map(ZeroProductStorageDO::getUser).collect(Collectors.toList()));
            Map<String, UserDO> enterMap = enterList.stream().collect(Collectors.toMap(UserDO::getId, enter -> enter));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(zeroProdStorageList.stream().map(ZeroProductStorageDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            for (ZeroProductStorageDO tempZeroProdStorage : zeroProdStorageList) {
                ZeroProductStorageVO temp = new ZeroProductStorageVO();

                temp.setId(tempZeroProdStorage.getId());
                temp.setEnter(enterMap.get(tempZeroProdStorage.getUser()).getName());
                if (null != tempZeroProdStorage.getChecker()) {
                    temp.setChecker(checkerMap.get(tempZeroProdStorage.getChecker()).getName());
                }
                temp.setHouseName(warehouseMap.get(tempZeroProdStorage.getWarehouseId()).getName());

                String tempManufactureProcessMasterId = manufactureProcessSlaveMap.get(tempZeroProdStorage.getManufactureProcessSlaveId()).getManufactureProcessMasterId();
                String tempCustomerOrderId = manufactureProcessMasterMap.get(tempManufactureProcessMasterId).getCustomerOrderId();
                String tempCustomerId = customerOrderMap.get(tempCustomerOrderId).getCustomerId();
                String tempOriginalProductId = customerOrderMap.get(tempCustomerOrderId).getOriginalProductId();

                temp.setProductNumber(originalProductMap.get(tempOriginalProductId).getProductNumber());
                temp.setItemName(originalProductMap.get(tempOriginalProductId).getItemName());
                temp.setSpec(originalProductMap.get(tempOriginalProductId).getSpec());
                temp.setOrderNumber(customerOrderMap.get(tempCustomerOrderId).getOrderNumber());
                temp.setCode(customerMap.get(tempCustomerId).getCode());
                temp.setJobTicketNumber(manufactureProcessSlaveMap.get(tempZeroProdStorage.getManufactureProcessSlaveId()).getJobTicketNumber());
                temp.setIsProductStorage(tempZeroProdStorage.getIsProductStorage());
                temp.setCheckFlag(tempZeroProdStorage.getCheckFlag());
                temp.setCreateTime(tempZeroProdStorage.getCreateTime());
                temp.setProductQuantity(tempZeroProdStorage.getProductQuantity());
                temp.setZeroStorageAmount(tempZeroProdStorage.getStorageAmount());
                temp.setStorageDate(tempZeroProdStorage.getStorageDate());
                temp.setManufactureProcessSlaveId(tempZeroProdStorage.getManufactureProcessSlaveId());
                temp.setZeroWareHouseId(tempZeroProdStorage.getWarehouseId());
                temp.setRemark(tempZeroProdStorage.getRemark());

                result.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(zeroProdStorageDAO.count(params), result));
        });

    }

    @Override
    public ResultDO<PageUtils<ZeroProductStorageVO>> getZeroProductStorageByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getZeroProductStorageByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");

            ResultDO<PageUtils<ZeroProductStorageVO>> resultVO = getZeroProductStorage(params);

            if (resultVO == null) {
                logger.error("getZeroProductStorageByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ZeroProductStorageVO> result = resultVO.getModule().getRows();

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
    public ResultDO<Void> insert(ZeroProductStorageDO zeroProdStorageDO) {
        if (StringUtils.isBlank(zeroProdStorageDO.getManufactureProcessSlaveId()) || StringUtils.isBlank(zeroProdStorageDO.getProductQuantity())
                || StringUtils.isBlank(zeroProdStorageDO.getStorageAmount()) || StringUtils.isBlank(zeroProdStorageDO.getWarehouseId())
                || StringUtils.isBlank(zeroProdStorageDO.getStorageDate().toString()) || StringUtils.isBlank(zeroProdStorageDO.getIsProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("ZeroProductStorage", zeroProdStorageDO.toString(), () -> zeroProdStorageDAO.insert(zeroProdStorageDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("ZeroProductStorage", id, () -> zeroProdStorageDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateZeroProductStorage(ZeroProductStorageDO zeroProdStorageDO) {
        if (StringUtils.isBlank(zeroProdStorageDO.getId()) || StringUtils.isBlank(zeroProdStorageDO.getProductQuantity())
                || StringUtils.isBlank(zeroProdStorageDO.getStorageAmount()) || StringUtils.isBlank(zeroProdStorageDO.getWarehouseId())
                || StringUtils.isBlank(zeroProdStorageDO.getStorageDate().toString()) || StringUtils.isBlank(zeroProdStorageDO.getIsProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateZeroProductStorage", zeroProdStorageDO.toString(), () -> zeroProdStorageDAO.updateZeroProdStorage(zeroProdStorageDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(ZeroProductStorageDO zeroProdStorageDO) {
        if (StringUtils.isBlank(zeroProdStorageDO.getId()) || StringUtils.isBlank(zeroProdStorageDO.getChecker()) || StringUtils.isBlank(zeroProdStorageDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("ZeroProductStorage updateCheckerById", zeroProdStorageDO.toString(), () -> zeroProdStorageDAO.updateCheckerById(zeroProdStorageDO));
    }

    @Override
    public ResultDO<Void> exportExcel(ZeroProductStorageVO[] zeroProdStorageVO) {
        if (null == zeroProdStorageVO || 0 == zeroProdStorageVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("ZeroProductStorage exportExcel", Arrays.toString(zeroProdStorageVO), () -> {
            List<ZeroProdStorageModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int i = 1;
            for (ZeroProductStorageVO temp : zeroProdStorageVO) {
                ZeroProdStorageModel item = new ZeroProdStorageModel();

                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setJobTicketNumber(temp.getJobTicketNumber());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setProductQuantity(temp.getProductQuantity());
                item.setZeroStorageAmount(temp.getZeroStorageAmount());
                item.setHouseName(temp.getHouseName());
                item.setIsProductStorage(temp.getIsProductStorage());
                item.setEnter(temp.getEnter());
                item.setChecker(temp.getChecker());
                item.setCheckFlag(temp.getCheckFlag());
                item.setStorageDate(format.format(temp.getStorageDate()));
                item.setCreateTime(format.format(temp.getCreateTime()));

                data.add(item);
            }
            Map<Integer,Integer> columnWidth = new HashMap<>(17);
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
            columnWidth.put(14,(int)(8.71*256));
            columnWidth.put(15,(int)(23.43*256));
            columnWidth.put(16,(int)(23.43*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.ZERO_PROD_STORAGE_FILE_NAME, ZeroProdStorageModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
