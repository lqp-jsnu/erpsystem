package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.IssueOrderDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.CustomerOrderVO;
import edu.yctc.erpsystem.vo.IssueOrderVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 生产补单表
 *
 * @author smile
 */
public interface IssueOrderRestController {

    /**
     * 获得所有生产补单信息
     *
     * @param params 过滤参数
     * @return 生产补单表
     */
    ResultDO<PageUtils<IssueOrderVO>> getIssueOrder(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的生产补单信息
     *
     * @param params 过滤参数
     * @return 生产补单链表
     */
    ResultDO<PageUtils<IssueOrderVO>> getIssueOrderByConditions(Map<String, Object> params);

    /**
     * 增加生产补单信息
     *
     * @param issueOrderDO 待修改的信息
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(IssueOrderDO issueOrderDO, HttpSession httpSession);

    /**
     * 删除记录
     *
     * @param issueOrderDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(IssueOrderDO issueOrderDO);

    /**
     * 通过id修改订单数量
     *
     * @param issueOrderDO 生产补单实体
     * @return 是否成功
     */
    ResultDO<Void> updateIssueOrder(IssueOrderDO issueOrderDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param issueOrderDO 生产补单实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(IssueOrderDO issueOrderDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param issueOrderVO 生产补单信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(IssueOrderVO[] issueOrderVO);

    /**
     * 生成制造流程单
     *
     * @param issueOrder 生产补单实体
     * @return 是否成功
     */
    ResultDO<Void> make(IssueOrderVO issueOrder);

}
