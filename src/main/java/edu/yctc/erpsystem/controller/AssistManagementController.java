package edu.yctc.erpsystem.controller;

import javax.servlet.http.HttpSession;

/**
 * 辅助管理页面接口
 *
 * @author lqp
 */
public interface AssistManagementController {

    /**
     * 展示模板管理页面
     *
     * @param httpSession session
     * @return 模板管理页面
     */
    String showTemplateManagement(HttpSession httpSession);

    /**
     * 展示仓库管理页面
     *
     * @param httpSession session
     * @return 仓库管理页面
     */
    String showWarehouseManagement(HttpSession httpSession);

    /**
     * 展示客户管理页面
     *
     * @param httpSession session
     * @return 客户管理页面
     */
    String showCustomerManagement(HttpSession httpSession);

    /**
     * 展示供应商管理页面
     *
     * @param httpSession session
     * @return 供应商管理页面
     */
    String showSupplierManagement(HttpSession httpSession);

}
