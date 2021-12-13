package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ProductInventoryRepertoryRestController;
import edu.yctc.erpsystem.entity.ProductAllocationDO;
import edu.yctc.erpsystem.entity.ProductExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductInventoryRepertoryInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 成品库存数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/finished-goods-repertory")
public class ProductInventoryRepertoryRestControllerImpl implements ProductInventoryRepertoryRestController {

    @Resource
    private ProductInventoryRepertoryInterService productInventoryRepertoryService;

    @Override
    @GetMapping("getProductInventoryRepertory")
    public ResultDO<PageUtils<ProductInventoryRepertoryVO>> getProductInventoryRepertory(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryRepertoryService.getProductInventoryRepertory(params);
    }

    @Override
    @GetMapping("getProductInventoryRepertoryByConditions")
    public   ResultDO<PageUtils<ProductInventoryRepertoryVO>> getProductInventoryRepertoryByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryRepertoryService.getProductInventoryRepertoryByConditions(params);
    }

    @Override
    @PostMapping("updateStorageAmount")
    public ResultDO<Void> updateStorageAmount(@RequestBody ProductInventoryRepertoryVO productInventoryRepertoryVO) {
        if(StringUtils.isBlank(productInventoryRepertoryVO.getProductStorageId()) || StringUtils.isBlank(productInventoryRepertoryVO.getStorageAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productInventoryRepertoryService.updateStorageAmount(productInventoryRepertoryVO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ProductInventoryRepertoryVO[] productInventoryRepertoryVO) {
        if (null == productInventoryRepertoryVO || 0 == productInventoryRepertoryVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        return productInventoryRepertoryService.exportExcel(productInventoryRepertoryVO);
    }

    @Override
    @PostMapping("productInventorySale")
    public ResultDO<Void> productInventorySale(@RequestBody ProductExportDO productExportDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(productExportDO.getProductInventoryId())
                || StringUtils.isBlank(productExportDO.getOrderNumber()) || StringUtils.isBlank(productExportDO.getNumber())
                || StringUtils.isBlank(productExportDO.getAmount()) || StringUtils.isBlank(productExportDO.getDate().toString())
                || StringUtils.isBlank(productExportDO.getCustomerId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        productExportDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return productInventoryRepertoryService.productInventorySale(productExportDO);

    }

    @Override
    @PostMapping("productAllocation")
    public ResultDO<Void> productAllocation(@RequestBody ProductAllocationDO productAllocationDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(productAllocationDO.getProductInventoryId())
                || StringUtils.isBlank(productAllocationDO.getAmount()) || StringUtils.isBlank(productAllocationDO.getWarehouseId())
                || StringUtils.isBlank(productAllocationDO.getAllocationDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        productAllocationDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return productInventoryRepertoryService.productAllocation(productAllocationDO);
    }

}
