package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import edu.yctc.erpsystem.vo.ManufactureProcessMasterVO;

import java.util.Map;

/**
 * 制造流程单
 *
 * @author smile
 */
public interface ManufactureProcessMasterInterService {

    /**
     * 获得所有制造流程单信息
     *
     * @param params 过滤参数
     * @return 制造流程单主表
     */
    ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMaster(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的制造流程单信息
     *
     * @param params 过滤参数
     * @return 制造流程单主表
     */
    ResultDO<PageUtils<ManufactureProcessMasterVO>> getManufactureProcessMasterByConditions(Map<String, Object> params);

    /**
     * 导出电阻制造流程单excel
     *
     * @param manufactureProcessSlaveDO 制造流程单实体
     * @return 电阻制造流程单excel
     */
    ResultDO<Void> exportAllDianZuExcel(ManufactureProcessSlaveDO[] manufactureProcessSlaveDO);

    /**
     * 导出弹簧制造流程单excel
     *
     * @param manufactureProcessSlaveDO 制造流程单实体
     * @return 弹簧制造流程单excel
     */
    ResultDO<Void> exportAllTanHuangExcel(ManufactureProcessSlaveDO[] manufactureProcessSlaveDO);

}
