package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ManufactureProcessSlaveRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ManufactureProcessSlaveInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManufactureProcessSlaveVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 制造流程单信息数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/manufacturing-process-sheet")
public class ManufactureProcessSlaveRestControllerImpl implements ManufactureProcessSlaveRestController {

    @Resource
    private ManufactureProcessSlaveInterService manufactureProcessSlaveService;

    @Override
    @GetMapping("getManufactureProcessSLave")
    public ResultDO<PageUtils<ManufactureProcessSlaveVO>> getManufactureProcessSlave(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessSlaveService.getManufactureProcessSlave(params);
    }

    @Override
    @GetMapping("getManufactureProcessSlaveByMaterialInventoryMasterId")
    public ResultDO<PageUtils<ManufactureProcessSlaveVO>> getManufactureProcessSlaveByMaterialInventoryMasterId(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessSlaveService.getManufactureProcessSlaveByMaterialInventoryMasterId(params);
    }

    @Override
    @PostMapping("changeIsZeroProductInHouseWhenInsert")
    public ResultDO<Void> changeIsZeroProductInHouseWhenInsert(@RequestBody ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessSlaveService.changeIsZeroProductInHouseWhenInsert(manufactureProcessSlaveVO);
    }

    @Override
    @PostMapping("changeIsZeroProductInHouseWhenDelete")
    public ResultDO<Void> changeIsZeroProductInHouseWhenDelete(@RequestBody ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessSlaveService.changeIsZeroProductInHouseWhenDelete(manufactureProcessSlaveVO);
    }

    @Override
    @PostMapping("changeIsInHouseWhenInsert")
    public ResultDO<Void> changeIsInHouseWhenInsert(@RequestBody ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessSlaveService.changeIsInHouseWhenInsert(manufactureProcessSlaveVO);
    }

    @Override
    @PostMapping("changeIsInHouseWhenDelete")
    public ResultDO<Void> changeIsInHouseWhenDelete(@RequestBody ManufactureProcessSlaveVO manufactureProcessSlaveVO) {
        if (StringUtils.isBlank(manufactureProcessSlaveVO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return manufactureProcessSlaveService.changeIsInHouseWhenDelete(manufactureProcessSlaveVO);
    }

}
