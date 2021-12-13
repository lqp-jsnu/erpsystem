package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ProductStorageRestController;
import edu.yctc.erpsystem.entity.ProductStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ProductStorageInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductStorageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 成品入库信息数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/finished-warehousing")
public class ProductStorageRestControllerImpl implements ProductStorageRestController {

    @Resource
    private ProductStorageInterService productStorageService;

    @Override
    @GetMapping("getProductStorage")
    public ResultDO<PageUtils<ProductStorageVO>> getProductStorage(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageService.getProductStorage(params);
    }

    @Override
    @GetMapping("getProductStorageByConditions")
    public ResultDO<PageUtils<ProductStorageVO>> getProductStorageByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageService.getProductStorageByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ProductStorageDO productStorageDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(productStorageDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(productStorageDO.getStorageAmount()) || StringUtils.isBlank(productStorageDO.getProductQuantity())
                || StringUtils.isBlank(productStorageDO.getWareHouseId()) || StringUtils.isBlank(productStorageDO.getStorageDate().toString())
                || StringUtils.isBlank(productStorageDO.getIsZeroProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        productStorageDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return productStorageService.insert(productStorageDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody ProductStorageDO productStorageDO) {
        if (StringUtils.isBlank(productStorageDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageService.delete(productStorageDO.getId());
    }

    @Override
    @PostMapping("updateProductStorage")
    public ResultDO<Void> updateProductStorage(@RequestBody ProductStorageDO productStorageDO) {
        if (StringUtils.isBlank(productStorageDO.getId()) || StringUtils.isBlank(productStorageDO.getProductQuantity())
                || StringUtils.isBlank(productStorageDO.getStorageAmount()) || StringUtils.isBlank(productStorageDO.getWareHouseId())
                || StringUtils.isBlank(productStorageDO.getIsZeroProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageService.updateProductStorage(productStorageDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody ProductStorageDO productStorageDO, HttpSession httpSession) {
        if (StringUtils.isBlank(productStorageDO.getId()) || StringUtils.isBlank(productStorageDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        productStorageDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return productStorageService.updateCheckerById(productStorageDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ProductStorageVO[] productStorageVO) {
        if (null == productStorageVO || 0 == productStorageVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return productStorageService.exportExcel(productStorageVO);
    }

}
