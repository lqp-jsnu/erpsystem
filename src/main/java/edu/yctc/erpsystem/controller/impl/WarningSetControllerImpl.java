package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.WarningSetController;
import edu.yctc.erpsystem.session.SessionContentHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 预警设置页面实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/warning-set")
public class WarningSetControllerImpl implements WarningSetController {

    @Override
    @GetMapping("finished-product-quantity-setting")
    public String showFinishedProductQuantitySetting(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.FINISHED_PRODUCT_QUANTITY_SETTING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("raw-material-quantity-setting")
    public String showRawMaterialQuantitySetting(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.RAW_MATERIAL_QUANTITY_SETTING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("shelf-life-setting")
    public String showShelfLifeSetting(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.SHELF_LIFE_SETTING : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("material-shelf-life-setting")
    public String showMaterialShelfLifeSetting(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MATERIAL_SHELF_LIFE_SETTING : TemplatePath.NO_AUTHORIZATION;
    }

}
