package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.OnlineRestController;
import edu.yctc.erpsystem.entity.OnlineDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.OnlineInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 用户登录历史接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/user-login-history-monitoring")
public class OnlineRestControllerImpl implements OnlineRestController {

    @Resource
    private OnlineInterService onlineService;

    @Override
    @GetMapping("getOnline")
    public ResultDO<PageUtils<OnlineDO>> getOnline(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return onlineService.getOnline(params);
    }

}
