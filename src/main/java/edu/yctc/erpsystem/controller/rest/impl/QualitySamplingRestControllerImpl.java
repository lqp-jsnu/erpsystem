package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.QualitySamplingRestController;
import edu.yctc.erpsystem.entity.QualitySamplingDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.QualitySamplingInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualitySamplingVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 质量抽检数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/the-quality-of-sampling")
public class QualitySamplingRestControllerImpl implements QualitySamplingRestController {

    @Resource
    private QualitySamplingInterService qualitySamplingService;

    @Override
    @GetMapping("getQualitySampling")
    public ResultDO<PageUtils<QualitySamplingVO>> getQualitySampling(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualitySamplingService.getQualitySampling(params);
    }

    @Override
    @GetMapping("getQualitySamplingByConditions")
    public ResultDO<PageUtils<QualitySamplingVO>> getQualitySamplingByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualitySamplingService.getQualitySamplingByConditions(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody QualitySamplingDO qualitySamplingDO, HttpSession httpSession) {
        if( StringUtils.isBlank(httpSession.getId()) || StringUtils.isBlank(qualitySamplingDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(qualitySamplingDO.getPackageNumber()) || StringUtils.isBlank(qualitySamplingDO.getSampleNumber())
                || StringUtils.isBlank(qualitySamplingDO.getAql()) || StringUtils.isBlank(qualitySamplingDO.getPassNumber())
                || StringUtils.isBlank(qualitySamplingDO.getFailNumber()) || StringUtils.isBlank(qualitySamplingDO.getSampleResult())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        qualitySamplingDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return qualitySamplingService.insert(qualitySamplingDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody QualitySamplingDO qualitySamplingDO) {
        if (StringUtils.isBlank(qualitySamplingDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        return qualitySamplingService.delete(qualitySamplingDO.getId());
    }

    @Override
    @PostMapping("updateQualitySampling")
    public ResultDO<Void> updateQualitySampling(@RequestBody QualitySamplingDO qualitySamplingDO) {
        if (StringUtils.isBlank(qualitySamplingDO.getId()) || StringUtils.isBlank(qualitySamplingDO.getManufactureProcessSlaveId())
                || StringUtils.isBlank(qualitySamplingDO.getPackageNumber()) || StringUtils.isBlank(qualitySamplingDO.getCheckOperator())
                || StringUtils.isBlank(qualitySamplingDO.getPackageOperator()) || StringUtils.isBlank(qualitySamplingDO.getSampleNumber())
                || StringUtils.isBlank(qualitySamplingDO.getSampleResult()) || StringUtils.isBlank(qualitySamplingDO.getAql())
                || StringUtils.isBlank(qualitySamplingDO.getPassNumber()) || StringUtils.isBlank(qualitySamplingDO.getFailNumber())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualitySamplingService.updateQualitySampling(qualitySamplingDO);
    }

    @Override
    @PostMapping("updateCheckerById")
    public ResultDO<Void> updateCheckerById(@RequestBody QualitySamplingDO qualitySamplingDO, HttpSession httpSession) {
        if (StringUtils.isBlank(qualitySamplingDO.getId()) || StringUtils.isBlank(qualitySamplingDO.getCheckFlag())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        qualitySamplingDO.setUser(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return qualitySamplingService.updateCheckerById(qualitySamplingDO);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody QualitySamplingVO[] qualitySamplingVO) {
        if (null == qualitySamplingVO || 0 == qualitySamplingVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualitySamplingService.exportExcel(qualitySamplingVO);
    }

}
