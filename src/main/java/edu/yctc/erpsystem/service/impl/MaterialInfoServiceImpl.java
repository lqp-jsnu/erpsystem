package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.MaterialInfoDAO;
import edu.yctc.erpsystem.dao.MaterialInfoMasterDAO;
import edu.yctc.erpsystem.dao.SupplierDAO;
import edu.yctc.erpsystem.dao.UserDAO;
import edu.yctc.erpsystem.entity.*;
import edu.yctc.erpsystem.excel.MaterialInfoMasterModel;
import edu.yctc.erpsystem.excel.MaterialInfoModel;
import edu.yctc.erpsystem.interceptor.MaterialInfoExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialInfoInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 料品原始信息接口实现
 *
 * @author xcg
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialInfoService")
public class MaterialInfoServiceImpl implements MaterialInfoInterService {

    // 过滤参数
    /** 供应商代号 */
    private static final String CODE = "code";
    /** 品名／磁棒／尺寸(材质) */
    private static final String ITEM_NAME = "itemName";
    /** 规格/初R值/电阻线(线径) */
    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private MaterialInfoDAO materialInfoDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Override
    public ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfo(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInfo", params.toString(), () -> {
            List<MaterialInfoVO> materialInfoViewList = new ArrayList<>();

            // 得到所偶有数据字典信息
            List<MaterialInfoDO> materialInfoList = materialInfoDAO.getMaterialInfo(params);
            if (materialInfoList == null) {
                logger.error("getMaterialInfoMaster exception, db error, params={}", params);
                return new ResultDO<>( false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR,null);
            }
            // 如果没有记录直接返回
            if (0 == materialInfoList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, materialInfoViewList));
            }

            // 获得所有材料原始信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialInfoList.stream().map(MaterialInfoDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            // 获得所有供应商信息
            List<SupplierDO> supperList = supplierDAO.getSupplierByIds(materialInfoList.stream().map(MaterialInfoDO::getSupplierId).collect(Collectors.toList()));
            Map<String, SupplierDO> supperMap = supperList.stream().collect(Collectors.toMap(SupplierDO::getId, supper -> supper));

            // 得到所有审核者
            List<UserDO> checkerList = userDAO.getUserByIds(materialInfoList.stream().map(MaterialInfoDO::getChecker).collect(Collectors.toList()));
            Map<String, UserDO> checkerMap = checkerList.stream().collect(Collectors.toMap(UserDO::getId, checker -> checker));

            for (MaterialInfoDO tempMaterialInfo : materialInfoList) {
                MaterialInfoVO temp = new MaterialInfoVO();

                temp.setId(tempMaterialInfo.getId());
                temp.setCode(supperMap.get(tempMaterialInfo.getSupplierId()).getCode());
                temp.setName(supperMap.get(tempMaterialInfo.getSupplierId()).getName());
                temp.setSupplierId(tempMaterialInfo.getSupplierId());
                temp.setMaterialInfoMasterId(tempMaterialInfo.getMaterialInfoMasterId());
                temp.setCreateTime(tempMaterialInfo.getCreateTime());
                temp.setItemName(materialInfoMasterMap.get(tempMaterialInfo.getMaterialInfoMasterId()).getItemName());
                temp.setSpec(materialInfoMasterMap.get(tempMaterialInfo.getMaterialInfoMasterId()).getSpec());
                temp.setRemark(tempMaterialInfo.getRemark());
                temp.setUnit(materialInfoMasterMap.get(tempMaterialInfo.getMaterialInfoMasterId()).getUnit());
                temp.setUnitPrice(tempMaterialInfo.getUnitPrice());
                temp.setCheckFlag(tempMaterialInfo.getCheckFlag());
                if (null != tempMaterialInfo.getChecker()) {
                    temp.setChecker(checkerMap.get(tempMaterialInfo.getChecker()).getName());
                }
                temp.setSpecialRequire(tempMaterialInfo.getSpecialRequire());

                materialInfoViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialInfoDAO.count(params), materialInfoViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInfoByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialInfoVO>> resultVO = getMaterialInfo(params);
            if (resultVO == null) {
                logger.error("getMaterialDumpByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialInfoVO> materialInfoViewList = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(CODE)) {
                materialInfoViewList = materialInfoViewList.stream().filter(item -> null != item.getCode() && item.getCode().toLowerCase().contains(params.get(CODE).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(ITEM_NAME)) {
                materialInfoViewList = materialInfoViewList.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(SPEC)) {
                materialInfoViewList = materialInfoViewList.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialInfoViewList.size(), materialInfoViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialInfoDO materialInfoDO) {
        if (StringUtils.isBlank(materialInfoDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialInfoDO.getSupplierId()) || StringUtils.isBlank(materialInfoDO.getUnitPrice())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialInfo", materialInfoDO.toString(), () -> materialInfoDAO.insert(materialInfoDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialInfo", id, () -> materialInfoDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateMaterialInfo(MaterialInfoDO materialInfoDO) {
        if (StringUtils.isBlank(materialInfoDO.getId()) || StringUtils.isBlank(materialInfoDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialInfoDO.getSupplierId()) || StringUtils.isBlank(materialInfoDO.getUnitPrice())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateMaterialInfo", materialInfoDO.toString(), () -> materialInfoDAO.updateMaterialInfo(materialInfoDO));
    }

    @Override
    public ResultDO<Void> updateCheckerById(MaterialInfoDO materialInfoDO) {
        if (StringUtils.isBlank(materialInfoDO.getId()) || StringUtils.isBlank(materialInfoDO.getChecker()) || StringUtils.isBlank(materialInfoDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("MaterialInfo updateCheckerById", materialInfoDO.toString(), () -> materialInfoDAO.updateCheckerById(materialInfoDO));
    }

    @Override
    public ResultDO<Void> exportExcel(MaterialInfoVO[] materialInfoVO) {
        if (null == materialInfoVO || 0 == materialInfoVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialInfo exportExcel", Arrays.toString(materialInfoVO), () -> {
            List<MaterialInfoModel> data = new ArrayList<>();

            int i = 1;
            for (MaterialInfoVO temp : materialInfoVO) {
                MaterialInfoModel item = new MaterialInfoModel();
                item.setIndex(Integer.toString(i++));
                item.setCode(temp.getCode());
                item.setName(temp.getName());
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setUnitPrice(temp.getUnitPrice());
                item.setRemark(temp.getRemark());
                item.setLabel("");
                item.setColorCode("");
                item.setUnit(temp.getUnit());

                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(8);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(10.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(26.43*256));
            columnWidth.put(4,(int)(26.86*256));
            columnWidth.put(5,(int)(10.0*256));
            columnWidth.put(6,(int)(5.14*256));
            columnWidth.put(7,(int)(5.71*256));
            columnWidth.put(8,(int)(20.14*256));
            columnWidth.put(9,(int)(5.71*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_INFO_FILE_NAME, MaterialInfoMasterModel.class, data, columnWidth, new MaterialInfoExcelStyleHandler());
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoBySearchSuppId(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInfoBySearchSuppId", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialInfoVO>> resultVO = getMaterialInfo(params);
            if (resultVO == null) {
                logger.error("getMaterialInfoBySearchSuppId exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }
            List<MaterialInfoVO> result = resultVO.getModule().getRows();

            // 模糊查询spec
            String spec = params.get(SPEC).toString().toLowerCase();
            result = result.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(spec)).collect(Collectors.toList());

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(result.size(), result.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

}
