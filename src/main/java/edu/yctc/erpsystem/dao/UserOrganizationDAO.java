package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.UserOrganizationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组织接口
 *
 * @author xcg
 */
@Mapper
public interface UserOrganizationDAO {

    /**
     * 通过用户id得到机构id
     *
     * @param syUserId 用户id
     * @return 用户机构链表
     */
    List<UserOrganizationDO> getOrganizationIdByUserId(@Param("syUserId") String syUserId);

    /**
     * 插入用户机构关系
     *
     * @param userOrganizationDO 用户机构关系实体
     */
    void insert(UserOrganizationDO userOrganizationDO);

    /**
     * 插入若干条记录
     *
     * @param userOrganizationDO 实体
     */
    void insertAll(@Param("userOrganizationDO") List<UserOrganizationDO> userOrganizationDO);

    /**
     * 通过机构id删除
     *
     * @param syOrganizationId 机构id
     */
    void deleteUserOrganizationByOrganizationId(@Param("syOrganizationId") String syOrganizationId);

    /**
     * 用户删除后删除的其在组织的信息
     *
     * @param syUserId 用户id
     */
    void deleteUserOrganizationByUserId(@Param("syUserId") String syUserId);

}
