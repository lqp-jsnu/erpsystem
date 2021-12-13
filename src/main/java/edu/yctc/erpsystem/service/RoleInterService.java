package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.RoleDO;
import edu.yctc.erpsystem.entity.RoleResourceDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色逻辑接口
 *
 * @author xcg
 */
public interface RoleInterService {

    /**
     * 获得所有角色信息
     *
     * @param params 过滤参数
     * @return params 角色信息链表
     */
    ResultDO<PageUtils<RoleDO>> getRole(Map<String, Object> params);

    /**
     * 添加一条角色
     *
     * @param role 角色信息视图
     * @return 是否成功
     */
    ResultDO<Void> insert(RoleDO role);

    /**
     * 根据角色id删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改角色信息
     *
     * @param roleDO 修改实体
     * @return 是否成功
     */
    ResultDO<Void> updateRole(RoleDO roleDO);

    /**
     * 获得所有角色
     *
     * @return 执行结果
     */
    ResultDO<List<RoleDO>> getAllRole();

    /**
     * 通过roleId获得resourceId
     *
     * @param id 记录角色id
     * @return 权限链表
     */
    ResultDO<List<String>> getResourceIdByRoleId(String id);

    /**
     * 插入角色权限
     *
     * @param roleResourceDO 角色权限实体
     * @return 执行结果
     */
    ResultDO<Void> insertRoleResource(RoleResourceDO roleResourceDO);

    /**
     * 初始化角色权限
     *
     * @param id 角色id
     * @return 执行结果
     */
    ResultDO<Void> deleteByRoleId(String id);

}
