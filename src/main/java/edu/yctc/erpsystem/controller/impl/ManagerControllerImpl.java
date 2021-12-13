package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.ManagerController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.UserInterService;
import edu.yctc.erpsystem.session.SessionContentHolder;
import edu.yctc.erpsystem.util.IpUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户控制层实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/admin")
public class ManagerControllerImpl implements ManagerController {

    @Resource
    private UserInterService userService;

    @Override
    @GetMapping("no-authorization")
    public String showNoAuthorization() {
        return "/" + TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("main")
    public String showMain(Model model, HttpSession httpSession, HttpServletRequest request) {
        ResultDO<String> result = SessionContentHolder.judgeSignin(httpSession.getId());

        if (!result.isSuccess()) {
            return TemplatePath.NO_AUTHORIZATION;
        }
        model.addAttribute("userDO", userService.getUserById(result.getModule()).getModule());
        model.addAttribute("ip", IpUtils.getIpAddr(request));

        return TemplatePath.MAIN_HTML;
    }

    @Override
    @GetMapping("main-table")
    public String showMainTable(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MAIN_TABLE_HTML : TemplatePath.NO_AUTHORIZATION;
    }

}
