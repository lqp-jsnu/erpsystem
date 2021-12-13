package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.SystemManagementController;
import edu.yctc.erpsystem.session.SessionContentHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 系统管理页面实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/system-management")
public class SystemManagementControllerImpl implements SystemManagementController {

    @Override
    @GetMapping("resource-management")
    public String showResourceManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.RESOURCE_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("role-management")
    public String showRoleManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ROLE_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("department-of-management")
    public String showDepartmentOfManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.DEPARTMENT_OF_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("the-data-dictionary")
    public String showTheDataDictionary(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.THE_DATA_DICTIONARY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("user-management")
    public String showUserManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.USER_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("the-invoice-looked-up")
    public String showTheInvoiceLookedUp(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.THE_INVOICE_LOOKED_UP : TemplatePath.NO_AUTHORIZATION;
    }

}
