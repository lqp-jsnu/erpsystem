package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.ProductInventoryDAO;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.ProductInventoryModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.FinishedGoodsInventoryInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;
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
 * 成品盘点逻辑接口实现
 *
 * @author zzy
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("finishedGoodsInventoryService")
public class FinishedGoodsInventoryServiceImpl implements FinishedGoodsInventoryInterService {

    //过滤参数
    /** 客户代码 */
    private static final String CODE = "code";
    /** 品名*/
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";
    /** 客户料号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 存放仓库 */
    private static final String HOUSE_NAME = "houseName";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ProductInventoryDAO productInventoryDAO;

    @Override
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getFinishedGoodsInventory(Map<String, Object> params) {
        return CallbackUtils.getCallback("getFinishedGoodsInventory", params.toString(), () -> new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productInventoryDAO.countRepertory(params), productInventoryDAO.getProductInventoryRepertory(params))));
    }

    @Override
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getFinishedGoodsInventoryByConditions(Map<String, Object> params){
        return CallbackUtils.getCallback("getFinishedGoodsInventoryByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ProductInventoryRepertoryVO>> resultVO = getFinishedGoodsInventory(params);

            if (resultVO == null) {
                logger.error("getProdInventoryByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ProductInventoryRepertoryVO> result = resultVO.getModule().getRows();

            // 过滤
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

            if (params.containsKey(HOUSE_NAME)) {
                result = result.stream().filter(item -> null != item.getHouseName() && item.getHouseName().toLowerCase().contains(params.get(HOUSE_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> exportExcel(ProductInventoryRepertoryVO[] productInventoryRepertoryVO) {
        if (null == productInventoryRepertoryVO || 0 == productInventoryRepertoryVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return CallbackUtils.exportCallback("FinishedGoodsInventory exportExcel", Arrays.toString(productInventoryRepertoryVO), () -> {
            List<ProductInventoryModel> data = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int i = 1;
            for(ProductInventoryRepertoryVO temp : productInventoryRepertoryVO) {
                ProductInventoryModel item = new ProductInventoryModel();
                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setProductNumber(temp.getProductNumber());
                item.setSpec(temp.getSpec());
                item.setStorageAmount(temp.getStorageAmount());
                item.setUnitPrice(temp.getUnitPrice());
                item.setUnit(temp.getUnit());
                item.setMoney(temp.getMoney());
                item.setHouseName(temp.getHouseName());
                item.setStorageDate(format.format(temp.getStorageDate()));
                data.add(item);
            }

            Map<Integer,Integer> columnWidth = new HashMap<>(10);
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

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.PRODUCT_INVENTORY_FILE_NAME, ProductInventoryModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

}
