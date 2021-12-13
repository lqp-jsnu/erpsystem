package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.QualitySamplingDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualitySamplingVO;

import java.util.Map;

/**
 * 质量抽检逻辑接口
 *
 * @author zzy
 */
public interface QualitySamplingInterService {

    /**
     * 获得所有质量抽检信息
     *
     * @param params  过滤参数
     * @return  质量抽检信息链表
     */
    ResultDO<PageUtils<QualitySamplingVO>> getQualitySampling(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的质量抽检信息
     *
     * @param params 过滤参数
     * @return 质量抽检信息链表
     */
    ResultDO<PageUtils<QualitySamplingVO>> getQualitySamplingByConditions(Map<String, Object> params);

    /**
     * 插入质量抽检记录
     *
     * @param qualitySamplingDO 质量抽检信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(QualitySamplingDO qualitySamplingDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改质量抽检信息
     *
     * @param qualitySamplingDO 质量抽检实体
     * @return 是否成功
     */
    ResultDO<Void> updateQualitySampling(QualitySamplingDO qualitySamplingDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param qualitySamplingDO 质量抽检实体
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(QualitySamplingDO qualitySamplingDO);

    /**
     * 导出excel
     *
     * @param qualitySamplingVO 质量抽检信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(QualitySamplingVO[] qualitySamplingVO);

}
