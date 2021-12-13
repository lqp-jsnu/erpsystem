package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialStorageRestController;
import edu.yctc.erpsystem.entity.MaterialStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialStorageInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialStorageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料入库数据接口实现
 *
 * @author wjd
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/wareHousing-of-raw-materials")
public class MaterialStorageRestControllerImpl implements MaterialStorageRestController {

    @Resource
    private MaterialStorageInterService materialStorageService;

    @Override
    @GetMapping("getMaterialStorage")
    public ResultDO<PageUtils<MaterialStorageVO>> getMaterialStorage(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialStorageService.getMaterialStorage(params);
    }

    @Override
    @GetMapping("getMaterialStorageByConditions")
    public ResultDO<PageUtils<MaterialStorageVO>> getMaterialStorageByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialStorageService.getMaterialStorageByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialStorageDO materialStorageDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(materialStorageDO.getMaterialPurchaseToBeStorageId())
                || StringUtils.isBlank(materialStorageDO.getInDate().toString()) || StringUtils.isBlank(materialStorageDO.getInAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialStorageDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialStorageService.insert(materialStorageDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialStorageVO materialStorage) {
        if (StringUtils.isBlank(materialStorage.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialStorageService.delete(materialStorage.getId());
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody MaterialStorageDO materialStorageDO, HttpSession httpSession) {
       if (StringUtils.isBlank(materialStorageDO.getId()) || StringUtils.isBlank(materialStorageDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialStorageDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialStorageService.updateCheckerById(materialStorageDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialStorageVO[] materialStorage) {
        if (null == materialStorage || 0 == materialStorage.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialStorageService.exportExcel(materialStorage);
    }

}
