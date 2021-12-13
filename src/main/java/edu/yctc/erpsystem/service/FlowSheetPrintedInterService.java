package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.DetailedManufactureProcessSlaveVO;

import java.util.Map;

/**
 * 已列印流程单表
 *
 * @author smile
 */
public interface FlowSheetPrintedInterService {

    /**
     * 获得所有已列印流程单
     *
     * @param params 过滤参数
     * @return 制造流程单详细表
     */
    ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getFlowSheetPrinted(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的已列印流程单
     *
     * @param params 过滤参数
     * @return 制造流程单详细表
     */
    ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getFlowSheetPrintedByConditions(Map<String, Object> params);

}
