package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.CustomerDAO;
import edu.yctc.erpsystem.dao.MaterialInfoMasterDAO;
import edu.yctc.erpsystem.dao.OriginalProductDAO;
import edu.yctc.erpsystem.entity.CustomerDO;
import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.OriginalProductDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.excel.OriginalProductModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import edu.yctc.erpsystem.service.OriginalProductInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.FileUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.OriginalProductVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 原始成品逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("originalProductService")
public class OriginalProductServiceImpl implements OriginalProductInterService {

    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("service");

    // 过滤参数
    /**
     * 客户代号
     */
    private static final String CODE = "code";
    /**
     * 品名／磁棒／尺寸(材质)
     */
    private static final String MASTER_ITEM_NAME = "masterItemName";
    /**
     * 规格/初R值/电阻线(线径)
     */
    private static final String MASTER_SPEC = "masterSpec";

    @Resource
    private OriginalProductDAO originalProductDAO;

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Override
    public ResultDO<PageUtils<OriginalProductVO>> getOriginalProduct(Map<String, Object> params) {
        return CallbackUtils.getCallback("getOriginalProduct", params.toString(), () -> {
            List<OriginalProductVO> originalProductViewList = new ArrayList<>();

            // 得到所有成品原始信息
            List<OriginalProductDO> originalProductList = originalProductDAO.getOriginalProduct(params);
            if (originalProductList == null) {
                logger.error("getOriginalProduct exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == originalProductList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, originalProductViewList));
            }

            // 获得所有供应商信息
            List<CustomerDO> customerList = customerDAO.getCustomerByIds(originalProductList.stream().map(OriginalProductDO::getCustomerId).collect(Collectors.toList()));
            Map<String, CustomerDO> customerMap = customerList.stream().collect(Collectors.toMap(CustomerDO::getId, customer -> customer));

            // 获得所有材料信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(originalProductList.stream().map(OriginalProductDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            for (OriginalProductDO tempOriginalProduct : originalProductList) {
                OriginalProductVO temp = new OriginalProductVO();

                temp.setId(tempOriginalProduct.getId());
                temp.setCode(customerMap.get(tempOriginalProduct.getCustomerId()).getCode());
                temp.setItemName(tempOriginalProduct.getItemName());
                temp.setSpec(tempOriginalProduct.getSpec());
                if (null != tempOriginalProduct.getMaterialInfoMasterId()) {
                    temp.setMasterItemName(materialInfoMasterMap.get(tempOriginalProduct.getMaterialInfoMasterId()).getItemName());
                    temp.setMasterSpec(materialInfoMasterMap.get(tempOriginalProduct.getMaterialInfoMasterId()).getSpec());
                }
                temp.setProductNumber(tempOriginalProduct.getProductNumber());
                temp.setUnit(tempOriginalProduct.getUnit());
                temp.setUnitPrice(tempOriginalProduct.getUnitPrice());
                temp.setLabel(tempOriginalProduct.getLabel());
                temp.setColorCode(tempOriginalProduct.getColorCode());
                temp.setRemark(tempOriginalProduct.getRemark());
                temp.setCreateTime(tempOriginalProduct.getCreateTime());
                temp.setCheckFlag(tempOriginalProduct.getCheckFlag());
                temp.setCustomerId(tempOriginalProduct.getCustomerId());
                temp.setDrawingUrl(tempOriginalProduct.getDrawingUrl());
                temp.setMaterialInfoMasterId(tempOriginalProduct.getMaterialInfoMasterId());

                originalProductViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(originalProductDAO.count(params), originalProductViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<OriginalProductVO>> getOriginalProductByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getOriginalProductByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<OriginalProductVO>> resultVO = getOriginalProduct(params);
            if (resultVO == null) {
                logger.error("getOriginalProductByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<OriginalProductVO> originalProductViewList = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                originalProductViewList = originalProductViewList.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(MASTER_ITEM_NAME)) {
                originalProductViewList = originalProductViewList.stream().filter(item -> null != item.getMasterItemName() && item.getMasterItemName().toLowerCase().contains(params.get(MASTER_ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }

            if (params.containsKey(MASTER_SPEC)) {
                originalProductViewList = originalProductViewList.stream().filter(item -> null != item.getMasterSpec() && item.getMasterSpec().toLowerCase().contains(params.get(MASTER_SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(originalProductViewList.size(), originalProductViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getItemName()) || StringUtils.isBlank(originalProductDO.getSpec())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("OriginalProduct", originalProductDO.toString(), () -> originalProductDAO.insert(originalProductDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("OriginalProduct", id, () -> {
            String drawingUrl = originalProductDAO.getOriginalProductById(id).getDrawingUrl();

            originalProductDAO.delete(id);

            FileUtils.deleteFile(ConstantHolder.PRODUCT_IMG_SAVE_PATH, drawingUrl);
        });
    }

    @Override
    public ResultDO<Void> updateOriginalProduct(OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getItemName()) || StringUtils.isBlank(originalProductDO.getSpec())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateOriginalProduct", originalProductDO.toString(), () -> originalProductDAO.updateOriginalProduct(originalProductDO));
    }

    @Override
    public ResultDO<Void> updateDrawById(OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getId()) || StringUtils.isBlank(originalProductDO.getDrawingUrl())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateDrawById", originalProductDO.toString(), () -> originalProductDAO.updateDrawById(originalProductDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getId()) || StringUtils.isBlank(originalProductDO.getChecker()) || StringUtils.isBlank(originalProductDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateCheckerById", originalProductDO.toString(), () -> originalProductDAO.updateCheckerById(originalProductDO));
    }

    @Override
    public ResultDO<Void> exportExcel(OriginalProductVO[] originalProductVO) {
        if (null == originalProductVO || 0 == originalProductVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("OriginalProduct exportExcel", Arrays.toString(originalProductVO), () -> {
            List<OriginalProductModel> data = new ArrayList<>();

            int i = 1;
            for (OriginalProductVO temp : originalProductVO) {
                OriginalProductModel item = new OriginalProductModel();
                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setMasterItemName(temp.getMasterItemName());
                item.setMasterSpec(temp.getMasterSpec());
                item.setProductNumber(temp.getProductNumber());
                item.setUnit(temp.getUnit());
                item.setUnitPrice(temp.getUnitPrice());
                item.setLabel(temp.getLabel());
                item.setColorCode(temp.getColorCode());
                item.setRemark(temp.getRemark());
                item.setDrawingUrl(temp.getDrawingUrl());
                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(13);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(23.43*256));
            columnWidth.put(3,(int)(23.43*256));
            columnWidth.put(4,(int)(23.43*256));
            columnWidth.put(5,(int)(23.43*256));
            columnWidth.put(6,(int)(26.14*256));
            columnWidth.put(7,(int)(8.71*256));
            columnWidth.put(8,(int)(8.71*256));
            columnWidth.put(9,(int)(23.43*256));
            columnWidth.put(10,(int)(24.86*256));
            columnWidth.put(11,(int)(26.14*256));
            columnWidth.put(12,(int)(26.14*256));

            return MyExcel.write( ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_MATERIAL_INVENTORY_FILE_NAME, OriginalProductModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<PageUtils<OriginalProductVO>> getOriginalProductBySearchCustomerId(Map<String, Object> params) {
        return CallbackUtils.getCallback("getCustomerOrderBySearchSuppId", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<OriginalProductVO>> result = getOriginalProduct(params);
            if (result == null) {
                logger.error( "getOriginalProductBySearchCustomerId exception, db error, params={}", params );
                return new ResultDO<>( false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null );
            }
            // 如果没有记录直接返回
            if (0 == result.getModule().getTotal()) {
                return result;
            }
            List<OriginalProductVO> originalProductViewList = result.getModule().getRows();

            String spec = params.get( SPEC ).toString().toLowerCase();
            originalProductViewList = originalProductViewList.stream().filter( item -> item.getMasterSpec().contains( spec ) ).collect( Collectors.toList() );

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(originalProductViewList.size(), originalProductViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }


}
