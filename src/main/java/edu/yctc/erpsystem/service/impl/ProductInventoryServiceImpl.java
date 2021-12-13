package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.*;
import edu.yctc.erpsystem.entity.*;

import edu.yctc.erpsystem.excel.ProductInventorySummaryModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.ProductInventoryInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainProductExpiryDateVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.ProductInventorySummaryVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author qiang
 *
 * 成品库存接口实现
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Resource
    private ProductNumberSetDAO productNumberSetDAO;

    @Resource
    private ProductExpiryDateSetDAO productExpiryDateSetDAO;

    @Override
    public ResultDO<PageUtils<ProductInventorySummaryVO>> getProductInventorySummary(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductInventorySummary", params.toString(), () -> new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productInventoryDAO.summaryCount(params), productInventoryDAO.getProductInventorySummary(params))));
    }

    @Override
    public ResultDO<Void> exportExcel(ProductInventorySummaryVO[] prodInventorySummaryVO) {
        if (null == prodInventorySummaryVO || 0 == prodInventorySummaryVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("ReceiptSummary exportExcel", Arrays.toString(prodInventorySummaryVO), () -> {
            List<ProductInventorySummaryModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int i = 1;
            for (ProductInventorySummaryVO temp : prodInventorySummaryVO) {
                ProductInventorySummaryModel item = new ProductInventorySummaryModel();

                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setProductNumber(temp.getProductNumber());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setUnit(temp.getUnit());
                item.setStorageAmount(temp.getStorageAmount());
                item.setStorageDate(format.format(temp.getStorageDate()));

                data.add(item);
            }
            Map<Integer,Integer> columnWidth = new HashMap<>(6);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(24.86*256));
            columnWidth.put(5,(int)(9.0*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.PRODUCT_INVENTORY_SUMMARY_FILE_NAME, ProductInventorySummaryModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<PageUtils<MainProductNumberVO>> getMainProductNumber(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMainProductNumber", params.toString(), () -> {
            List<MainProductNumberVO> productInventoryRepertoryList = productInventoryDAO.getMainProductNumber(params);

            if (productInventoryRepertoryList == null) {
                logger.error("getMainProductNumber exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (productInventoryRepertoryList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, productInventoryRepertoryList));
            }

            // 得到所有成品数量设置信息
            Map<String, Object> productNumberParams = new HashMap<>(0);
            List<ProductNumberSetDO> productNumberSetList = productNumberSetDAO.getProductNumberSet(productNumberParams);

            DecimalFormat df = new DecimalFormat("0.0");
            for (MainProductNumberVO temp : productInventoryRepertoryList) {
                temp.setStorageAmount(df.format(Float.parseFloat(temp.getStorageAmount())));
            }

            if (0 == productInventoryRepertoryList.size()) {
                for(MainProductNumberVO tempMainProInventory : productInventoryRepertoryList) {
                    tempMainProInventory.setResult("未设置预警值");
                }
            } else {
                for (MainProductNumberVO tempMainProInventory : productInventoryRepertoryList) {
                    for (ProductNumberSetDO tempProductNumberSet : productNumberSetList) {
                        if (tempMainProInventory.getOriginalProductId().equals(tempProductNumberSet.getOriginalProductId())) {
                            if (Float.parseFloat(tempMainProInventory.getStorageAmount()) < Float.parseFloat(tempProductNumberSet.getMinNumber())) {
                                tempMainProInventory.setResult("低于预警值");
                            } else if (Float.parseFloat(tempMainProInventory.getStorageAmount()) > Float.parseFloat(tempProductNumberSet.getMaxNumber())) {
                                tempMainProInventory.setResult("高于预警值");
                            } else {
                                tempMainProInventory.setResult("正常");
                            }
                            break;
                        } else {
                            tempMainProInventory.setResult("未设置预警值");
                        }
                    }
                }
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,new PageUtils<>(productInventoryDAO.mainProductNumberCount(params), productInventoryRepertoryList));
        });
    }


    @Override
    public ResultDO<PageUtils<MainProductExpiryDateVO>> getMainProductExpiryDate(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMainProductExpiryDate", params.toString(), () -> {
            List<MainProductExpiryDateVO> prodInventoryRepertoryList = productInventoryDAO.getProductInventory(params);

            if (prodInventoryRepertoryList == null) {
                logger.error("getMainProductExpiryDate exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (prodInventoryRepertoryList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, prodInventoryRepertoryList));
            }

            // 用来获得成品保质期设置所有信息参数
            Map<String, Object> proExpiryDateParams = new HashMap<>(0);
            // 得到所有成品保质期设置信息
            List<ProductExpiryDateSetDO> productExpiryDateSetList = productExpiryDateSetDAO.getExpiryDateSet(proExpiryDateParams);


            Date todayDate = new Date();
            Integer leaveDate;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.format(todayDate);
            if (0 == productExpiryDateSetList.size()) {
                for(MainProductExpiryDateVO tempMainProInventoryRepertory : prodInventoryRepertoryList) {
                    tempMainProInventoryRepertory.setResult("未设置预警值");
                }
            } else {
                for (MainProductExpiryDateVO tempMainProInventoryRepertory : prodInventoryRepertoryList) {
                    for (ProductExpiryDateSetDO tempProductExpiryDateSet : productExpiryDateSetList) {
                        if (tempMainProInventoryRepertory.getOriginalProductId().equals(tempProductExpiryDateSet.getOriginalProductId())) {
                            long days = (todayDate.getTime() - tempMainProInventoryRepertory.getStorageDate().getTime() + 1000000) / (60*60*24*1000);
                            if (days <= tempProductExpiryDateSet.getExpiryDate()) {
                                leaveDate = tempProductExpiryDateSet.getExpiryDate() - (int)days;
                                tempMainProInventoryRepertory.setDate("还有"+leaveDate+"天过期");
                                tempMainProInventoryRepertory.setResult("正常");
                                tempMainProInventoryRepertory.setLeaveDate(leaveDate);
                            } else {
                                leaveDate = (int)days - tempProductExpiryDateSet.getExpiryDate();
                                tempMainProInventoryRepertory.setDate("已过期"+leaveDate+"天");
                                tempMainProInventoryRepertory.setResult("产品已过期");
                                tempMainProInventoryRepertory.setLeaveDate(leaveDate);
                            }
                            break;
                        } else {
                            tempMainProInventoryRepertory.setResult("未设置预警值");
                        }
                    }
                }
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productInventoryDAO.count(params), prodInventoryRepertoryList));
        });
    }

    @Override
    public ResultDO<Void> insert(ProductInventoryDO productInventoryDO) {
        if (StringUtils.isBlank(productInventoryDO.getProductStorageId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("ProductInventory", productInventoryDO.toString(), () -> productInventoryDAO.insert(productInventoryDO));
    }

}
