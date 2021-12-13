package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.OrganizationDO;
import edu.yctc.erpsystem.entity.ResultDO;

import java.util.List;

/**
 * 部门管理接口
 *
 * @author xcg
 */
public interface OrganizationInterService {

    /**
     * 得到所有部门
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
     * @param id 部门id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改部门记录
     *
     * @param organizationDO 部门实体
     * @return 是否成功
     */
    ResultDO<Void> updateOrganization(OrganizationDO organizationDO);

}
