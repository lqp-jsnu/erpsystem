package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialInfoDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInfoVO;

import java.util.Map;

/**
 * 料品主信息
 *
 * @author xcg
 */
public interface MaterialInfoInterService {

    /**
     * 获得所有料品原始信息
     *
     * @param params 过滤参数
     * @return 料品原始信息链表
     */
    ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfo(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的料品原始信息
     *
     * @param params 过滤参数
     * @return 料品原始信息链表
     */
    ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoByConditions(Map<String, Object> params);

    /**
     * 添加一条料品原始信息
     *
     * @param materialInfoDO 料品原始信息
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialInfoDO materialInfoDO);

    /**
     * 通过id修改delflag
     *
     * @param id 主键
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改unitPrice specialRequire remark
     *
     * @param materialInfoDO 料品原始信息实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialInfo(MaterialInfoDO materialInfoDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialInfoDO 原材料实体
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialInfoDO materialInfoDO);

    /**
     * 导出excel
     *
     * @param materialInfoVO 料品原始信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialInfoVO[] materialInfoVO);

    /**
     * 通过供应商搜索材料信息
     *
     * @param params 搜索信息
     * @return 材料信息链表
     */
    ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoBySearchSuppId(Map<String, Object> params);

}
