package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.AssistManagementController;
import edu.yctc.erpsystem.session.SessionContentHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 辅助管理页面实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/assist-management")
public class AssistManagementControllerImpl implements AssistManagementController {

    @Override
    @GetMapping("template-management")
    public String showTemplateManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.TEMPLATE_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("warehouse-management")
    public String showWarehouseManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.WAREHOUSE_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("customer-management")
    public String showCustomerManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.CUSTOMER_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("supplier-management")
    public String showSupplierManagement(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.SUPPLIER_MANAGEMENT : TemplatePath.NO_AUTHORIZATION;
    }

}
