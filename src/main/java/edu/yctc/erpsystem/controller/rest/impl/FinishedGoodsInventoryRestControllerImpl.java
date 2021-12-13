package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.FinishedGoodsInventoryRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.FinishedGoodsInventoryInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 成品盘点数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/finished-goods-inventory")
public class FinishedGoodsInventoryRestControllerImpl implements FinishedGoodsInventoryRestController {

    @Resource
    private FinishedGoodsInventoryInterService finishedGoodsInventoryService;

    @Override
    @GetMapping("getFinishedGoodsInventory")
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getFinishedGoodsInventory(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return finishedGoodsInventoryService.getFinishedGoodsInventory(params);
    }

    @Override
    @GetMapping("getFinishedGoodsInventoryByConditions")
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getFinishedGoodsInventoryByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return finishedGoodsInventoryService.getFinishedGoodsInventoryByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel( @RequestBody ProductInventoryRepertoryVO[] prodInventoryVO) {
        if (null == prodInventoryVO || 0 == prodInventoryVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return finishedGoodsInventoryService.exportExcel(prodInventoryVO);
    }

}
