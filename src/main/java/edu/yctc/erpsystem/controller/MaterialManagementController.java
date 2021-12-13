package edu.yctc.erpsystem.controller;

import javax.servlet.http.HttpSession;

/**
 * 材料管理页面接口
 *
 * @author lqp
 */
public interface MaterialManagementController {

    /**
     * 展示料品主信息页面
     *
     * @param httpSession session
     * @return 料品主信息页面
     */
    String showMaterialOwnerInformation(HttpSession httpSession);

    /**
     * 展示料品原始信息页面
     *
     * @param httpSession session
     * @return 料品原始信息页面
     */
    String showRawMaterialInformation(HttpSession httpSession);

    /**
     * 展示原材料采购页面
     *
     * @param httpSession session
     * @return 原材料采购页面
     */
    String showPurchaseOfRawMaterials(HttpSession httpSession);

    /**
     * 展示原材料待入库页面
     *
     * @param httpSession session
     * @return 原材料待入库页面
     */
    String showRawMaterialsToBeStored(HttpSession httpSession);

    /**
     * 展示原材料入库页面
     *
     * @param httpSession session
     * @return 原材料入库页面
     */
    String showWarehousingOfRawMaterials(HttpSession httpSession);

    /**
     * 展示材料库存页面
     *
     * @param httpSession session
     * @return 材料库存页面
     */
    String showMaterialRepertory(HttpSession httpSession);

    /**
     * 展示材料出库信息页面
     *
     * @param httpSession session
     * @return 材料出库信息页面
     */
    String showMaterialOutgoingInformation(HttpSession httpSession);

    /**
     * 展示材料出库明细页面
     *
     * @param httpSession session
     * @return 材料出库明细页面
     */
    String showMaterialDeliveryDetails(HttpSession httpSession);

    /**
     * 展示原材料报废页面
     *
     * @param httpSession session
     * @return 原材料报废页面
     */
    String showScrapOfRawMaterials(HttpSession httpSession);

    /**
     * 展示手动出库页面
     *
     * @param httpSession session
     * @return 手动出库页面
     */
    String showManualOutbound(HttpSession httpSession);

    /**
     * 展示未完结排程页面
     *
     * @param httpSession session
     * @return 未完结排程页面
     */
    String showOpenSchedule(HttpSession httpSession);

    /**
     * 展示已完结排程页面
     *
     * @param httpSession session
     * @return 已完结排程页面
     */
    String showCompletedSchedule(HttpSession httpSession);

    /**
     * 展示材料盘点页面
     *
     * @param httpSession session
     * @return 材料盘点页面
     */
    String showMaterialInventory(HttpSession httpSession);

}
