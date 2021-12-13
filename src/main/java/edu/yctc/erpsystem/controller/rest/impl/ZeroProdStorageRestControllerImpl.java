package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ZeroProdStorageRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ZeroProductStorageDO;
import edu.yctc.erpsystem.service.ZeroProductStorageInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ZeroProductStorageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 零品入库数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/zero-product-put-in-storage")
public class ZeroProdStorageRestControllerImpl implements ZeroProdStorageRestController {

    @Resource
    private ZeroProductStorageInterService zeroProdStorageService;

    @Override
    @GetMapping("getZeroProductStorage")
    public ResultDO<PageUtils<ZeroProductStorageVO>> getZeroProductStorage(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return zeroProdStorageService.getZeroProductStorage(params);
    }

    @Override
    @GetMapping("getZeroProductStorageByConditions")
    public ResultDO<PageUtils<ZeroProductStorageVO>> getZeroProductStorageByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return zeroProdStorageService.getZeroProductStorageByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ZeroProductStorageDO zeroProdStorageDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(zeroProdStorageDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(zeroProdStorageDO.getProductQuantity()) || StringUtils.isBlank(zeroProdStorageDO.getStorageAmount())
                || StringUtils.isBlank(zeroProdStorageDO.getWarehouseId()) || StringUtils.isBlank(zeroProdStorageDO.getStorageDate().toString())
                || StringUtils.isBlank(zeroProdStorageDO.getIsProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        zeroProdStorageDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return zeroProdStorageService.insert(zeroProdStorageDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody ZeroProductStorageDO zeroProdStorageDO) {
        if (StringUtils.isBlank(zeroProdStorageDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return zeroProdStorageService.delete(zeroProdStorageDO.getId());
    }

    @Override
    @PostMapping("updateZeroProductStorage")
    public  ResultDO<Void> updateZeroProductStorage(@RequestBody ZeroProductStorageDO zeroProdStorageDO) {
        if (StringUtils.isBlank(zeroProdStorageDO.getId()) || StringUtils.isBlank(zeroProdStorageDO.getProductQuantity())
                || StringUtils.isBlank(zeroProdStorageDO.getStorageAmount()) || StringUtils.isBlank(zeroProdStorageDO.getWarehouseId())
                || StringUtils.isBlank(zeroProdStorageDO.getStorageDate().toString()) || StringUtils.isBlank(zeroProdStorageDO.getIsProductStorage())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return zeroProdStorageService.updateZeroProductStorage(zeroProdStorageDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody ZeroProductStorageDO zeroProdStorageDO, HttpSession httpSession) {
        if (StringUtils.isBlank(zeroProdStorageDO.getId()) || StringUtils.isBlank(zeroProdStorageDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        zeroProdStorageDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return zeroProdStorageService.updateCheckerById(zeroProdStorageDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody ZeroProductStorageVO[] zeroProdStorageVO) {
        if (null==zeroProdStorageVO || 0 == zeroProdStorageVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return zeroProdStorageService.exportExcel(zeroProdStorageVO);
    }

}
