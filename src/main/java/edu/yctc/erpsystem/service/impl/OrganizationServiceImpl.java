package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.OrganizationDAO;
import edu.yctc.erpsystem.dao.UserOrganizationDAO;
import edu.yctc.erpsystem.entity.OrganizationDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.OrganizationInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门管理接口实现
 *
 * @author xcg
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private OrganizationDAO organizationDAO;

    @Resource
    private UserOrganizationDAO userOrganizationDAO;

    @Override
    public ResultDO<List<OrganizationDO>> getAllOrganization() {
        try {
            List<OrganizationDO> result = organizationDAO.getAllOrganization();
            if (result == null) {
                logger.error("getAllOrganization exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
        } catch (Exception e) {
            logger.error("getAllOrganization exception, system error, e={}", e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<Void> insert(OrganizationDO organizationDO) {
        if (StringUtils.isBlank(organizationDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Organization", organizationDO.toString(), () -> organizationDAO.insert(organizationDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Organization", id, () -> {
            userOrganizationDAO.deleteUserOrganizationByOrganizationId(id);
            organizationDAO.delete(id);
        });
    }

    @Override
    public ResultDO<Void> updateOrganization(OrganizationDO organizationDO) {
        if (StringUtils.isBlank(organizationDO.getId()) || StringUtils.isBlank(organizationDO.getName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateOrganization", organizationDO.toString(), () -> organizationDAO.updateOrganization(organizationDO));
    }

}
