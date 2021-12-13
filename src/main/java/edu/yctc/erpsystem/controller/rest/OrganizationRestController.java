package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.OrganizationDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.vo.TreeViewVO;

import java.util.List;

/**
 * 部门管理接口
 *
 * @author xcg
 */
public interface OrganizationRestController {

    /**
     * 得到树形列表
     *
     * @return 树形列表
     */
    ResultDO<List<TreeViewVO>> getTreeView();

    /**
     * 获得所有部门
     *
     * @return 部门链表
     */
    ResultDO<List<OrganizationDO>> getAllOrganization();

    /**
     * 插入一条记录
     *
     * @param organizationDO 部门实体
     * @return 是否成功
     */
    ResultDO<Void> insert(OrganizationDO organizationDO);

    /**
     * 删除记录
     *
     * @param organizationDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(OrganizationDO organizationDO);

    /**
     * 通过id修改部门记录
     *
     * @param organizationDO 部门实体
     * @return 是否成功
     */
    ResultDO<Void> updateOrganization(OrganizationDO organizationDO);

}
