package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.ResourceRestController;
import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.ResourceInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.vo.TreeViewVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import static edu.yctc.erpsystem.util.TreeUtils.getTree;

/**
 * 资源数据接口实现
 *
 * @author lqp
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/resource-management")
public class ResourceRestControllerImpl implements ResourceRestController {

    @Resource
    private ResourceInterService resourceService;

    @Override
    @GetMapping("getTreeView")
    public ResultDO<List<TreeViewVO>> getTreeView(HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, getTree(resourceService.getMenuResourcesByUserId(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule()).getModule()));
    }

    @Override
    @PostMapping("getAllResources")
    public ResultDO<List<ResourceDO>> getAllResources() {
        return resourceService.getAllResources();
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody ResourceDO resourceDO) {
        if (StringUtils.isBlank(resourceDO.getName()) || StringUtils.isBlank(resourceDO.getSyResourceTypeId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return resourceService.insert(resourceDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody ResourceDO resourceDO) {
        if (StringUtils.isBlank(resourceDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return resourceService.delete(resourceDO.getId());
    }

    @Override
    @PostMapping("updateResources")
    public ResultDO<Void> updateResources(@RequestBody ResourceDO resourceDO) {
        if (StringUtils.isBlank(resourceDO.getId()) || StringUtils.isBlank(resourceDO.getName()) || StringUtils.isBlank(resourceDO.getSyResourceTypeId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return resourceService.updateResources(resourceDO);
    }

}
