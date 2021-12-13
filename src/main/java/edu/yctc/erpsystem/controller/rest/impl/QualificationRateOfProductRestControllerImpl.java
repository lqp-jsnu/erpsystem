package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.QualificationRateOfProductRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.QualificationRateOfProductInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualificationRateOfProductVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 成品合格率数据接口实现
 *
 * @author zzy
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/qualified-rate-of-finished-product")
public class QualificationRateOfProductRestControllerImpl implements QualificationRateOfProductRestController {

    @Resource
    private QualificationRateOfProductInterService qualificationRateOfProductService;

    @Override
    @GetMapping("getQualificationRateOfProduct")
    public ResultDO<PageUtils<QualificationRateOfProductVO>> getQualificationRateOfProduct(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualificationRateOfProductService.getQualificationRateOfProduct(params);
    }

    @Override
    @GetMapping("getQualificationRateOfProductByConditions")
    public  ResultDO<PageUtils<QualificationRateOfProductVO>> getQualificationRateOfProductByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualificationRateOfProductService.getQualificationRateOfProductByConditions(params);
    }

    @Override
    @PostMapping("exportExcel")
    public ResultDO<Void> exportExcel(@RequestBody QualificationRateOfProductVO[] qualificationRateOfProductVO) {
        if (null == qualificationRateOfProductVO || 0 == qualificationRateOfProductVO.length) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return qualificationRateOfProductService.exportExcel(qualificationRateOfProductVO);
    }
}
