package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.MaterialInfoDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInfoVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 料品原始信息逻辑接口
 *
 * @author xcg
 */
public interface MaterialInfoRestController {

    /**
     * 获得料品原始信息
     *
     * @param params 过滤参数
     * @return 料品原始信息链表
     */
    ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfo(Map<String, Object> params);

    /**
     * 通过条件过滤后的料品原始信息
     *
     * @param params 过滤参数
     * @return 料品原始信息链表
     */
    ResultDO<PageUtils<MaterialInfoVO>> getMaterialInfoByConditions(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialInfoDO 料品原始信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialInfoDO materialInfoDO);

    /**
     * 删除
     *
     * @param materialInfoDO 料品原始信息id
     * @return 是否成功
     */
    ResultDO<Void> delete(MaterialInfoDO materialInfoDO);

    /**
     * 修改料品原始信息
     *
     * @param materialInfoDO 料品原始信息实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialInfo(MaterialInfoDO materialInfoDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialInfoDO 料品原始信息实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialInfoDO materialInfoDO, HttpSession httpSession);

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
