package edu.yctc.erpsystem.controller.impl;

import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.controller.MaterialManagementController;
import edu.yctc.erpsystem.session.SessionContentHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 材料管理页面实现
 *
 * @author lqp
 */
@Controller
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/material-management")
public class MaterialManagementControllerImpl implements MaterialManagementController {

    @Override
    @GetMapping("material-owner-information")
    public String showMaterialOwnerInformation(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MATERIAL_OWNER_INFORMATION : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("raw-material-information")
    public String showRawMaterialInformation(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.ROW_MATERIAL_INFORMATION : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("purchase-of-raw-materials")
    public String showPurchaseOfRawMaterials(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.PURCHASE_OF_RAW_MATERIALS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("raw-materials-to-be-stored")
    public String showRawMaterialsToBeStored(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.RAW_MATERIALS_TO_BE_STORED : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("warehousing-of-raw-materials")
    public String showWarehousingOfRawMaterials(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.WAREHOUSING_OF_RAW_MATERIALS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("material-repertory")
    public String showMaterialRepertory(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MATERIAL_REPERTORY : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("material-outgoing-information")
    public String showMaterialOutgoingInformation(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MATERIAL_OUTGOING_INFORMATION : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("material-delivery-details")
    public String showMaterialDeliveryDetails(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MATERIAL_DELIVERY_DETAILS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("scrap-of-raw-materials")
    public String showScrapOfRawMaterials(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.SCRAP_OF_RAW_MATERIALS : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("manual-outbound")
    public String showManualOutbound(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MANUAL_OUTBOUND : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("open-schedule")
    public String showOpenSchedule(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.OPEN_SCHEDULE : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("completed-schedule")
    public String showCompletedSchedule(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.COMPLETED_SCHEDULE : TemplatePath.NO_AUTHORIZATION;
    }

    @Override
    @GetMapping("material-inventory")
    public String showMaterialInventory(HttpSession httpSession) {
        return SessionContentHolder.judgeSignin(httpSession.getId()).isSuccess() ? TemplatePath.MATERIAL_INVENTORY : TemplatePath.NO_AUTHORIZATION;
    }

}
