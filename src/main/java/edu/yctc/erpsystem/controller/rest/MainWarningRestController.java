package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainDateWarningVO;
import edu.yctc.erpsystem.vo.MainNubWarningVO;

/**
 * 首页预警接口
 *
 * @author qiang
 */
public interface MainWarningRestController {

    /**
     * 获取首页成品保质期预警信息
     *
     * @return 成品保质期预警信息链表
     */
    ResultDO<PageUtils<MainDateWarningVO>> getMainProductDateWarning();

    /**
     * 获取首页成品数量预警信息
     *
     * @return 成品数量预警信息链表
     */
    ResultDO<PageUtils<MainNubWarningVO>> getMainProductNumberWarning();

    /**
     * 获取首页原材料保质期预警信息
     *
     * @return 原材料保质期预警信息链表
     */
    ResultDO<PageUtils<MainDateWarningVO>> getMainMaterialDateWarning();

    /**
     * 获取首页原材料数量预警信息
     *
     * @return 原材料数量预警信息链表
     */
    ResultDO<PageUtils<MainNubWarningVO>> getMainMaterialNumberWarning();

}
