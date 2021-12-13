package edu.yctc.erpsystem.controller;

import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户控制层接口
 *
 * @author lqp
 */
public interface UserController {

    /**
     * 展示登录页面
     *
     * @param httpSession session
     * @param request 请求
     * @return 登陆界面
     */
    String showLogin(HttpSession httpSession, HttpServletRequest request);

    /**
     * 登录post请求
     *
     * @param userDO      用户DO
     * @param httpSession session
     * @param request 请求
     * @return 主页面
     */
    String login(UserVO userDO, HttpSession httpSession, HttpServletRequest request);

    /**
     * 注销请求
     *
     * @param httpSession session
     * @param request 请求
     * @return 登陆界面
     */
    String logout(HttpSession httpSession, HttpServletRequest request);

}
