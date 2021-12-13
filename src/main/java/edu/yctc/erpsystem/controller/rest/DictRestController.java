package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.DictDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 数据字典接口
 *
 * @author xcg
 */
public interface DictRestController {

    /**
     * 获得数据字典所有信息
     *
     * @param params 过滤参数
     * @return 数据字典信息链表
     */
    ResultDO<PageUtils<DictDO>> getDict(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param dictDO 数据字典实体
     * @return 是否成功
     */
    ResultDO<Void> insert(DictDO dictDO);

    /**
     * 删除
     *
     * @param dictDO 数据字典记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(DictDO dictDO);

    /**
     * 修改数据字典实体
     *
     * @param dictDO 数据字典实体
     * @return 是否成功
     */
    ResultDO<Void> updateDict(DictDO dictDO);

}
