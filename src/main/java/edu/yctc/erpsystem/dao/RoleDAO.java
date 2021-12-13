package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色DAO接口
 *
 * @author xcg
 */
@Mapper
public interface RoleDAO {

    /**
     * 获得角色数据的数量
     *
     * @param params 过滤参数
     * @return 角色数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有角色信息
     *
     * @param params 过滤参数
     * @return 角色信息链表
     */
    List<RoleDO> getRole(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param roleDO 数据库备份实体
     */
    void insert(RoleDO roleDO);

    /**
     * 根据角色id记录
     *
     * @param id 角色id
     */
    void delete(@Param("id") String id);

    /**
     * 修改角色信息
     *
     * @param roleDO 修改实体
     */
    void updateRole(RoleDO roleDO);

}
