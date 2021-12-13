package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.DictDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 数据字典逻辑接口
 *
 * @author xcg
 */
public interface DictInterService {

    /**
     * 获得所有数据字典信息
     *
     * @param params 过滤参数
     * @return 数据字典信息链表
     */
    ResultDO<PageUtils<DictDO>> getDict(Map<String, Object> params);

    /**
     * 添加一条数据字典
     *
     * @param dictDO 数据字典实体
     * @return 是否成功
     */
    ResultDO<Void> insert(DictDO dictDO);

    /**
     * 删除
     *
     * @param id 数据字典记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改数据字典
     *
     * @param dictDO 数据字典实体
     * @return 是否成功
     */
    ResultDO<Void> updateDict(DictDO dictDO);

}
