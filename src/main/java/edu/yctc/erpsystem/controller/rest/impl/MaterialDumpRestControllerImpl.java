package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialDumpRestController;
import edu.yctc.erpsystem.entity.MaterialDumpDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialDumpInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialDumpVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 原材料报废数据接口实现
 *
 * @author lqp
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/scrap-of-raw-materials")
public class MaterialDumpRestControllerImpl implements MaterialDumpRestController {

    @Resource
    private MaterialDumpInterService materialDumpService;

    @Override
    @GetMapping("getMaterialDump")
    public ResultDO<PageUtils<MaterialDumpVO>> getMaterialDump(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialDumpService.getMaterialDump(params);
    }

    @Override
    @GetMapping("getMaterialDumpByConditions")
    public ResultDO<PageUtils<MaterialDumpVO>> getMaterialDumpByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialDumpService.getMaterialDumpByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody MaterialDumpDO materialDumpDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(materialDumpDO.getMaterialInventoryId())
                || StringUtils.isBlank(materialDumpDO.getDate().toString()) || StringUtils.isBlank(materialDumpDO.getAmount())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialDumpDO.setEnter(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialDumpService.insert(materialDumpDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody MaterialDumpDO materialDumpDO) {
        if (StringUtils.isBlank(materialDumpDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialDumpService.delete(materialDumpDO.getId());
    }

    @Override
    @PostMapping("updateMaterialDump")
    public ResultDO<Void> updateMaterialDump(@RequestBody MaterialDumpDO materialDumpDO) {
        if (StringUtils.isBlank(materialDumpDO.getId()) || StringUtils.isBlank(materialDumpDO.getAmount()) || StringUtils.isBlank(materialDumpDO.getDate().toString())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialDumpService.updateMaterialDump(materialDumpDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody MaterialDumpDO materialDumpDO, HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(materialDumpDO.getId())
                || StringUtils.isBlank(materialDumpDO.getCheckFlag()) || StringUtils.isBlank(materialDumpDO.getMaterialInventoryId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        materialDumpDO.setChecker(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return materialDumpService.updateCheckerById(materialDumpDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody MaterialDumpVO[] materialDumpVO) {
        if (null == materialDumpVO || 0 == materialDumpVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialDumpService.exportExcel(materialDumpVO);
    }

}
