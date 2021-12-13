package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.ProductInventoryRepertoryModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.ProductInventoryRepertoryInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;
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
 * 成品库存逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("productInventoryRepertoryService")
public class ProductInventoryRepertoryServiceImpl implements ProductInventoryRepertoryInterService {

    //过滤参数
    /*** 订单号*/
    private static final String ORDER_NUMBER = "orderNumber";
    /*** 客户代码*/
    private static final String CODE = "code";
    /*** 品名*/
    private static final String ITEM_NAME = "itemName";
    /*** 规格*/
    private static final String SPEC = "spec";
    /*** 工作传票号*/
    private static final String JOB_TICKET_NUMBER = "jobTicketNumber";
    /**客户料号*/
    private static final String PRODUCT_NUMBER = "productNumber";
    /*** 存放仓库*/
    private static final String HOUSE_NAME = "houseName";
    /*** 是否调拨*/
    private static final String IS_ALLOCATION = "isAllocation";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ProductExportDAO productExportDAO;

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Resource
    private ProductStorageDAO productStorageDAO;

    @Resource
    private ProductAllocationDAO productAllocationDAO;

    @Override
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getProductInventoryRepertory(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductInventoryRepertory", params.toString(), () -> new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productInventoryDAO.countRepertory(params), productInventoryDAO.getProductInventoryRepertory(params))));
    }

    @Override
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getProductInventoryRepertoryByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductInventoryRepertoryByConditions", params.toString(), () ->  {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ProductInventoryRepertoryVO>> resultVO = getProductInventoryRepertory(params);

            if (resultVO == null) {
                logger.error("getProductInventoryRepertoryByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ProductInventoryRepertoryVO> result = resultVO.getModule().getRows();

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

            if (params.containsKey(IS_ALLOCATION)) {
                result = result.stream().filter(item -> null != item.getIsAllocation() && item.getIsAllocation().equals(params.get(IS_ALLOCATION).toString())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> updateStorageAmount(ProductInventoryRepertoryVO productInventoryRepertoryVO) {
        if(StringUtils.isBlank(productInventoryRepertoryVO.getProductStorageId()) || StringUtils.isBlank(productInventoryRepertoryVO.getStorageAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateStorageAmount", productInventoryRepertoryVO.toString(), () -> {
            ProductStorageDO productStorageDO = new ProductStorageDO();

            productStorageDO.setId(productInventoryRepertoryVO.getProductStorageId());
            productStorageDO.setStorageAmount(productInventoryRepertoryVO.getStorageAmount());

            productStorageDAO.updateStorageAmount(productStorageDO);
        });
    }

    @Override
    public ResultDO<Void> exportExcel(ProductInventoryRepertoryVO[] productInventoryRepertoryVO) {
        if (null == productInventoryRepertoryVO || 0 == productInventoryRepertoryVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("productInventoryRepertory exportExcel", Arrays.toString(productInventoryRepertoryVO), () -> {
            List<ProductInventoryRepertoryModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int i = 1;
            for (ProductInventoryRepertoryVO temp : productInventoryRepertoryVO) {
                ProductInventoryRepertoryModel item = new ProductInventoryRepertoryModel();

                item.setIndex(Integer.toString(i++));
                item.setOrderNumber(temp.getOrderNumber());
                item.setCode(temp.getCode());
                item.setJobTicketNumber(temp.getJobTicketNumber());
                item.setProductNumber(temp.getProductNumber());
                item.setSpec(temp.getSpec());
                item.setUnit(temp.getUnit());
                item.setStorageAmount(temp.getStorageAmount());
                item.setUnitPrice(temp.getUnitPrice());
                item.setMoney(temp.getMoney());
                item.setHouseName(temp.getHouseName());
                item.setIsAllocation(temp.getIsAllocation());
                item.setStorageDate(format.format(temp.getStorageDate()));

                data.add(item);
            }
            Map<Integer, Integer> columnWidth = new HashMap<>(12);
            columnWidth.put(0, (int) (4.57 * 256));
            columnWidth.put(1, (int) (10.86 * 256));
            columnWidth.put(2, (int) (26.14 * 256));
            columnWidth.put(3, (int) (23.43 * 256));
            columnWidth.put(4, (int) (24.86 * 256));
            columnWidth.put(5, (int) (9.0 * 256));
            columnWidth.put(6, (int) (10.14 * 256));
            columnWidth.put(7, (int) (8.71 * 256));
            columnWidth.put(8, (int) (9.0 * 256));
            columnWidth.put(9, (int) (10.14 * 256));
            columnWidth.put(10, (int) (8.71 * 256));
            columnWidth.put(11, (int) (23.43 * 256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.PRODUCT_INVENTORY_REPERTORY_FILE_NAME, ProductInventoryRepertoryModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });

    }

    @Override
    public ResultDO<Void> productInventorySale(ProductExportDO productExportDO) {
        if (StringUtils.isBlank(productExportDO.getProductInventoryId()) || StringUtils.isBlank(productExportDO.getOrderNumber())
                || StringUtils.isBlank(productExportDO.getNumber()) || StringUtils.isBlank(productExportDO.getAmount())
                || StringUtils.isBlank(productExportDO.getDate().toString()) || StringUtils.isBlank(productExportDO.getCustomerId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("productInventorySale", productExportDO.toString(), () -> productExportDAO.insert(productExportDO));
    }

    @Override
    public ResultDO<Void> productAllocation(ProductAllocationDO productAllocationDO) {
        if (StringUtils.isBlank(productAllocationDO.getProductInventoryId()) || StringUtils.isBlank(productAllocationDO.getAmount())
                || StringUtils.isBlank(productAllocationDO.getWarehouseId()) || StringUtils.isBlank(productAllocationDO.getAllocationDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("productAllocation", productAllocationDO.toString(), () -> productAllocationDAO.insert(productAllocationDO));
    }

}