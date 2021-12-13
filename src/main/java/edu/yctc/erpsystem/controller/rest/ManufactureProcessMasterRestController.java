package edu.yctc.erpsystem.controller.rest;


import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManufactureProcessMasterVO;

import java.util.Map;

/**
 * 制造流程单逻辑接口
 *
 * @author smile
 */
public interface ManufactureProcessMasterRestController {

    /**
     * 获得所有制造流程单信息
     *
     * @param params 过滤参数
     * @return 制造流程单链表
     */
    ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMaster(Map<String, Object> params);

    /**
     * 通过条件过滤制造流程单
     *
     * @param params 过滤参数
     * @return 制造流程单链表
     */
    ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMasterByConditions(Map<String, Object> params);

    /**
     * 导出电阻模板
     *
     * @param manufactureProcessSlaveDO 制造流程单详细信息实体
     * @return 是否成功
     */
    ResultDO<Void> exportAllDianZuExcel(ManufactureProcessSlaveDO[] manufactureProcessSlaveDO);

    /**
     * 导出弹簧模板
     *
     * @param manufactureProcessSlaveDO 制造流程单详细信息实体
     * @return 是否成功
     */
    ResultDO<Void> exportAllTanHuangExcel(ManufactureProcessSlaveDO[] manufactureProcessSlaveDO);

}
