package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ProductExpiryDateSetRestController;
import edu.yctc.erpsystem.entity.ProductExpiryDateSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductExpiryDateSetInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductExpiryDateSetVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 产品数保质期设置接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/shelf-life-setting")
public class ProductExpiryDateSetRestControllerImpl implements ProductExpiryDateSetRestController {

    @Resource
    private ProductExpiryDateSetInterService productExpiryDateSetService;

    @Override
    @GetMapping("getProductExpiryDateSet")
    public ResultDO<PageUtils<ProductExpiryDateSetVO>> getProductExpiryDateSet(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productExpiryDateSetService.getExpiryDateSet(params);
    }

    @Override
    @GetMapping("getProductExpiryDateSetByConditions")
    public ResultDO<PageUtils<ProductExpiryDateSetVO>> getProductExpiryDateSetByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productExpiryDateSetService.getProductExpiryDateSetByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ProductExpiryDateSetDO productExpiryDateSetDO) {
        if (StringUtils.isBlank(productExpiryDateSetDO.getOriginalProductId()) || productExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productExpiryDateSetService.insert(productExpiryDateSetDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody ProductExpiryDateSetDO productExpiryDateSetDO) {
        if (StringUtils.isBlank(productExpiryDateSetDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productExpiryDateSetService.delete(productExpiryDateSetDO.getId());
    }

    @Override
    @PostMapping("updateExpiryDateSet")
    public ResultDO<Void> updateExpiryDateSet(@RequestBody ProductExpiryDateSetDO productExpiryDateSetDO) {
        if (StringUtils.isBlank(productExpiryDateSetDO.getOriginalProductId()) || productExpiryDateSetDO.getExpiryDate() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productExpiryDateSetService.updateExpiryDateSet(productExpiryDateSetDO);
    }

}