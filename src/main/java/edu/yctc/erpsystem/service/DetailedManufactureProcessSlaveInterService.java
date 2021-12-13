package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.DetailedManufactureProcessSlaveVO;

import java.util.Map;

/**
 * 制造流程单详细
 *
 * @author smile
 */
public interface DetailedManufactureProcessSlaveInterService {

    /**
     * 获得所有制造流程单详细
     *
     * @param params 过滤参数
     * @return 制造流程单详细表
     */
    ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getDetailedManufactureProcessSlaves(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的制造流程单详细信息
     *
     * @param params 过滤参数
     * @return 制造流程单详细表
     */
    ResultDO<PageUtils<DetailedManufactureProcessSlaveVO>> getDetailedManufactureProcessSlaveByConditions(Map<String, Object> params);

    /**
     * 通过id对材料库存出库信息新增
     *
     * @param detailedManufactureProcessSlaveVO 制造流程单材料出库视图
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialExport(DetailedManufactureProcessSlaveVO detailedManufactureProcessSlaveVO);

    /**
     * 通过id进行生产
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> updateIsIntoMake(String id);

    /**
     * 导出excel
     *
     * @param detailedManufactureProcessSlaveVO 制造流程单详细信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(DetailedManufactureProcessSlaveVO[] detailedManufactureProcessSlaveVO);

    /**
     * 导出电阻模板
     *
     * @param manufactureProcessSlaveDO 制造流程单详细信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcelDianZu(ManufactureProcessSlaveDO manufactureProcessSlaveDO);

    /**
     * 导出弹簧模板
     *
     * @param manufactureProcessSlaveDO 制造流程单详细信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcelTangHuang(ManufactureProcessSlaveDO manufactureProcessSlaveDO);

}
