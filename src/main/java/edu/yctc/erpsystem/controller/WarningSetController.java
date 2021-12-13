package edu.yctc.erpsystem.controller;

import javax.servlet.http.HttpSession;

/**
 * 预警设置页面接口
 *
 * @author lqp
 */
public interface WarningSetController {

    /**
     * 展示成品数量设置页面
     *
     * @param httpSession session
     * @return 成品数量设置页面
     */
    String showFinishedProductQuantitySetting(HttpSession httpSession);

    /**
     * 展示原材料数量设置页面
     *
     * @param httpSession session
     * @return 原材料数量设置页面
     */
    String showRawMaterialQuantitySetting(HttpSession httpSession);

    /**
     * 展示产品保质期设置页面
     *
     * @param httpSession session
     * @return 产品保质期设置页面
     */
    String showShelfLifeSetting(HttpSession httpSession);

    /**
     * 展示材料保质期设置页面
     *
     * @param httpSession session
     * @return 材料保质期设置页面
     */
    String showMaterialShelfLifeSetting(HttpSession httpSession);

}
