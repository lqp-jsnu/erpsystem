package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.OriginalProductDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.OriginalProductVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 原始成品数据接口
 *
 * @author qiang
 */
public interface OriginalProductRestController {

    /**
     * 获得所有成品原始信息视图
     *
     * @param params 过滤参数
     * @return 成品原始信息信息链表
     */
    ResultDO<PageUtils<OriginalProductVO>> getOriginalProduct(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品原始信息
     *
     * @param params 过滤参数
     * @return 成品原始信息链表
     */
    ResultDO<PageUtils<OriginalProductVO>> getOriginalProductByConditions(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param originalProductDO 产品原始信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(OriginalProductDO originalProductDO);

    /**
     * 删除记录
     *
     * @param originalProduct 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(OriginalProductVO originalProduct);

    /**
     * 修改产品原始信息信息
     *
     * @param originalProductDO 产品原始信息设置实体
     * @return 是否成功
     */
    ResultDO<Void> updateOriginalProduct(OriginalProductDO originalProductDO);

    /**
     * 修改成品图片
     *
     * @param originalProductDO 图片
     * @return 是否成功
     */
    ResultDO<Void> updateDrawById(OriginalProductDO originalProductDO);

    /**
     * 审核
     *
     * @param originalProductDO 成品原始信息实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(OriginalProductDO originalProductDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param originalProducts 成品原始信息信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(OriginalProductVO[] originalProducts);

    /**
     * 通过客户搜索材料信息
     *
     * @param params 搜索信息
     * @return 材料信息链表
     */
    ResultDO<PageUtils<OriginalProductVO>> getOriginalProductBySearchCustomerId(Map<String, Object> params);


}
