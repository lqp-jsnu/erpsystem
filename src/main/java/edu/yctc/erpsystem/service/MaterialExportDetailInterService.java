package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialExportDetailDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExportDetailVO;
import edu.yctc.erpsystem.vo.MaterialExportVO;

import java.util.List;
import java.util.Map;

/**
 * 材料出库明细逻辑接口
 *
 * @author wjd
 */
public interface MaterialExportDetailInterService {

    /**
     * 获得所有材料出库明细信息
     *
     * @param params 过滤参数
     * @return 材料出库明细信息链表
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
