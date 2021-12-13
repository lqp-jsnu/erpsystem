package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.OrganizationRestController;
import edu.yctc.erpsystem.entity.OrganizationDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.OrganizationInterService;
import edu.yctc.erpsystem.vo.TreeViewVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static edu.yctc.erpsystem.util.TreeUtils.getOrganizationTree;

/**
 * 部门数据接口实现
 *
 * @author xcg
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/department-of-management")
public class OrganizationRestControllerImpl implements OrganizationRestController {

    @Resource
    private OrganizationInterService organizationService;

    @Override
    @GetMapping("getTreeView")
    public ResultDO<List<TreeViewVO>> getTreeView() {
        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, getOrganizationTree(organizationService.getAllOrganization().getModule()));
    }

    @Override
    @PostMapping("getAllOrganization")
    public ResultDO<List<OrganizationDO>> getAllOrganization() {
        return organizationService.getAllOrganization();
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody OrganizationDO organizationDO) {
        if (StringUtils.isBlank(organizationDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return organizationService.insert(organizationDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody OrganizationDO organizationDO) {
        if (StringUtils.isBlank(organizationDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return organizationService.delete(organizationDO.getId());
    }

    @Override
    @PostMapping("updateOrganization")
    public ResultDO<Void> updateOrganization(@RequestBody OrganizationDO organizationDO) {
        if (StringUtils.isBlank(organizationDO.getId()) || StringUtils.isBlank(organizationDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return organizationService.updateOrganization(organizationDO);
    }

}
