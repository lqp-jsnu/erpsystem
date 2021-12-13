package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportDetailVO;

import java.util.Map;

/**
 * 材料出库明细数据接口
 *
 * @author wjd
 */
public interface MaterialExportDetailRestController {

    /**
     * 获得所有材料出库明细信息
     *
     * @param params 过滤参数
     * @return 材料出库信息明细链表
     */
    ResultDO<PageUtils<MaterialExportDetailVO>> getMaterialExportDetail(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的材料出库明细信息
     *
     * @param params 过滤参数
     * @return 材料出库明细信息链表
     */
    ResultDO<PageUtils<MaterialExportDetailVO>> getMaterialExportDetailByConditions(Map<String, Object> params);

    /**
     * 获得所有查看信息
     *
     * @param params 过滤参数
     * @return 材料出库明细信息链表
     */
    ResultDO<PageUtils<MaterialExportDetailVO>> getView(Map<String, Object> params);

}
