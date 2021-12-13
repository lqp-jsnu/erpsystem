package edu.yctc.erpsystem.controller.rest.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.controller.rest.MainWarningRestController;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.MaterialInventoryInterService;
import edu.yctc.erpsystem.service.ProductInventoryInterService;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页预警接口实现
 *
 * @author qiang
 */
@RestController
@ComponentScan({"edu.yctc.erpsystem.service"})
@RequestMapping("/main-table")
public class MainWarningRestControllerImpl implements MainWarningRestController {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private ProductInventoryInterService productInventoryService;

    @Resource
    private MaterialInventoryInterService materialInventoryService;

    @Override
    @GetMapping("getMainProductDateWarning")
    public ResultDO<PageUtils<MainDateWarningVO>> getMainProductDateWarning() {
        Map<String, Object> params = new HashMap<>(0);
        List<MainDateWarningVO> mainProductDateWarningList = new ArrayList<>();
        // 获得所有成品保质期预警信息
        List<MainProductExpiryDateVO> mainProductExpiryDateList = productInventoryService.getMainProductExpiryDate(params).getModule().getRows();
        if (mainProductExpiryDateList == null) {
            logger.error("prodInventoryService exception, db error");
            return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
        }
        // 如果没有记录直接返回
        if (mainProductExpiryDateList.size() == 0) {
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, mainProductDateWarningList));
        }
        for (MainProductExpiryDateVO tempProductExpiryDate : mainProductExpiryDateList) {
            if (!"未设置预警值".equals(tempProductExpiryDate.getResult()) && tempProductExpiryDate.getLeaveDate() <= 7) {
                MainDateWarningVO tempMainProductDateWarning = new MainDateWarningVO();

                tempMainProductDateWarning.setItemName(tempProductExpiryDate.getItemName());
                tempMainProductDateWarning.setId(tempProductExpiryDate.getId());
                tempMainProductDateWarning.setSpec(tempProductExpiryDate.getSpec());
                tempMainProductDateWarning.setDate(tempProductExpiryDate.getDate());
                tempMainProductDateWarning.setLeaveDate(tempProductExpiryDate.getLeaveDate());

                mainProductDateWarningList.add(tempMainProductDateWarning);
            }
        }

        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(mainProductDateWarningList.size(), mainProductDateWarningList));
    }

    @Override
    @GetMapping("getMainProductNumberWarning")
    public ResultDO<PageUtils<MainNubWarningVO>> getMainProductNumberWarning() {
        Map<String, Object> params = new HashMap<>(0);
        List<MainNubWarningVO> mainNubWarningList = new ArrayList<>();
        // 获得所有成品保质期预警信息
        List<MainProductNumberVO> mainProductNumberList = productInventoryService.getMainProductNumber(params).getModule().getRows();
        if (mainProductNumberList == null) {
            logger.error("getMainProductNumber exception, db error");
            return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
        }
        // 如果没有记录直接返回
        if (mainProductNumberList.size() == 0) {
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, mainNubWarningList));
        }
        for (MainProductNumberVO tempProductNumber : mainProductNumberList) {
            if (!"未设置预警值".equals(tempProductNumber.getResult()) && "低于预警值".equals(tempProductNumber.getResult())) {
                MainNubWarningVO tempMainProductNubWarning = new MainNubWarningVO();

                tempMainProductNubWarning.setId(tempProductNumber.getId());
                tempMainProductNubWarning.setItemName(tempProductNumber.getItemName());
                tempMainProductNubWarning.setSpec(tempProductNumber.getSpec());
                tempMainProductNubWarning.setResult(tempProductNumber.getResult());

                mainNubWarningList.add(tempMainProductNubWarning);
            }
        }

        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(mainNubWarningList.size(), mainNubWarningList));
    }

    @Override
    @GetMapping("getMainMaterialDateWarning")
    public ResultDO<PageUtils<MainDateWarningVO>> getMainMaterialDateWarning() {
        Map<String, Object> params = new HashMap<>(0);
        List<MainDateWarningVO> mainMaterialDateWarningList = new ArrayList<>();
        // 获得所有原材料保质期预警信息
        List<MainMaterialExpiryDateVO> mainMaterialExpiryDateList = materialInventoryService.getMainMaterialExpiryDate(params).getModule().getRows();
        if (mainMaterialExpiryDateList == null) {
            logger.error("getMainMaterialExpiryDate exception, db error");
            return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
        }
        // 如果没有记录直接返回
        if (mainMaterialExpiryDateList.size() == 0) {
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, mainMaterialDateWarningList));
        }
        for (MainMaterialExpiryDateVO tempMainMaterialExpiryDate : mainMaterialExpiryDateList) {
            if (!"未设置预警值".equals(tempMainMaterialExpiryDate.getResult()) && tempMainMaterialExpiryDate.getLeaveDate() <= 7) {
                MainDateWarningVO tempMainMaterialDateWarning = new MainDateWarningVO();

                tempMainMaterialDateWarning.setId(tempMainMaterialExpiryDate.getId());
                tempMainMaterialDateWarning.setItemName(tempMainMaterialExpiryDate.getItemName());
                tempMainMaterialDateWarning.setSpec(tempMainMaterialExpiryDate.getSpec());
                tempMainMaterialDateWarning.setDate(tempMainMaterialExpiryDate.getDate());
                tempMainMaterialDateWarning.setLeaveDate(tempMainMaterialExpiryDate.getLeaveDate());

                mainMaterialDateWarningList.add(tempMainMaterialDateWarning);
            }
        }

        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(mainMaterialDateWarningList.size(), mainMaterialDateWarningList));
    }

    @Override
    @GetMapping("getMainMaterialNumberWarning")
    public ResultDO<PageUtils<MainNubWarningVO>> getMainMaterialNumberWarning() {
        Map<String, Object> params = new HashMap<>(0);
        List<MainNubWarningVO> mainMaterialNubWarningList = new ArrayList<>();
        // 获得所有原材料保质期预警信息
        List<MainMaterialNumberVO> mainMaterialNumberList = materialInventoryService.getMainMaterialNumber(params).getModule().getRows();
        if (mainMaterialNumberList == null) {
            logger.error("getMainMaterialNumber exception, db error");
            return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
        }
        // 如果没有记录直接返回
        if (mainMaterialNumberList.size() == 0) {
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, mainMaterialNubWarningList));
        }
        for (MainMaterialNumberVO tempMaterialNumber : mainMaterialNumberList) {
            if (!"未设置预警值".equals(tempMaterialNumber.getResult()) && "低于预警值".equals(tempMaterialNumber.getResult())) {
                MainNubWarningVO tempMainProductNubWarning = new MainNubWarningVO();

                tempMainProductNubWarning.setItemName(tempMaterialNumber.getItemName());
                tempMainProductNubWarning.setId(tempMaterialNumber.getId());
                tempMainProductNubWarning.setSpec(tempMaterialNumber.getSpec());
                tempMainProductNubWarning.setResult(tempMaterialNumber.getResult());

                mainMaterialNubWarningList.add(tempMainProductNubWarning);
            }
        }

        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(mainMaterialNubWarningList.size(), mainMaterialNubWarningList));
    }

}
