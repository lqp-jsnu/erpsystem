package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualificationRateOfProductVO;

import java.util.Map;

/**
 * 成品合格率逻辑接口
 *
 * @author zzy
 */
public interface QualificationRateOfProductInterService {

    /**
     * 获得所有成品合格率信息
     *
     * @param params  过滤参数
     * @return  成品合格率信息链表
     */
    ResultDO<PageUtils<QualificationRateOfProductVO>> getQualificationRateOfProduct(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品合格率信息
     *
     * @param params 过滤参数
     * @return 成品合格率信息链表
     */
    ResultDO<PageUtils<QualificationRateOfProductVO>> getQualificationRateOfProductByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param qualificationRateOfProductVO 成品合格率信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(QualificationRateOfProductVO[] qualificationRateOfProductVO);

}
