package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.entity.ResultDO;

import java.util.List;

/**
 * 菜单资源接口
 *
 * @author lqp
 */
public interface ResourceInterService {

    /**
     * 根据用户id确定能显示的所有资源
     *
     * @param id 用户id
     * @return 菜单资源
     */
    ResultDO<List<ResourceDO>> getMenuResourcesByUserId(String id);

    /**
     * 根据用户id确定所有资源
     *
     * @param id 用户id
     * @return 菜单资源
     */
    ResultDO<List<ResourceDO>> getAllResourcesByUserId(String id);

    /**
     * 得到所有资源
     *
     * @return 资源链表
     */
    ResultDO<List<ResourceDO>> getAllResources();

    /**
     * 插入一条记录
     *
     * @param resourceDO 资源实体
     * @return 是否成功
     */
    ResultDO<Void> insert(ResourceDO resourceDO);

    /**
     * 删除记录
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改资源记录
     *
     * @param resourceDO 资源实体
     * @return 是否成功
     */
    ResultDO<Void> updateResources(ResourceDO resourceDO);

}
