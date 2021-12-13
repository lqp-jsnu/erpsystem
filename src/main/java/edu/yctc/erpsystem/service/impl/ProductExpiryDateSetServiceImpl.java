package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.OriginalProductDAO;
import edu.yctc.erpsystem.dao.ProductExpiryDateSetDAO;
import edu.yctc.erpsystem.entity.OriginalProductDO;
import edu.yctc.erpsystem.entity.ProductExpiryDateSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductExpiryDateSetInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductExpiryDateSetVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 产品保质期设置逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("productExpiryDateSetService")
public class ProductExpiryDateSetServiceImpl implements ProductExpiryDateSetInterService {

    // 过滤参数
    /** 品名 */
    private static final String ITEM_NAME = "itemName";
    /** 产品号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 规格 */
    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private ProductExpiryDateSetDAO productExpiryDateSetDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Override
    public ResultDO<PageUtils<ProductExpiryDateSetVO>> getExpiryDateSet(Map<String, Object> params) {
        return CallbackUtils.getCallback("getExpiryDateSet", params.toString(), () -> {
            List<ProductExpiryDateSetVO> productExpiryDateSetViewList = new ArrayList<>();

            // 获得成品数量设置信息
            List<ProductExpiryDateSetDO> productExpiryDateSetList = productExpiryDateSetDAO.getExpiryDateSet(params);
            if (productExpiryDateSetList == null) {
                logger.error("getExpiryDateSet exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            if (productExpiryDateSetList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, productExpiryDateSetViewList));
            }

            // 获得原始成品信息
            List<OriginalProductDO> oriProductList = originalProductDAO.getOriginalProductByIds(productExpiryDateSetList.stream().map(ProductExpiryDateSetDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String, OriginalProductDO> oriProductMap = oriProductList.stream().collect(Collectors.toMap(OriginalProductDO::getId, originalProduct -> originalProduct));

            for (ProductExpiryDateSetDO tempProductExpiryDateSet : productExpiryDateSetList){
                ProductExpiryDateSetVO temp = new ProductExpiryDateSetVO();

                temp.setItemName(oriProductMap.get(tempProductExpiryDateSet.getOriginalProductId()).getItemName());
                temp.setSpec(oriProductMap.get(tempProductExpiryDateSet.getOriginalProductId()).getSpec());
                temp.setProductNumber(oriProductMap.get(tempProductExpiryDateSet.getOriginalProductId()).getProductNumber());
                temp.setUnit(oriProductMap.get(tempProductExpiryDateSet.getOriginalProductId()).getUnit());
                temp.setExpiryDate(tempProductExpiryDateSet.getExpiryDate());
                temp.setCreateTime(tempProductExpiryDateSet.getCreateTime());
                temp.setRemark(tempProductExpiryDateSet.getRemark());
                temp.setId(tempProductExpiryDateSet.getId());
                temp.setOriginalProductId(tempProductExpiryDateSet.getOriginalProductId());

                productExpiryDateSetViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productExpiryDateSetDAO.count(params), productExpiryDateSetViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<ProductExpiryDateSetVO>> getProductExpiryDateSetByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductExpiryDateSetByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ProductExpiryDateSetVO>> result =  getExpiryDateSet(params);
            if (result == null) {
                logger.error("getProductExpiryDateSetByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == result.getModule().getTotal()) {
                return result;
            }
            List<ProductExpiryDateSetVO> productExpiryDateSetViewList = result.getModule().getRows();

            // 过滤
            if (params.containsKey(ITEM_NAME)) {
                productExpiryDateSetViewList = productExpiryDateSetViewList.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(SPEC)) {
                productExpiryDateSetViewList = productExpiryDateSetViewList.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(PRODUCT_NUMBER)) {
                productExpiryDateSetViewList = productExpiryDateSetViewList.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productExpiryDateSetViewList.size(), productExpiryDateSetViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(ProductExpiryDateSetDO productExpiryDateSetDO) {
        if (StringUtils.isBlank(productExpiryDateSetDO.getOriginalProductId()) || productExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("ProductExpiryDateSet", productExpiryDateSetDO.toString(), () -> productExpiryDateSetDAO.insert(productExpiryDateSetDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("ProductExpiryDateSet", id, () -> productExpiryDateSetDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateExpiryDateSet(ProductExpiryDateSetDO productExpiryDateSetDO) {
        if (StringUtils.isBlank(productExpiryDateSetDO.getOriginalProductId()) || productExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateExpiryDateSet", productExpiryDateSetDO.toString(), () -> productExpiryDateSetDAO.updateExpiryDateSet(productExpiryDateSetDO));
    }

}
