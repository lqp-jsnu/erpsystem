package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.UserDAO;
import edu.yctc.erpsystem.dao.UserOrganizationDAO;
import edu.yctc.erpsystem.dao.UserRoleDAO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.entity.UserOrganizationDO;
import edu.yctc.erpsystem.entity.UserRoleDO;
import edu.yctc.erpsystem.service.UserInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.FileUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户逻辑实现
 *
 * @author lqp
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("userService")
public class UserServiceImpl implements UserInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private UserDAO userDAO;

    @Resource
    private UserRoleDAO userRoleDAO;

    @Resource
    private UserOrganizationDAO userOrganizationDAO;

    @Override
    public ResultDO<Void> login(UserDO userDO) {
        if (StringUtils.isBlank(userDO.getLoginName()) || StringUtils.isBlank(userDO.getPwd())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            // 通过用户名密码获取用户
            UserDO result = userDAO.login(userDO.getLoginName(), userDO.getPwd());
            if (result == null) {
                logger.error("login exception, db error, userDO=" + userDO);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            logger.error("login exception, system error userDO={}, e={}", userDO, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<UserDO> getUserByLoginName(String loginName) {
        if (StringUtils.isBlank(loginName)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            // 通过登录名找到UserDO
            UserDO userDO = userDAO.getUserByLoginName(loginName);
            if (userDO == null) {
                logger.error("getUserByLoginName error, no such loginName, loginName={}", loginName);
                return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userDO);
        } catch (Exception e) {
            logger.error("getUserByLoginName exception, system error, loginName={}, e={}", loginName, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<UserDO> getUserById(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            // 通过登录名找到UserDO
            Map<String, Object> params = new HashMap<>(1);
            params.put("id", id);
            List<UserDO> userDO = userDAO.getUser(params);
            if (userDO == null) {
                logger.error("getUserById error, no such loginName, id={}", id);
                return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userDO.get(0));
        } catch (Exception e) {
            logger.error("getUserById exception, system error, id={}, e={}", id, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<Void> changePassword(UserDO userDO) {
        if (StringUtils.isEmpty(userDO.getId()) || StringUtils.isEmpty(userDO.getPwd())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            userDO.setPwd(DigestUtils.md5DigestAsHex(userDO.getPwd().getBytes()));

            userDAO.updatePasswordById(userDO);
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            logger.error("changePassword exception, system error, id={}, pwd={}, e={}", userDO.getId(), userDO.getPwd(), e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<PageUtils<UserVO>> getUsers(Map<String, Object> params) {
        return CallbackUtils.getCallback("getUser", params.toString(), () -> {
            List<UserVO> userViewList = new ArrayList<>();

            // 得到所有用户信息
            List<UserDO> userList = userDAO.getUser(params);
            if (userList == null) {
                logger.error("getRole exception, db error");
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            for (UserDO tempUser : userList) {
                UserVO temp = new UserVO();
                temp.setName(tempUser.getName());
                temp.setCreateTime(tempUser.getCreateTime());
                temp.setUpdateTime(tempUser.getUpdateTime());
                temp.setSex(tempUser.getSex());
                temp.setId(tempUser.getId());
                temp.setPhoto(tempUser.getPhoto());
                temp.setLoginName(tempUser.getLoginName());
                userViewList.add(temp);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(userDAO.count(params), userViewList));
        });
    }

    @Override
    public ResultDO<Void> insert(UserDO userDO) {
        if (StringUtils.isBlank(userDO.getLoginName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("User", userDO.toString(), () -> {
            userDO.setPwd(DigestUtils.md5DigestAsHex("123456".getBytes()));
            userDO.setPhoto("default.jpg");
            if (null == userDO.getAge()) {
                userDO.setAge(0);
            }

            userDAO.insert(userDO);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("User", id, () -> {
            String photo = userDAO.getUserById(id).getPhoto();

            userRoleDAO.deleteByUserId(id);
            userOrganizationDAO.deleteUserOrganizationByUserId(id);
            userDAO.delete(id);

            if (!ConstantHolder.DEFAULT_IMAGE.equals(photo)) {
                FileUtils.deleteFile(ConstantHolder.IMG_SAVE_PATH, photo);
            }
        });
    }

    @Override
    public ResultDO<Void> updatePhotoById(UserDO userDO) {
        if (StringUtils.isBlank(userDO.getId()) || StringUtils.isBlank(userDO.getPhoto())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updatePhotoById", userDO.toString(), () -> userDAO.updatePhotoById(userDO));
    }

    @Override
    public ResultDO<Void> updateUserInfo(UserDO userDO) {
        if (StringUtils.isBlank(userDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateUserInfo", userDO.toString(), () -> userDAO.updateUserInfo(userDO));
    }

    @Override
    public ResultDO<Void> insertUserRole(List<UserRoleDO> userRoleDO) {
        for (UserRoleDO temp : userRoleDO) {
            if (StringUtils.isBlank(temp.getSyRoleId()) && StringUtils.isBlank(temp.getSyUserId())) {
                return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
            }
        }

        return CallbackUtils.insertCallback("insertUserRole", userRoleDO.toString(), () -> userRoleDAO.insertAll(userRoleDO));
    }

    @Override
    public ResultDO<Void> initUserRole(UserRoleDO userRoleDO) {
        return CallbackUtils.deleteCallback("initUserRole", userRoleDO.toString(), () -> userRoleDAO.deleteByUserId(userRoleDO.getSyUserId()));
    }

    @Override
    public ResultDO<Void> insertUserOrganization(List<UserOrganizationDO> userOrganizationDO) {
        for (UserOrganizationDO temp : userOrganizationDO) {
            if (StringUtils.isBlank(temp.getSyOrganizationId()) && StringUtils.isBlank(temp.getSyUserId())) {
                return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
            }
        }

        return CallbackUtils.insertCallback("insertUserOrganization", userOrganizationDO.toString(), ()-> userOrganizationDAO.insertAll(userOrganizationDO));
    }

    @Override
    public ResultDO<Void> initUserOrganization(UserOrganizationDO userOrganizationDO) {
        if (StringUtils.isBlank(userOrganizationDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.deleteCallback("initUserOrganization", userOrganizationDO.toString(), ()->userOrganizationDAO.deleteUserOrganizationByUserId(userOrganizationDO.getSyUserId()));
    }

    @Override
    public ResultDO<List<UserRoleDO>> getRoleIdByUserId(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userRoleDAO.getRoleIdByUserId(id));
        } catch (Exception e) {
            logger.error("getRoleIdByUserId exception, system error, id={}, e={}", id, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<List<UserOrganizationDO>> getOrganizationIdByUserId(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userOrganizationDAO.getOrganizationIdByUserId(id));
        } catch (Exception e) {
            logger.error("getOrganizationIdByUserId exception, system error, id={}, e={}", id, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

}