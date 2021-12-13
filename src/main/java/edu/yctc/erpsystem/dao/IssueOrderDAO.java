package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.IssueOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 生产补单表
 *
 * @author smile
 */
@Mapper
public interface IssueOrderDAO {

    /**
     * 获得生产补单数据的数量
     *
     * @param params 过滤参数
     * @return 生产补单数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有生产补单信息
     *
     * @param params 过滤参数
     * @return 生产补单信息链表
     */
    List<IssueOrderDO> getIssueOrder(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param issueOrderDO 生产补单实体
     */
    void insert(IssueOrderDO issueOrderDO);

    /**
     * 通过生产补单id删除记录
     *
     * @param id 生产补单信息主键
     */
    void delete(@Param("id") String id);

    /**
     * 通过id修改订单数量
     *
     * @param issueOrderDO 生产补单实体
     */
    void updateIssueOrder(IssueOrderDO issueOrderDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param issueOrderDO 生产补单实体
     */
    void updateCheckerById(IssueOrderDO issueOrderDO);

    /**
     * 通过生产补单id生产制造流程单
     *
     * @param id 生产补单信息主键
     */
    void make(@Param("id") String id);

}
