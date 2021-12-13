package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.OnlineDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 用户登录历史接口
 *
 * @author qiang
 */
public interface OnlineRestController {

    /**
     * 获得用户登录历史记录
     *
     * @param params 过滤参数
     * @return 用户登录历史链表
     */
    ResultDO<PageUtils<OnlineDO>> getOnline(Map<String, Object> params);

}
