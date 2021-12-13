package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.RoleResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权力接口
 *
 * @author xcg
 */
@Mapper
public interface RoleResourceDAO {

    /**
     * 根据角色id获得权限
     *
     * @param syRoleId 角色id
     * @return 角色与权限关系链表
     */
    List<RoleResourceDO> getResourceIdByRoleId(@Param("syRoleId") String syRoleId);

    /**
     * 插入一条记录
     *
     * @param roleResourceDO 角色权限实体
     */
    void insert(RoleResourceDO roleResourceDO);

    /**
     * 根据删除角色删除对应权力关系
     *
     * @param syRoleId 角色id
     */
    void deleteByRoleId(@Param("syRoleId") String syRoleId);

}
