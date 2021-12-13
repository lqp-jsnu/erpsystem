package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.ResourceDAO;
import edu.yctc.erpsystem.dao.RoleDAO;
import edu.yctc.erpsystem.dao.RoleResourceDAO;
import edu.yctc.erpsystem.dao.UserRoleDAO;
import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.RoleDO;
import edu.yctc.erpsystem.entity.RoleResourceDO;
import edu.yctc.erpsystem.service.RoleInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色逻辑接口实现
 *
 * @author xcg
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("roleService")
public class RoleServiceImpl implements RoleInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private RoleDAO roleDAO;

    @Resource
    private ResourceDAO resourceDAO;

    @Resource
    private RoleResourceDAO roleResourceDAO;

    @Resource
    private UserRoleDAO userRoleDAO;

    @Override
    public ResultDO<PageUtils<RoleDO>> getRole(Map<String, Object> params) {
       return CallbackUtils.getCallback("getRole",params.toString(), ()-> {
            List<RoleDO> result = roleDAO.getRole(params);
            if (result == null) {
                logger.error("getRole exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(roleDAO.count(params), result));
        });

    }

    @Override
    public ResultDO<Void> insert(RoleDO roleDO) {
        if ( StringUtils.isBlank(roleDO.getName()) || StringUtils.isBlank(Integer.toString(roleDO.getSeq()))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Role", roleDO.toString(), () -> roleDAO.insert(roleDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Role", id, () -> {
            userRoleDAO.deleteByRoleId(id);
            roleResourceDAO.deleteByRoleId(id);
            roleDAO.delete(id);
        });
    }

    @Override
    public ResultDO<Void> updateRole(RoleDO roleDO) {
        if (StringUtils.isBlank(roleDO.getId()) || StringUtils.isBlank(roleDO.getName()) || StringUtils.isBlank(Integer.toString(roleDO.getSeq()))) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateSupplier", roleDO.toString(), () -> roleDAO.updateRole(roleDO));
    }

    @Override
    public ResultDO<List<RoleDO>> getAllRole() {
        try {
            Map<String, Object> params = new HashMap<>(0);
            List<RoleDO> result = roleDAO.getRole(params);
            if (result == null) {
                logger.error("getAllRole exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
        } catch (Exception e) {
            logger.error("getAllRole exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<List<String>> getResourceIdByRoleId(String id) {
        try {
            List<String> result = new ArrayList<>();

            List<RoleResourceDO> roleResourceList =  roleResourceDAO.getResourceIdByRoleId(id);
            if (0 == roleResourceList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
            }

            List<ResourceDO> resourceList = resourceDAO.getResourceByIds(roleResourceList.stream().map(RoleResourceDO::getSyResourceId).collect(Collectors.toList()));
            result = resourceList.stream().filter(item -> "1".equals(item.getSyResourceTypeId())).map(ResourceDO::getId).collect(Collectors.toList());
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
        } catch (Exception e) {
            logger.error("getResourceIdByRoleId exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<Void> insertRoleResource(RoleResourceDO roleResourceDO) {
        if ( StringUtils.isBlank(roleResourceDO.getSyResourceId()) || StringUtils.isBlank(roleResourceDO.getSyRoleId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("RoleResource", roleResourceDO.toString(), () -> roleResourceDAO.insert(roleResourceDO));
    }

    @Override
    public ResultDO<Void> deleteByRoleId(String id) {
        return CallbackUtils.deleteCallback("RoleResource", id, () -> roleResourceDAO.deleteByRoleId(id));
    }

}
