package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.OnlineDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户登录历史监控DAO接口
 *
 * @author qiang
 */
@Mapper
public interface OnlineDAO {

    /**
     * 获得用户登录历史数据的数量
     *
     * @param params 过滤参数
     * @return 用户登录历史数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得用户登录历史记录
     *
     * @param params 过滤参数
     * @return 用户登录历史链表
     */
    List<OnlineDO> getOnline(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param onlineDO 用户登录历史实体
     */
    void insert(OnlineDO onlineDO);

}
