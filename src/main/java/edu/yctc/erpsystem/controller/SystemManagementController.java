package edu.yctc.erpsystem.controller;

import javax.servlet.http.HttpSession;

/**
 * 系统管理页面接口
 *
 * @author xiaotao
 */
public interface SystemManagementController {

    /**
     * 展示资源管理页面
     *
     * @param httpSession session
     * @return 资源管理页面
     */
    String showResourceManagement(HttpSession httpSession);

    /**
     * 展示角色管理页面
     *
     * @param httpSession session
     * @return 角色管理页面
     */
    String showRoleManagement(HttpSession httpSession);

    /**
     * 展示部门管理页面
     *
     * @param httpSession session
     * @return 部门管理页面
     */
    String showDepartmentOfManagement(HttpSession httpSession);

    /**
     * 展示数据字典页面
     *
     * @param httpSession session
     * @return 数据字典页面
     */
    String showTheDataDictionary(HttpSession httpSession);

    /**
     * 展示用户管理页面
     *
     * @param httpSession session
     * @return 用户管理页面
     */
    String showUserManagement(HttpSession httpSession);

    /**
     * 展示发票抬头页面
     *
     * @param httpSession session
     * @return 发票抬头页面
     */
    String showTheInvoiceLookedUp(HttpSession httpSession);

}
