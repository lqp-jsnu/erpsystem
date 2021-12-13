package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.constant.StaticPath;
import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.UserController;
import edu.yctc.erpsystem.entity.OnlineDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.service.OnlineInterService;
import edu.yctc.erpsystem.service.UserInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.IpUtils;
import edu.yctc.erpsystem.util.RedisUtil;
import edu.yctc.erpsystem.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户接口实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final static Logger logger = LoggerFactory.getLogger("controller");

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserInterService userService;

    @Resource
    private OnlineInterService onlineService;

    @Override
    @GetMapping("login")
    public String showLogin(HttpSession httpSession, HttpServletRequest request) {
        ResultDO<String> result = SessionContentHolder.judgeSignin(httpSession.getId());

        if (!result.isSuccess()) {
            return TemplatePath.USER_LOGIN;
        }

        return "redirect:/" + TemplatePath.MAIN_HTML;
    }

    @Override
    @PostMapping("login")
    public String login(@ModelAttribute UserVO userVO, HttpSession httpSession, HttpServletRequest request) {
        // 获取验证码
        String verifyCode = null;
        for (Cookie cookie : request.getCookies()) {
            if("verifyCode".equals(cookie.getName())) {
                verifyCode = cookie.getValue();
            }
        }

        // 验证码
        if (StringUtils.isEmpty(verifyCode) || !redisUtil.get(verifyCode).toString().toLowerCase().equals(userVO.getVerifyCode().toLowerCase())) {
            logger.error("sign in fail, parameter illegal, userVO={}, sessionId={}", userVO, httpSession.getId());
            return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_VERIFICATION_CODE_ERROR;
        }

        // 将 验证码id和验证码值 从缓存删除
        redisUtil.del(verifyCode);

        if (StringUtils.isEmpty(userVO.getLoginName()) || StringUtils.isEmpty(userVO.getPwd())) {
            logger.error("sign in fail, parameter illegal, userVO={}, sessionId={}", userVO, httpSession.getId());
            return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_PARAMETER_INVALID;
        }

        // 获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getLoginName(), DigestUtils.md5DigestAsHex(userVO.getPwd().getBytes()));
        try {
            subject.login(token);
            UserDO temp = userService.getUserByLoginName(userVO.getLoginName()).getModule();
            ResultDO<Void> result = SessionContentHolder.addSignInUserId(httpSession.getId(), temp.getId());
            if (!result.isSuccess()) {
                return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_PARAMETER_INVALID;
            }
            logger.info("sign in success, userVO={}, sessionId={}", userVO, httpSession.getId());

            String ip = IpUtils.getIpAddr(request);

            // 记录登陆信息
            OnlineDO onlineDO = new OnlineDO();
            onlineDO.setIp(ip);
            onlineDO.setLoginName(temp.getLoginName());
            onlineDO.setType("1");
            onlineService.insert(onlineDO);

            return "redirect:/" + TemplatePath.MAIN_HTML;
        } catch (UnknownAccountException e) {
            // 登录失败：用户名不存在
            logger.error("UnknownAccountException, no such user, userVO={}, sessionId={}", userVO, httpSession.getId(), e);
            return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_NO_SUCH_USER;
        } catch (IncorrectCredentialsException e) {
            // 登录失败：密码错误
            logger.error("shiro authentication user password error, userVO={}, sessionId={}", userVO, httpSession.getId(), e);
            return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_PASSWORD_ERROR;
        }
    }

    @Override
    @GetMapping("logout")
    public String logout(HttpSession httpSession, HttpServletRequest request) {
        if (StringUtils.isBlank(httpSession.getId())) {
            logger.error("session error, session={}", httpSession);
            return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_PARAMETER_INVALID;
        }

        ResultDO<UserDO> userResult;

        try {
            ResultDO<String> result = SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId());
            if (StringUtils.isBlank(result.getModule())) {
                logger.error("sign out error, userId is null, sessionId={}", httpSession.getId());
                return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_NO_SUCH_USER;
            }

            userResult = userService.getUserById(result.getModule());
            if (!userResult.isSuccess()) {
                logger.error("sign out fail, userId={}, session={}, resultDO={}", result.getModule(), httpSession, userResult);
                return "redirect:/" + StaticPath.ERROR + "?" + userResult.getMsg();
            }
        } catch (Exception e) {
            logger.error("sign out error, sessionId={}", httpSession.getId(), e);
            return "redirect:/" + StaticPath.ERROR + "?" + ResultCode.MSG_ERROR_SYSTEM_EXCEPTION;
        }
        logger.info("sign out success, sessionId={}, userId={}", httpSession.getId(), SessionContentHolder.getSignInUserIdBySessionId(httpSession.getId()));
        ResultDO<Void> result = SessionContentHolder.deleteSignInSessionBySessionId(httpSession.getId());
        if (!result.isSuccess()) {
            return "redirect:/" + StaticPath.ERROR + "?" + result.getMsg();
        }

        // 记录登出信息
        OnlineDO onlineDO = new OnlineDO();
        onlineDO.setIp(IpUtils.getIpAddr(request));
        onlineDO.setLoginName(userResult.getModule().getLoginName());
        onlineDO.setType("0");
        onlineService.insert(onlineDO);

        return "redirect:/" + TemplatePath.USER_LOGIN;
    }

}
