package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ProductNumberSetController;
import edu.yctc.erpsystem.entity.ProductNumberSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductNumberSetInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductNumberSetVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 产品数量设置接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/finished-product-quantity-setting")
public class ProductNumberSetControllerImpl implements ProductNumberSetController {

    @Resource
    private ProductNumberSetInterService productNumberSetService;

    @Override
    @GetMapping("getProductNumberSet")
    public ResultDO<PageUtils<ProductNumberSetVO>> getProductNumberSet(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productNumberSetService.getProductNumberSet(params);
    }

    @Override
    @GetMapping("getProductNumberSetByConditions")
    public ResultDO<PageUtils<ProductNumberSetVO>> getProductNumberSetByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productNumberSetService.getProductNumberSetByConditions(params);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody ProductNumberSetDO productNumberSetDO) {
        if (StringUtils.isBlank(productNumberSetDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productNumberSetService.delete(productNumberSetDO.getId());
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ProductNumberSetDO productNumberSetDO) {
        if (StringUtils.isBlank(productNumberSetDO.getOriginalProductId()) || StringUtils.isBlank(productNumberSetDO.getMaxNumber()) || StringUtils.isBlank(productNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productNumberSetService.insert(productNumberSetDO);
    }

    @Override
    @PostMapping("updateProductNumberSet")
    public ResultDO<Void> updateProductNumberSet(@RequestBody ProductNumberSetDO productNumberSetDO){
        if (StringUtils.isBlank(productNumberSetDO.getOriginalProductId()) || StringUtils.isBlank(productNumberSetDO.getMaxNumber()) || StringUtils.isBlank(productNumberSetDO.getMinNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productNumberSetService.updateProductNumberSet(productNumberSetDO);
    }

}
