package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.SystemMonitoringController;
import edu.yctc.erpsystem.session.SessionContentHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 系统监控页面实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/system-monitoring")
public class SystemMonitoringControllerImpl implements SystemMonitoringController {

    @Override
    @GetMapping("project-monitoring")
    public String showProjectMonitoring(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.PROJECT_MONITORING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("data-source-monitoring")
    public String showDataSourceMonitoring(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.DATA_SOURCE_MONITORING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("user-login-history-monitoring")
    public String showUserLoginHistoryMonitoring(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.USER_LOGIN_HISTORY_MONITORING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("db-back-up")
    public String showDbBackUp(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.DB_BACK_UP : TemplatePath.NO_AUTHORIZATION;
    }

}
