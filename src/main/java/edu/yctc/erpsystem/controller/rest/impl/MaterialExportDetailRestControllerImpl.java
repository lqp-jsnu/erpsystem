package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MaterialExportDetailRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialExportDetailInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportDetailVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 材料出库明细数据接口实现
 *
 * @author wjd
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-delivery-details")
public class MaterialExportDetailRestControllerImpl implements MaterialExportDetailRestController {

    @Resource
    private MaterialExportDetailInterService materialExportDetailService;

    @Override
    @GetMapping("getMaterialExportDetail")
    public ResultDO<PageUtils<MaterialExportDetailVO>> getMaterialExportDetail(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportDetailService.getMaterialExportDetail(params);
    }

    @Override
    @GetMapping("getMaterialExportDetailByConditions")
    public ResultDO<PageUtils<MaterialExportDetailVO>> getMaterialExportDetailByConditions(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportDetailService.getMaterialExportDetailByConditions(params);
    }

    @Override
    @GetMapping("getView")
    public ResultDO<PageUtils<MaterialExportDetailVO>> getView(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return materialExportDetailService.getView(params);
    }

}
