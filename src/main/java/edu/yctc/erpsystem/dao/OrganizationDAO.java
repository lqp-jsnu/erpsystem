package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.OrganizationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理
 *
 * @author xcg
 */
@Mapper
public interface OrganizationDAO {

    /**
     * 得到所有部门
     *
     * @return 部门链表
     */
    List<OrganizationDO> getAllOrganization();

    /**
     * 插入一条记录
     *
     * @param organizationDO 部门实体
     */
    void insert(OrganizationDO organizationDO);

    /**
     * 通过部门id删除记录
     *
     * @param id 记录id
     */
    void delete(@Param("id") String id);

    /**
     * 通过id修改部门记录
     *
     * @param organizationDO 部门实体
     */
    void updateOrganization(OrganizationDO organizationDO);

}
