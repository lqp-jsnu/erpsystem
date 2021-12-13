package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialNumberSetRestController;
import edu.yctc.erpsystem.entity.MaterialNumberSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialNumberSetInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialNumberSetVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料数量设置接口实现
 *
 * @author qiang
 */

@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/raw-material-quantity-setting")
public class MaterialNumberSetRestControllerImpl implements MaterialNumberSetRestController {

    @Resource
    private MaterialNumberSetInterService materialNumberSetService;

    @Override
    @GetMapping("getMaterialNumberSet")
    public ResultDO<PageUtils<MaterialNumberSetVO>> getMaterialNumberSet(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialNumberSetService.getMaterialNumberSet(params);
    }

    @Override
    @GetMapping("getMaterialNumberSetByConditions")
    public ResultDO<PageUtils<MaterialNumberSetVO>> getProductNumberSetByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialNumberSetService.getMaterialNumberSetByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialNumberSetDO materialNumberSetDO) {
        if (StringUtils.isBlank(materialNumberSetDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialNumberSetDO.getMaxNumber()) || StringUtils.isBlank(materialNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialNumberSetService.insert(materialNumberSetDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialNumberSetDO materialNumberSetDO) {
        if (StringUtils.isBlank(materialNumberSetDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialNumberSetService.delete(materialNumberSetDO.getId());
    }

    @Override
    @PostMapping("updateMaterialNumberSet")
    public ResultDO<Void> updateMaterialNumberSet(@RequestBody MaterialNumberSetDO materialNumberSetDO) {
        if (StringUtils.isBlank(materialNumberSetDO.getMaterialInfoMasterId()) || StringUtils.isBlank(materialNumberSetDO.getMaxNumber()) || StringUtils.isBlank(materialNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialNumberSetService.updateMaterialNumberSet(materialNumberSetDO);
    }

}
