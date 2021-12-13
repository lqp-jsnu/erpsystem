package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ManualExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManualExportVO;

import java.util.Map;

/**
 * 手动出库表
 * @author smile
 */
public interface ManualExportInterService {

    /**
     * 获得所有手动出库信息
     *
     * @param params 过滤参数
     * @return 手动出库信息链表
     */
    ResultDO<PageUtils<ManualExportVO>> getManualExport(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的手动出库信息
     *
     * @param params 过滤参数
     * @return 手动出库信息链表
     */
    ResultDO<PageUtils<ManualExportVO>> getManualExportByConditions(Map<String, Object> params);

    /**
     * 插入手动出库记录
     *
     * @param manualExportDO 手动出库信息
     * @return 是否成功
     */
    ResultDO<Void> insert(ManualExportDO manualExportDO);

    /**
     * 删除记录
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改amount exportDate remark
     *
     * @param manualExportDO 手动出库实体
     * @return 是否成功
     */
    ResultDO<Void> updateManualExport(ManualExportDO manualExportDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param manualExportDO 手动出库实体
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(ManualExportDO manualExportDO);

    /**
     * 导出excel
     *
     * @param manualExports 手动出库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ManualExportVO[] manualExports);
}
