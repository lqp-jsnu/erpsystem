package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.DictRestController;
import edu.yctc.erpsystem.entity.DictDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.DictInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 数据字典接口实现
 *
 * @author xcg
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/the-data-dictionary")
public class DictRestControllerImpl implements DictRestController {

    @Resource
    private DictInterService dictService;

    @Override
    @GetMapping("getDict")
    public ResultDO<PageUtils<DictDO>> getDict(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return dictService.getDict(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody DictDO dictDO) {
        if (StringUtils.isBlank(dictDO.getName()) || StringUtils.isBlank(dictDO.getValue()) || StringUtils.isBlank(dictDO.getType())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return dictService.insert(dictDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody DictDO dictDO) {
        if (StringUtils.isBlank(dictDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return dictService.delete(dictDO.getId());
    }

    @Override
    @PostMapping("updateDict")
    public ResultDO<Void> updateDict(@RequestBody DictDO dictDO) {
        if (StringUtils.isBlank(dictDO.getName()) || StringUtils.isBlank(dictDO.getValue()) || StringUtils.isBlank(dictDO.getType())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return dictService.updateDict(dictDO);
    }

}
