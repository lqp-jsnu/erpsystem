package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ManualExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManualExportVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 手动出库表
 *
 * @author smile
 */
public interface ManualExportRestController {

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
     * 增加手动出库信息
     *
     * @param manualExportDO 手动出库实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(ManualExportDO manualExportDO, HttpSession httpSession);

    /**
     * 删除记录
     *
     * @param manualExport 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(ManualExportDO manualExport);

    /**
     * 通过id修改dumpAmount dumpDate dumpReason
     *
     * @param manualExportDO 手动出库实体
     * @return 是否成功
     */
    ResultDO<Void> updateManualExport(ManualExportDO manualExportDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param manualExportDO 手动出库实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(ManualExportDO manualExportDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param manualExportVO 手动出库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ManualExportVO[] manualExportVO);

}
