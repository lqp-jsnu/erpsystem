package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.OnlineDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 用户登录历史逻辑接口
 *
 * @author qiang
 */
public interface OnlineInterService {

    /**
     * 获得用户登录历史记录
     *
     * @param params 过滤参数
     * @return 用户登录历史链表
     */
    ResultDO<PageUtils<OnlineDO>> getOnline(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param onlineDO 用户登录历史实体
     * @return 是否成功
     */
    ResultDO<Void> insert(OnlineDO onlineDO);

}
