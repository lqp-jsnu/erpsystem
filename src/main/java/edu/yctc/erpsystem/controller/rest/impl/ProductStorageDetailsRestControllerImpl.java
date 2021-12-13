package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ProductStorageDetailsRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductStorageDetailsInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductStorageDetailsVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 成品入库明细数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/finished-product-warehousing-details")
public class ProductStorageDetailsRestControllerImpl implements ProductStorageDetailsRestController {

    @Resource
    private ProductStorageDetailsInterService productStorageDetailsService;

    @Override
    @GetMapping("getProductStorageDetails")
    public ResultDO<PageUtils<ProductStorageDetailsVO>> getProductStorageDetails(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageDetailsService.getProductStorageDetails(params);
    }

    @Override
    @GetMapping("getProductStorageDetailsByConditions")
    public ResultDO<PageUtils<ProductStorageDetailsVO>> getProductStorageDetailsByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageDetailsService.getProductStorageDetailsByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ProductStorageDetailsVO[] productStorageDetailsVO) {
        if (null == productStorageDetailsVO || 0 == productStorageDetailsVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageDetailsService.exportExcel(productStorageDetailsVO);
    }

}
