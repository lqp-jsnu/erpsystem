package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.vo.TreeViewVO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 资源数据接口
 *
 * @author lqp
 */
public interface ResourceRestController {

    /**
     * 得到树形列表
     *
     * @param httpSession session
     * @return 树形列表
     */
    ResultDO<List<TreeViewVO>> getTreeView(HttpSession httpSession);

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
     * @param resourceDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(ResourceDO resourceDO);

    /**
     * 通过id修改资源记录
     *
     * @param resourceDO 资源实体
     * @return 是否成功
     */
    ResultDO<Void> updateResources(ResourceDO resourceDO);

}
