package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色接口
 *
 * @author xcg
 */
@Mapper
public interface UserRoleDAO {

    /**
     * 根据用户id得到角色id
     *
     * @param syUserId 用户id
     * @return 用户角色链表
     */
    List<UserRoleDO> getRoleIdByUserId(@Param("syUserId") String syUserId);

    /**
     * 添加用户与角色关系
     *
     * @param userRoleDO 用户角色关系实体
     */
    void insert(UserRoleDO userRoleDO);

    /**
     * 插入若干条记录
     *
     * @param userRoleDO 实体
     */
    void insertAll(@Param("userRoleDO") List<UserRoleDO> userRoleDO);

    /**
     * 根据角色删除删除用户与角色关系
     *
     * @param syRoleId 角色id
     */
    void deleteByRoleId(@Param("syRoleId") String syRoleId);

    /**
     * 根据用户删除删除用户与角色关系
     *
     * @param syUserId 用户id
     */
    void deleteByUserId(@Param("syUserId") String syUserId);

}
