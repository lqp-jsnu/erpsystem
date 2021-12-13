package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.DbBackUpRestController;
import edu.yctc.erpsystem.entity.DbBackUpDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.DbBackUpInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 数据库备份数据接口实现
 *
 * @author lqp
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/db-back-up")
public class DbBackUpRestControllerImpl implements DbBackUpRestController {

    @Resource
    private DbBackUpInterService dbBackUpService;

    @Override
    @GetMapping("getDbBackUps")
    public ResultDO<PageUtils<DbBackUpDO>> getDbBackUps(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return dbBackUpService.getDbBackUps(params);
    }

    @Override
    @PostMapping("deleteByFileNames")
    public ResultDO<Void> deleteByFileNames(@RequestBody List<String> fileNames) {
        if (fileNames == null || fileNames.size() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return dbBackUpService.deleteByFileNames(fileNames);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<String> insert() {
        return dbBackUpService.insert();
    }

}
