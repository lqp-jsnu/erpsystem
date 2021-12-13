package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.OriginalProductDAO;
import edu.yctc.erpsystem.dao.ProductNumberSetDAO;
import edu.yctc.erpsystem.entity.OriginalProductDO;
import edu.yctc.erpsystem.entity.ProductNumberSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductNumberSetInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductNumberSetVO;
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
 * 产品数量设置逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("productNumberSetService")
public class ProductNumberSetServiceImpl implements ProductNumberSetInterService {

    // 过滤参数
    /** 品名 */
    private static final String ITEM_NAME = "itemName";
    /** 产品号 */
    private static final String PRODUCT_NUMBER = "productNumber";
    /** 规格 */
    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private ProductNumberSetDAO productNumberSetDAO;

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Override
    public ResultDO<PageUtils<ProductNumberSetVO>> getProductNumberSet(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductNumberSet", params.toString(), () -> {
            List<ProductNumberSetVO> productNumberViewList = new ArrayList<>();

            // 获得成品数量设置信息
            List<ProductNumberSetDO> productNumberSetList = productNumberSetDAO.getProductNumberSet(params);
            if (productNumberSetList == null) {
                logger.error("getProductNumberSet exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (productNumberSetList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, productNumberViewList));
            }

            // 获得原始成品信息
            List<OriginalProductDO> oriProductList = originalProductDAO.getOriginalProductByIds(productNumberSetList.stream().map(ProductNumberSetDO::getOriginalProductId).collect(Collectors.toList()));
            Map<String, OriginalProductDO> oriProductMap = oriProductList.stream().collect(Collectors.toMap(OriginalProductDO::getId, originalProduct -> originalProduct));

            for (ProductNumberSetDO tempProductNumberSet : productNumberSetList) {
                ProductNumberSetVO temp = new ProductNumberSetVO();

                temp.setOriginalProductId(tempProductNumberSet.getOriginalProductId());
                temp.setItemName(oriProductMap.get(tempProductNumberSet.getOriginalProductId()).getItemName());
                temp.setSpec(oriProductMap.get(tempProductNumberSet.getOriginalProductId()).getSpec());
                temp.setProductNumber(oriProductMap.get(tempProductNumberSet.getOriginalProductId()).getProductNumber());
                temp.setUnit(oriProductMap.get(tempProductNumberSet.getOriginalProductId()).getUnit());
                temp.setMinNumber(tempProductNumberSet.getMinNumber());
                temp.setMaxNumber(tempProductNumberSet.getMaxNumber());
                temp.setCreateTime(tempProductNumberSet.getCreateTime());
                temp.setRemark(tempProductNumberSet.getRemark());
                temp.setId(tempProductNumberSet.getId());


                productNumberViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productNumberSetDAO.count(params), productNumberViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<ProductNumberSetVO>> getProductNumberSetByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getProductNumberSetByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<ProductNumberSetVO>> resultVO =  getProductNumberSet(params);
            if (resultVO == null) {
                logger.error("getProductNumberSetByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<ProductNumberSetVO> productNumberSetViewList = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(ITEM_NAME)) {
                productNumberSetViewList = productNumberSetViewList.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(SPEC)) {
                productNumberSetViewList = productNumberSetViewList.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(PRODUCT_NUMBER)) {
                productNumberSetViewList = productNumberSetViewList.stream().filter(item -> null != item.getProductNumber() && item.getProductNumber().toLowerCase().contains(params.get(PRODUCT_NUMBER).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(productNumberSetViewList.size(), productNumberSetViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(ProductNumberSetDO productNumberSetDO) {
        if (StringUtils.isBlank(productNumberSetDO.getOriginalProductId()) || StringUtils.isBlank(productNumberSetDO.getMaxNumber()) || StringUtils.isBlank(productNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("ProductNumberSet", productNumberSetDO.toString(), () -> productNumberSetDAO.insert(productNumberSetDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("ProductNumberSet", id, () -> productNumberSetDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateProductNumberSet(ProductNumberSetDO productNumberSetDO) {
        if (StringUtils.isBlank(productNumberSetDO.getOriginalProductId()) || StringUtils.isBlank(productNumberSetDO.getMaxNumber()) || StringUtils.isBlank(productNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateProductNumberSet", productNumberSetDO.toString(), () -> productNumberSetDAO.updateProductNumberSet(productNumberSetDO));
    }

}
