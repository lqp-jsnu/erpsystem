package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.MaterialInfoMasterDAO;
import edu.yctc.erpsystem.dao.MaterialNumberSetDAO;
import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.MaterialNumberSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialNumberSetInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialNumberSetVO;
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
 * 原材料数量设置逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialNumberSetService")
public class MaterialNumberSetServiceImpl implements MaterialNumberSetInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private MaterialNumberSetDAO materialNumberSetDAO;

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    // 过滤参数
    /** 品名 */
    private static final String ITEM_NAME = "itemName";
    /** 规格 */
    private static final String SPEC = "spec";

    @Override
    public ResultDO<PageUtils<MaterialNumberSetVO>> getMaterialNumberSet(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialNumberSet", params.toString(), () -> {
            List<MaterialNumberSetVO> materialNumberViewList = new ArrayList<>();

            // 获得成品数量设置信息
            List<MaterialNumberSetDO> materialNumberSetList = materialNumberSetDAO.getMaterialNumberSet(params);
            if (materialNumberSetList == null) {
                logger.error("getMaterialNumberSet exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            if (materialNumberSetList.size() == 0) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, materialNumberViewList));
            }

            // 获得原材料信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMasterByIds(materialNumberSetList.stream().map(MaterialNumberSetDO::getMaterialInfoMasterId).collect(Collectors.toList()));
            Map<String, MaterialInfoMasterDO> materialInfoMasterMap = materialInfoMasterList.stream().collect(Collectors.toMap(MaterialInfoMasterDO::getId, materialInfoMaster -> materialInfoMaster));

            for (MaterialNumberSetDO tempMaterialNumberSet : materialNumberSetList) {
                MaterialNumberSetVO temp = new MaterialNumberSetVO();

                temp.setItemName(materialInfoMasterMap.get(tempMaterialNumberSet.getMaterialInfoMasterId()).getItemName());
                temp.setSpec(materialInfoMasterMap.get(tempMaterialNumberSet.getMaterialInfoMasterId()).getSpec());
                temp.setUnit(materialInfoMasterMap.get(tempMaterialNumberSet.getMaterialInfoMasterId()).getUnit());
                temp.setMinNumber(tempMaterialNumberSet.getMinNumber());
                temp.setMaxNumber(tempMaterialNumberSet.getMaxNumber());
                temp.setCreateTime(tempMaterialNumberSet.getCreateTime());
                temp.setRemark(tempMaterialNumberSet.getRemark());
                temp.setId(tempMaterialNumberSet.getId());
                temp.setMaterialInfoMasterId(tempMaterialNumberSet.getMaterialInfoMasterId());

                materialNumberViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialNumberSetDAO.count(params), materialNumberViewList));
        });
    }

    @Override
    public ResultDO<PageUtils<MaterialNumberSetVO>> getMaterialNumberSetByConditions(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialNumberSetByConditions", params.toString(), () -> {
            // 获得所有数据
            Integer offset = (Integer) params.get("offset");
            Integer limit = (Integer) params.get("limit");
            params.remove("offset");
            params.remove("limit");
            ResultDO<PageUtils<MaterialNumberSetVO>> resultVO =  getMaterialNumberSet(params);
            if (resultVO == null) {
                logger.error("getMaterialNumberSetByConditions exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == resultVO.getModule().getTotal()) {
                return resultVO;
            }

            List<MaterialNumberSetVO> materialNumberSetViewList = resultVO.getModule().getRows();

            // 过滤
            if (params.containsKey(ITEM_NAME)) {
                materialNumberSetViewList = materialNumberSetViewList.stream().filter(item -> null != item.getItemName() && item.getItemName().toLowerCase().contains(params.get(ITEM_NAME).toString().toLowerCase())).collect(Collectors.toList());
            }
            if (params.containsKey(SPEC)) {
                materialNumberSetViewList = materialNumberSetViewList.stream().filter(item -> null != item.getSpec() && item.getSpec().toLowerCase().contains(params.get(SPEC).toString().toLowerCase())).collect(Collectors.toList());
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialNumberSetViewList.size(), materialNumberSetViewList.stream().skip(offset).limit(limit).collect(Collectors.toList())));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialNumberSetDO materialNumberSetDO) {
        if (StringUtils.isBlank(materialNumberSetDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialNumberSetDO.getMaxNumber()) || StringUtils.isBlank(materialNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialNumberSet", materialNumberSetDO.toString(), () -> materialNumberSetDAO.insert(materialNumberSetDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialNumberSet", id, () -> materialNumberSetDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateMaterialNumberSet(MaterialNumberSetDO materialNumberSetDO) {
        if (StringUtils.isBlank(materialNumberSetDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialNumberSetDO.getMaxNumber()) || StringUtils.isBlank(materialNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateMaterialNumberSet", materialNumberSetDO.toString(), () -> materialNumberSetDAO.updateMaterialNumberSet(materialNumberSetDO));
    }

}
