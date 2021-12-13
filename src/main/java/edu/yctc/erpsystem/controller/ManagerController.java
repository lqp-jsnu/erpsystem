package edu.yctc.erpsystem.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 主界面控制接口
 *
 * @author lqp
 */
public interface ManagerController {

    /**
     * 展示未授权页面
     *
     * @return 未授权页面
     */
    String showNoAuthorization();

    /**
     * 展示主页面
     *
     * @param model 提供用户名
     * @param httpSession session
     * @param request 请求
     * @return 主页面
     */
    String showMain(Model model, HttpSession httpSession, HttpServletRequest request);

    /**
     * 展示主页面表格
     *
     * @param httpSession session
     * @return 未授权页面
     */
    String showMainTable(HttpSession httpSession);

}
