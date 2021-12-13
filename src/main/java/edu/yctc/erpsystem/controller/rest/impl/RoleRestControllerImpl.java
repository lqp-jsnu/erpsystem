package edu.yctc.erpsystem.controller.rest.impl;


import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.RoleRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.RoleDO;
import edu.yctc.erpsystem.entity.RoleResourceDO;
import edu.yctc.erpsystem.service.RoleInterService;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 角色信息接口实现
 *
 * @author xcg
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/role-management")
public class RoleRestControllerImpl implements RoleRestController {

    @Resource
    private RoleInterService roleService;

    @Override
    @GetMapping("getRole")
    public ResultDO<PageUtils<RoleDO>> getRole(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.getRole(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody RoleDO role) {
        if (StringUtils.isBlank(role.getName()) && StringUtils.isBlank(Integer.toString(role.getSeq()))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.insert(role);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody RoleDO roleDO) {
        if (StringUtils.isBlank(roleDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.delete(roleDO.getId());
    }

    @Override
    @PostMapping("updateRole")
    public ResultDO<Void> updateRole(@RequestBody RoleDO roleDO) {
        if (StringUtils.isBlank(roleDO.getId())||StringUtils.isBlank(roleDO.getName()) || StringUtils.isBlank(Integer.toString(roleDO.getSeq()))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.updateRole(roleDO);
    }

    @Override
    @PostMapping("insertRoleResource")
    public ResultDO<Void> insertRoleResource(@RequestBody RoleResourceDO roleResourceDO) {
        if (StringUtils.isBlank(roleResourceDO.getSyRoleId()) || StringUtils.isBlank(roleResourceDO.getSyResourceId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.insertRoleResource(roleResourceDO);
    }

    @Override
    @PostMapping("deleteByRoleId")
    public ResultDO<Void> deleteByRoleId(@RequestBody RoleResourceDO roleResourceDO) {
        if (StringUtils.isBlank(roleResourceDO.getSyRoleId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.deleteByRoleId(roleResourceDO.getSyRoleId());
    }

    @Override
    @PostMapping("getAllRole")
    public ResultDO<List<RoleDO>> getAllRole() {
        return roleService.getAllRole();
    }

    @Override
    @RequestMapping("getResourceIdByRoleId")
    public ResultDO<List<String>> getResourceIdByRoleId(@RequestBody  RoleResourceDO roleResourceDO) {
        if (StringUtils.isBlank(roleResourceDO.getSyRoleId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return roleService.getResourceIdByRoleId(roleResourceDO.getSyRoleId());
    }

}
