package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.TemplateRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.TemplateDO;
import edu.yctc.erpsystem.service.TemplateInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.TemplateVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 模板管理接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/template-management")
public class TemplateRestControllerImpl implements TemplateRestController {

    @Resource
    private TemplateInterService templateService;

    @Override
    @GetMapping("getTemplate")
    public ResultDO<PageUtils<TemplateVO>> getTemplate(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return templateService.getTemplate(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getName()) || StringUtils.isBlank(templateDO.getDictId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return templateService.insert(templateDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return templateService.delete(templateDO.getId());
    }

    @Override
    @PostMapping("updateTemplate")
    public ResultDO<Void> updateTemplate(@RequestBody TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getId()) || StringUtils.isBlank(templateDO.getDictId()) || StringUtils.isBlank(templateDO.getRemark())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return templateService.updateTemplate(templateDO);
    }

    @Override
    @PostMapping("updateUrl")
    public ResultDO<Void> updateUrl(@RequestBody TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getId()) || StringUtils.isBlank(templateDO.getName()) || StringUtils.isBlank(templateDO.getUrl())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return templateService.updateUrl(templateDO);
    }

}
