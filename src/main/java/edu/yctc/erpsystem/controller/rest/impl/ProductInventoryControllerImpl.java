package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ProductInventoryController;
import edu.yctc.erpsystem.entity.ProductInventoryDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductInventoryInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainProductExpiryDateVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.ProductInventorySummaryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 成品库存数据接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/finished-goods-inventory")
public class ProductInventoryControllerImpl implements ProductInventoryController {

    @Resource
    private ProductInventoryInterService productInventoryService;

    @Override
    @GetMapping("getProductInventorySummary")
    public ResultDO<PageUtils<ProductInventorySummaryVO>> getProductInventorySummary(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryService.getProductInventorySummary(params);
    }

    @Override
    @PostMapping("exportSummaryExcel")
    public ResultDO<Void> exportExcel(@RequestBody ProductInventorySummaryVO[] prodInventorySummary) {
        if ( null == prodInventorySummary || 0 == prodInventorySummary.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryService.exportExcel(prodInventorySummary);
    }

    @Override
    @GetMapping("getMainProductNumber")
    public ResultDO<PageUtils<MainProductNumberVO>> getMainProductNumber(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryService.getMainProductNumber(params);
    }

    @Override
    @GetMapping("getMainProductExpiryDate")
    public ResultDO<PageUtils<MainProductExpiryDateVO>> getMainProductExpiryDate(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryService.getMainProductExpiryDate(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ProductInventoryDO productInventoryDO) {
        if (StringUtils.isBlank(productInventoryDO.getProductStorageId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryService.insert(productInventoryDO);
    }

}

