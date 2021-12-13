package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialPurchaseRestController;
import edu.yctc.erpsystem.entity.MaterialPurchaseDO;
import edu.yctc.erpsystem.entity.MaterialPurchaseToBeStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialPurchaseInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialPurchaseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料采购信息接口实现
 *
 * @author wjd
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/purchase-of-raw-materials")
public class MaterialPurchaseRestControllerImpl implements MaterialPurchaseRestController {

    @Resource
    private MaterialPurchaseInterService materialPurchaseService;

    @Override
    @GetMapping("getMaterialPurchase")
    public ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchase(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseService.getMaterialPurchase(params);
    }

    @Override
    @GetMapping("getMaterialPurchaseByConditions")
    public ResultDO<PageUtils<MaterialPurchaseVO>> getMaterialPurchaseByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseService.getMaterialPurchaseByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialPurchaseDO materialPurchaseDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(materialPurchaseDO.getMaterialInfoId())
                || StringUtils.isBlank(materialPurchaseDO.getSupplierId()) || StringUtils.isBlank(materialPurchaseDO.getHopeDeliveryDate().toString())
                || StringUtils.isBlank(materialPurchaseDO.getAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialPurchaseDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialPurchaseService.insert(materialPurchaseDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialPurchaseDO materialPurchaseDO) {
        if (StringUtils.isBlank(materialPurchaseDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseService.delete(materialPurchaseDO.getId());
    }

    @Override
    @PostMapping("updateMaterialPurchase")
    public ResultDO<Void> updateMaterialPurchase(@RequestBody MaterialPurchaseDO materialPurchaseDO) {
        if (StringUtils.isBlank(materialPurchaseDO.getId()) || StringUtils.isBlank(materialPurchaseDO.getAmount()) || StringUtils.isBlank(materialPurchaseDO.getHopeDeliveryDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialPurchaseService.updateMaterialPurchase(materialPurchaseDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody MaterialPurchaseDO materialPurchaseDO, HttpSession httpSession) {
        if (StringUtils.isBlank(materialPurchaseDO.getId()) || StringUtils.isBlank(materialPurchaseDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialPurchaseDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialPurchaseService.updateCheckerById(materialPurchaseDO);
    }

    @Override
    @PostMapping("insertToStored")
    public ResultDO<Void> insertToStored(@RequestBody List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageDO) {
        for (MaterialPurchaseToBeStorageDO item: materialPurchaseToBeStorageDO) {
            if (StringUtils.isBlank(item.getMaterialPurchaseId()) || StringUtils.isBlank(item.getInvoiceTitleId()) || StringUtils.isBlank(item.getOrderNumber())) {
                return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
            }
        }

        return materialPurchaseService.insertToStored(materialPurchaseToBeStorageDO);
    }

    @Override
    @PostMapping("importExcel")
    public ResultDO<Void> importExcel(HttpSession httpSession) {
        return materialPurchaseService.importExcel(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
    }

}
