package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.UserRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.entity.UserOrganizationDO;
import edu.yctc.erpsystem.entity.UserRoleDO;
import edu.yctc.erpsystem.service.UserInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.util.RedisUtil;
import edu.yctc.erpsystem.util.SimpleCharVerifyCodeGenerate;
import edu.yctc.erpsystem.vo.UserVO;
import edu.yctc.erpsystem.vo.VerifyCodeVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static edu.yctc.erpsystem.util.ParamUtils.validation;

/**
 * 用户信息数据接口实现
 *
 * @author lqp
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/user")
public class UserRestControllerImpl implements UserRestController {

    private final static Logger logger = LoggerFactory.getLogger("controller");

    @Resource
    private UserInterService userService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @PostMapping("lock")
    public ResultDO<Void> lock(HttpSession httpSession) {
        if (StringUtils.isBlank(httpSession.getId())) {
            logger.error("lock error, session={}", httpSession);
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            ResultDO<String> result = SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId());
            if (StringUtils.isBlank(result.getModule())) {
                logger.error("lock error, userId is null, sessionId={}", httpSession.getId());
                return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
            }

            ResultDO<UserDO> userResult = userService.getUserById(result.getModule());
            if (!userResult.isSuccess()) {
                logger.error("lock fail, userId={}, session={}, resultDO={}", result.getModule(), httpSession, userResult);
                return new ResultDO<>(false, userResult.getCode(), userResult.getMsg(), null);
            }
        } catch (Exception e) {
            logger.error("lock error, sessionId={}", httpSession.getId(), e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
        logger.info("lock success, sessionId={}, userId={}", httpSession.getId(), SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()));
        return SessionContentHolder.deleteSignInSessionBySessionId(httpSession.getId());
    }

    @Override
    @PostMapping("unlock")
    public ResultDO<Void> unlock(@RequestBody UserDO userDO, HttpSession httpSession) {
        if (StringUtils.isEmpty(userDO.getLoginName()) || StringUtils.isEmpty(userDO.getPwd())) {
            logger.error("unlock fail, parameter illegal, loginName={}, pwd={}", userDO.getLoginName(), userDO.getPwd());
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        // 获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userDO.getLoginName(), DigestUtils.md5DigestAsHex(userDO.getPwd().getBytes()));
        try {
            subject.login(token);
            ResultDO<Void> result = SessionContentHolder.addSignInUserId(httpSession.getId(), userService.getUserByLoginName(userDO.getLoginName()).getModule().getId());
            if (result.isSuccess()) {
                logger.info("unlock success, loginName={}, sessionId={}", userDO.getLoginName(), httpSession.getId());
            }
            return result;
        } catch (UnknownAccountException e) {
            // 解锁失败：用户名不存在
            logger.error("UnknownAccountException, no such user, loginName={}, sessionId={}", userDO.getLoginName(), httpSession.getId(), e);
            return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
        } catch (IncorrectCredentialsException e) {
            // 解锁失败：密码错误
            logger.error("shiro authentication user password error, loginName={}, sessionId={}", userDO.getLoginName(), httpSession.getId(), e);
            return new ResultDO<>(false, ResultCode.PASSWORD_ERROR, ResultCode.MSG_PASSWORD_ERROR, null);
        }
    }

    @Override
    @PostMapping("changePassword")
    public ResultDO<Void> changePassword(@RequestBody UserDO userDO, HttpSession httpSession) {
        if (StringUtils.isEmpty(userDO.getPwd())) {
            logger.error("changePassword fail, parameter illegal, pwd={}", userDO.getPwd());
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        userDO.setId(SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()).getModule());
        return userService.changePassword(userDO);
    }

    @Override
    @GetMapping("getUsers")
    public ResultDO<PageUtils<UserVO>> getUsers(@RequestParam Map<String, Object> params) {
        if (!validation(params)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.getUsers(params);
    }

    @Override
    @PostMapping("insert")
    public ResultDO<Void> insert(@RequestBody UserDO userDO) {
        if (StringUtils.isBlank(userDO.getLoginName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.insert(userDO);
    }

    @Override
    @PostMapping("delete")
    public ResultDO<Void> delete(@RequestBody UserDO userDO) {
        if (StringUtils.isBlank(userDO.getId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.delete(userDO.getId());
    }

    @Override
    @PostMapping("updatePhotoById")
    public ResultDO<Void> updatePhotoById(@RequestBody UserDO userDO) {
        if (StringUtils.isBlank(userDO.getId()) || StringUtils.isBlank(userDO.getPhoto())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.updatePhotoById(userDO);
    }

    @Override
    @PostMapping("updateUserInfo")
    public ResultDO<Void> updateUserInfo(@RequestBody UserDO userDO) {
        if (StringUtils.isBlank(userDO.getId()) || StringUtils.isBlank(userDO.getLoginName())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.updateUserInfo(userDO);
    }

    @Override
    @PostMapping("insertUserRole")
    public ResultDO<Void> insertUserRole(@RequestBody List<UserRoleDO> userRoleDO) {
        for (UserRoleDO temp : userRoleDO) {
            if (StringUtils.isBlank(temp.getSyRoleId()) && StringUtils.isBlank(temp.getSyUserId())) {
                return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
            }
        }

        return userService.insertUserRole(userRoleDO);
    }

    @Override
    @PostMapping("initUserRole")
    public ResultDO<Void> initUserRole(@RequestBody UserRoleDO userRoleDO) {
        if (StringUtils.isBlank(userRoleDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.initUserRole(userRoleDO);
    }

    @Override
    @PostMapping("insertUserOrganization")
    public ResultDO<Void> insertUserOrganization(@RequestBody List<UserOrganizationDO> userOrganizationDO) {
        for (UserOrganizationDO temp : userOrganizationDO) {
            if (StringUtils.isBlank(temp.getSyOrganizationId()) && StringUtils.isBlank(temp.getSyUserId())) {
                return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
            }
        }

        return userService.insertUserOrganization(userOrganizationDO);
    }

    @Override
    @PostMapping("initUserOrganization")
    public ResultDO<Void> initUserOrganization(@RequestBody UserOrganizationDO userOrganizationDO) {
        if (StringUtils.isBlank(userOrganizationDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.initUserOrganization(userOrganizationDO);
    }

    @Override
    @PostMapping("getRoleIdByUserId")
    public ResultDO<List<UserRoleDO>> getRoleIdByUserId(@RequestBody UserRoleDO userRoleDO) {
        if (StringUtils.isBlank(userRoleDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.getRoleIdByUserId(userRoleDO.getSyUserId());
    }

    @Override
    @PostMapping("getOrganizationIdByUserId")
    public ResultDO<List<UserOrganizationDO>> getOrganizationIdByUserId(@RequestBody UserOrganizationDO userOrganizationDO) {
        if (StringUtils.isBlank(userOrganizationDO.getSyUserId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return userService.getOrganizationIdByUserId(userOrganizationDO.getSyUserId());
    }

    @Override
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletResponse response) {
        try {
            VerifyCodeVO verifyCode = SimpleCharVerifyCodeGenerate.generate(84, 38);

            // 将 验证码id和验证码值 写入缓存
            String uuid = UUID.randomUUID().toString();

            redisUtil.set(uuid, verifyCode.getCode(), 60);

            Cookie cookie = new Cookie("verifyCode", uuid);

            response.addCookie(cookie);

            // 设置响应内容类型
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.error("verifyCode error, IOException={}, response={}", e, response);
        }
    }

}
