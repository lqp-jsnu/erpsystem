package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.MaterialExportDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportVO;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 材料出库信息数据接口
 *
 * @author wjd
 */
public interface MaterialExportRestController {

    /**
     * 获得所有材料出库信息
     *
     * @param params 过滤参数
     * @return 材料出库信息链表
     */
    ResultDO<PageUtils<MaterialExportVO>> getMaterialExport(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的材料出库信息
     *
     * @param params 过滤参数
     * @return 材料出库信息链表
     */
    ResultDO<PageUtils<MaterialExportVO>> getMaterialExportByConditions(Map<String, Object> params);

    /**
     * 删除记录
     *
     * @param materialExportDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(MaterialExportDO materialExportDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialExportDO 原材料报废实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialExportDO materialExportDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param materialExportVO 材料出库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialExportVO[] materialExportVO);

    /**
     * 导出DetailExcel
     *
     * @param materialExportVO 材料出库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportDetailExcel(List<MaterialExportVO> materialExportVO);

}
