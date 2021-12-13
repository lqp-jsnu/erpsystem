package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialInfoMasterRestController;
import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialInfoMasterInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料数据接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-owner-information")

public class MaterialInfoMasterRestControllerImpl implements MaterialInfoMasterRestController {

    @Resource
    private MaterialInfoMasterInterService materialInfoMasterService;

    @Override
    @GetMapping("getMaterialInfoMaster")
    public ResultDO<PageUtils<MaterialInfoMasterDO>> getMaterialInfoMaster(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoMasterService.getMaterialInfoMaster(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialInfoMasterDO materialInfoMasterDO) {
        if (StringUtils.isBlank(materialInfoMasterDO.getItemName()) || StringUtils.isBlank(materialInfoMasterDO.getSpec()) || StringUtils.isBlank(materialInfoMasterDO.getUnit())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoMasterService.insert(materialInfoMasterDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialInfoMasterDO materialInfoMasterDO) {
        if (StringUtils.isBlank(materialInfoMasterDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoMasterService.delete(materialInfoMasterDO.getId());
    }

    @Override
    @PostMapping("updateMaterialInfoMaster")
    public ResultDO<Void> updateMaterialInfoMaster(@RequestBody MaterialInfoMasterDO materialInfoMasterDO) {
        if (StringUtils.isBlank(materialInfoMasterDO.getItemName()) || StringUtils.isBlank(materialInfoMasterDO.getSpec())
                || StringUtils.isBlank(materialInfoMasterDO.getUnit()) || StringUtils.isBlank(materialInfoMasterDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoMasterService.updateMaterialInfoMaster(materialInfoMasterDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialInfoMasterDO[] materialInfoMasterDO) {
        if (null == materialInfoMasterDO || 0 == materialInfoMasterDO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialInfoMasterService.exportExcel(materialInfoMasterDO);
    }

}
