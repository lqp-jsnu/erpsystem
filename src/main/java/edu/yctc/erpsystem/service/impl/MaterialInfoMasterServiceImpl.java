package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.MaterialInfoMasterDAO;
import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.excel.MaterialInfoMasterModel;
import edu.yctc.erpsystem.interceptor.MaterialInfoMasterExcelStyleHandler;
import edu.yctc.erpsystem.service.MaterialInfoMasterInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MyExcel;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 原材料主表业务逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("materialInfoMasterService")
public class MaterialInfoMasterServiceImpl implements MaterialInfoMasterInterService {
    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Override
    public ResultDO<PageUtils<MaterialInfoMasterDO>> getMaterialInfoMaster(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialInfoMaster", params.toString(), () -> {
            // 得到所有料品主信息
            List<MaterialInfoMasterDO> materialInfoMasterList = materialInfoMasterDAO.getMaterialInfoMaster(params);
            if (materialInfoMasterList == null) {
                logger.error("getMaterialInfoMaster exception, db error, params={}", params);
                return new ResultDO<>( false, ResultCode.DB_ERROR , ResultCode.MSG_DB_ERROR,null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(materialInfoMasterDAO.count(params), materialInfoMasterList));
        });
    }

    @Override
    public ResultDO<Void> insert(MaterialInfoMasterDO materialInfoMasterDO) {
        if (StringUtils.isBlank(materialInfoMasterDO.getItemName()) || StringUtils.isBlank(materialInfoMasterDO.getSpec()) || StringUtils.isBlank(materialInfoMasterDO.getUnit())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("MaterialInfoMaster", materialInfoMasterDO.toString(), () -> materialInfoMasterDAO.insert(materialInfoMasterDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("MaterialInfoMaster", id, () -> materialInfoMasterDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateMaterialInfoMaster(MaterialInfoMasterDO materialInfoMasterDO) {
        if (StringUtils.isBlank(materialInfoMasterDO.getItemName()) || StringUtils.isBlank(materialInfoMasterDO.getSpec()) || StringUtils.isBlank(materialInfoMasterDO.getUnit())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateMaterialInfoMaster", materialInfoMasterDO.toString(), () -> materialInfoMasterDAO.updateMaterialInfoMaster(materialInfoMasterDO));
    }

    @Override
    public ResultDO<Void> exportExcel(MaterialInfoMasterDO[] materialInfoMasterDO) {
        if (null == materialInfoMasterDO || 0 == materialInfoMasterDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.exportCallback("MaterialInfoMaster exportExcel", Arrays.toString(materialInfoMasterDO), () -> {
            List<MaterialInfoMasterModel> data = new ArrayList<>();

            int i = 1;
            for (MaterialInfoMasterDO temp : materialInfoMasterDO) {
                MaterialInfoMasterModel item = new MaterialInfoMasterModel();
                item.setIndex(Integer.toString(i++));
                item.setItemName(temp.getItemName());
                item.setSpec(temp.getSpec());
                item.setUnit(temp.getUnit());
                item.setRemark(temp.getRemark());

                data.add(item);
            }

            // 设置列宽
            Map<Integer,Integer> columnWidth = new HashMap<>(8);
            columnWidth.put(0,(int)(4.57*256));
            columnWidth.put(1,(int)(26.86*256));
            columnWidth.put(2,(int)(26.14*256));
            columnWidth.put(3,(int)(4.43*256));
            columnWidth.put(4,(int)(20.86*256));

            return MyExcel.write(ConstantHolder.FILE_SAVE_PATH + ConstantHolder.MATERIAL_INFO_MASTER_FILE_NAME, MaterialInfoMasterModel.class, data, columnWidth, new MaterialInfoMasterExcelStyleHandler());
        });
    }

}
