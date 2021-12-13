package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialExpiryDateSetRestController;
import edu.yctc.erpsystem.entity.MaterialExpiryDateSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialExpiryDateSetInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExpiryDateSetVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料保质期设置接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-shelf-life-setting")
public class MaterialExpiryDateSetRestControllerImpl implements MaterialExpiryDateSetRestController {

    @Resource
    private MaterialExpiryDateSetInterService materialExpiryDateSetService;

    @Override
    @GetMapping("getMaterialExpiryDateSet")
    public ResultDO<PageUtils<MaterialExpiryDateSetVO>> getMaterialExpiryDateSet(@RequestParam  Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExpiryDateSetService.getMaterialDateSet(params);
    }

    @Override
    @GetMapping("getMaterialExpiryDateSetByConditions")
    public ResultDO<PageUtils<MaterialExpiryDateSetVO>> getMaterialExpiryDateSetByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExpiryDateSetService.getMaterialExpiryDateSetByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialExpiryDateSetDO materialExpiryDateSetDO) {
        if (StringUtils.isBlank(materialExpiryDateSetDO.getMaterialInfoMasterId()) || materialExpiryDateSetDO.getExpiryDate() == 0) {

            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExpiryDateSetService.insert(materialExpiryDateSetDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialExpiryDateSetDO materialExpiryDateSetDO) {
        if (StringUtils.isBlank(materialExpiryDateSetDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExpiryDateSetService.delete(materialExpiryDateSetDO.getId());
    }

    @Override
    @PostMapping("updateMaterialDateSet")
    public ResultDO<Void> updateMaterialDateSet(@RequestBody MaterialExpiryDateSetDO materialExpiryDateSetDO) {
        if (StringUtils.isBlank(materialExpiryDateSetDO.getMaterialInfoMasterId()) || materialExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExpiryDateSetService.updateMaterialDateSet(materialExpiryDateSetDO);
    }

}