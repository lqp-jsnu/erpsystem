package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.OriginalProductRestController;
import edu.yctc.erpsystem.entity.OriginalProductDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.OriginalProductInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.OriginalProductVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原始成品数据接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/original-information-of-finished-product")
public class OriginalProductRestControllerImpl implements OriginalProductRestController {

    /** 搜索字段 */
    private static final String CUSTOMER_ID = "customerId";
    private static final String SPEC = "spec";

    @Resource
    private OriginalProductInterService originalProductService;

    @Override
    @GetMapping("getOriginalProduct")
    public ResultDO<PageUtils<OriginalProductVO>> getOriginalProduct(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.getOriginalProduct(params);
    }

    @Override
    @GetMapping("getOriginalProductByConditions")
    public ResultDO<PageUtils<OriginalProductVO>> getOriginalProductByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.getOriginalProductByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getItemName()) || StringUtils.isBlank(originalProductDO.getSpec())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.insert(originalProductDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody OriginalProductVO originalProduct) {
        if (StringUtils.isBlank(originalProduct.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.delete(originalProduct.getId());
    }

    @Override
    @PostMapping("updateOriginalProduct")
    public ResultDO<Void> updateOriginalProduct(@RequestBody OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getItemName()) || StringUtils.isBlank(originalProductDO.getSpec())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.updateOriginalProduct(originalProductDO);
    }

    @Override
    @PostMapping("updateDrawById")
    public ResultDO<Void> updateDrawById(@RequestBody OriginalProductDO originalProductDO) {
        if (StringUtils.isBlank(originalProductDO.getId()) || StringUtils.isBlank(originalProductDO.getDrawingUrl())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.updateDrawById(originalProductDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody OriginalProductDO originalProductDO, HttpSession httpSession) {
        if (StringUtils.isBlank(originalProductDO.getId()) || StringUtils.isBlank(originalProductDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        originalProductDO.setChecker( SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return originalProductService.updateCheckerById(originalProductDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody OriginalProductVO[] originalProducts) {
        if (null == originalProducts || 0 == originalProducts.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.exportExcel(originalProducts);
    }

    @Override
    @GetMapping("getOriginalProductBySearchCustomerId")
    public ResultDO<PageUtils<OriginalProductVO>> getOriginalProductBySearchCustomerId(@RequestParam Map<String, Object> params) {
        if (!validation(params) || StringUtils.isBlank((String)params.get(CUSTOMER_ID)) || StringUtils.isBlank((String)params.get(SPEC))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return originalProductService.getOriginalProductBySearchCustomerId(params);
    }

}
