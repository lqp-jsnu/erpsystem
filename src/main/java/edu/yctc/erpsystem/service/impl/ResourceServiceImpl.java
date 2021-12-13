package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.ResourceDAO;
import edu.yctc.erpsystem.dao.RoleResourceDAO;
import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.RoleResourceDO;
import edu.yctc.erpsystem.service.ResourceInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单资源实现
 *
 * @author lqp
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("resourceService")
public class ResourceServiceImpl implements ResourceInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ResourceDAO resourceDAO;

    @Resource
    private RoleResourceDAO roleResourceDAO;

    @Override
    public ResultDO<List<ResourceDO>> getMenuResourcesByUserId(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            List<ResourceDO> result = resourceDAO.getMenuResourcesByUserId(id);
            if (result == null) {
                logger.error("getMenuResourcesByUserId error, no such UserId, UserId={}", id);
                return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
        } catch (Exception e) {
            logger.error("getMenuResourcesByUserId exception, system error, id={}, e={}", id, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<List<ResourceDO>> getAllResourcesByUserId(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            List<ResourceDO> result = resourceDAO.getAllResourcesByUserId(id);
            if (result == null) {
                logger.error("getAllResourcesByUserId error, no such UserId, UserId={}", id);
                return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
        } catch (Exception e) {
            logger.error("getAllResourcesByUserId exception, system error, id={}, e={}", id, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<List<ResourceDO>> getAllResources() {
        try {
            List<ResourceDO> result = resourceDAO.getAllResources();
            if (result == null) {
                logger.error("getAllResources exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
        } catch (Exception e) {
            logger.error("getAllResources exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<Void> insert(ResourceDO resourceDO) {
        if (StringUtils.isBlank(resourceDO.getName()) || StringUtils.isBlank(resourceDO.getSyResourceTypeId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Resource", resourceDO.toString(), () -> {
            resourceDAO.insert(resourceDO);
            // 为管理员添加权限
            RoleResourceDO roleResourceDO = new RoleResourceDO();
            roleResourceDO.setSyResourceId(resourceDO.getId());
            roleResourceDO.setSyRoleId("0");
            roleResourceDAO.insert(roleResourceDO);
        });
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Resource", id, () -> resourceDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateResources(ResourceDO resourceDO) {
        if (StringUtils.isBlank(resourceDO.getId()) || StringUtils.isBlank(resourceDO.getName()) || StringUtils.isBlank(resourceDO.getSyResourceTypeId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateResources", resourceDO.toString(), () -> resourceDAO.updateResources(resourceDO));
    }

}
