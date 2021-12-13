package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.QualitySamplingDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualitySamplingVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 质量抽检数据接口
 *
 * @author zzy
 */
public interface QualitySamplingRestController {

    /**
     * 获得所有质量抽检信息
     *
     * @param params 过滤参数
     * @return 质量抽检信息链表
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
     * 增加质量抽检信息
     *
     * @param qualitySamplingDO 质量抽检实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(QualitySamplingDO qualitySamplingDO, HttpSession httpSession);

    /**
     * 删除
     *
     * @param qualitySamplingDO 质量抽检id
     * @return 是否成功
     */
    ResultDO<Void> delete(QualitySamplingDO qualitySamplingDO);

    /**
     * 修改质量抽检信息
     *
     * @param qualitySamplingDO 质量抽检实体
     * @return 是否成功
     */
    ResultDO<Void> updateQualitySampling(QualitySamplingDO qualitySamplingDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param qualitySamplingDO 质量抽检实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(QualitySamplingDO qualitySamplingDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param qualitySamplingVO 质量抽检信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(QualitySamplingVO[] qualitySamplingVO);

}
