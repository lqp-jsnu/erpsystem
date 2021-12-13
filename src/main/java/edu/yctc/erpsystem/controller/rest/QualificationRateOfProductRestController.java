package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.QualificationRateOfProductVO;

import java.util.Map;

/**
 * 成品合格率数据接口
 *
 * @author zzy
 */
public interface QualificationRateOfProductRestController {

    /**
     * 获得所有成品合格信息
     *
     *@param params 过滤参数
     * @return 成品合格信息链表
     */
    ResultDO<PageUtils<QualificationRateOfProductVO>> getQualificationRateOfProduct(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品合格信息
     *
     * @param params 过滤参数
     * @return 成品合格信息链表
     */
    ResultDO<PageUtils<QualificationRateOfProductVO>> getQualificationRateOfProductByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param qualificationRateOfProductVO 成品合格信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(QualificationRateOfProductVO[] qualificationRateOfProductVO);

}
