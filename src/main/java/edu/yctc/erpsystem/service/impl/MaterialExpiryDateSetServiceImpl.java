package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.MaterialExpiryDateSetDAO;
import edu.yctc.erpsystem.dao.MaterialInfoMasterDAO;
import edu.yctc.erpsystem.entity.MaterialExpiryDateSetDO;
import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialExpiryDateSetInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExpiryDateSetVO;
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
 * 原材料保质期设置逻辑接口实现
 *
 * @author qiang
 * */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialExpiryDateSetService")
public class MaterialExpiryDateSetServiceImpl implements MaterialExpiryDateSetInterService {

    // 过滤参数
    /** 品名 */
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private MaterialExpiryDateSetDAO materialExpiryDateSetDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Override
    public ResultDO<PageUtils<MaterialExpiryDateSetVO>> getMaterialDateSet(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialDateSet", params.toString(), () -> {
            List<MaterialExpiryDateSetVO> materialExpiryDateViewList = new ArrayList<>();
            // 获得成品数量设置信息
            List<MaterialExpiryDateSetDO> materialExpiryDateSetList = materialExpiryDateSetDAO.getMaterialDateSet(params);
            if (materialExpiryDateSetList == null) {
                logger.error("getMaterialDateSet exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            if (materialExpiryDateSetList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, materialExpiryDateViewList));
            }

            // 获得原材料信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialExpiryDateSetList.stream().map(MaterialExpiryDateSetDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            for (MaterialExpiryDateSetDO tempMaterialExpiryDateSet : materialExpiryDateSetList) {
                MaterialExpiryDateSetVO temp = new MaterialExpiryDateSetVO();

                temp.setItemName(materialInfoMasterMap.get(tempMaterialExpiryDateSet.getMaterialInfoMasterId()).getItemName());
                temp.setSpec(materialInfoMasterMap.get(tempMaterialExpiryDateSet.getMaterialInfoMasterId()).getSpec());
                temp.setUnit(materialInfoMasterMap.get(tempMaterialExpiryDateSet.getMaterialInfoMasterId()).getUnit());
                temp.setExpiryDate(tempMaterialExpiryDateSet.getExpiryDate());
                temp.setCreateTime(tempMaterialExpiryDateSet.getCreateTime());
                temp.setRemark(tempMaterialExpiryDateSet.getRemark());
                temp.setId(tempMaterialExpiryDateSet.getId());
                temp.setMaterialInfoMasterId(tempMaterialExpiryDateSet.getMaterialInfoMasterId());

                materialExpiryDateViewList.add(temp);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialExpiryDateSetDAO.count(params), materialExpiryDateViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialExpiryDateSetVO>> getMaterialExpiryDateSetByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialExpiryDateSetByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialExpiryDateSetVO>> result =  getMaterialDateSet(params);

            if (result == null) {
                logger.error("getMaterialExpiryDateSetByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == result.getModule().getTotal()) {
                return result;
            }
            List<MaterialExpiryDateSetVO> materialExpiryDateSetViewList = result.getModule().getRows();

            // 过滤
            if (params.containsKey(ITEM_NAME)) {
                materialExpiryDateSetViewList = materialExpiryDateSetViewList.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(SPEC)) {
                materialExpiryDateSetViewList = materialExpiryDateSetViewList.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,  new PageUtils<>(materialExpiryDateSetViewList.size(), materialExpiryDateSetViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialExpiryDateSetDO materialExpiryDateSetDO) {
        if (StringUtils.isBlank(materialExpiryDateSetDO.getMaterialInfoMasterId()) || materialExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialExpiryDateSet", materialExpiryDateSetDO.toString(), () -> materialExpiryDateSetDAO.insert(materialExpiryDateSetDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialExpiryDateSet", id, () -> materialExpiryDateSetDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateMaterialDateSet(MaterialExpiryDateSetDO materialExpiryDateSetDO) {
        if (StringUtils.isBlank(materialExpiryDateSetDO.getMaterialInfoMasterId()) || materialExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateMaterialDateSet", materialExpiryDateSetDO.toString(), () -> materialExpiryDateSetDAO.updateMaterialDateSet(materialExpiryDateSetDO));
    }

}
