package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.RoleDO;
import edu.yctc.erpsystem.entity.RoleResourceDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 角色逻辑数据接口
 *
 * @author xcg
 */
public interface RoleRestController {

    /**
     * 获得所有角色信息
     *
     * @param params 过滤参数
     * @return 角色信息链表
     */
    ResultDO<PageUtils<RoleDO>> getRole(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param role 角色实体
     * @return 执行结果
     */
    ResultDO<Void> insert(RoleDO role);

    /**
     * 根据角色id删除
     *
     * @param role 记录id
     * @return 执行结果
     */
    ResultDO<Void> delete(RoleDO role);

    /**
     * 修改角色信息
     *
     * @param roleDO 角色实体
     * @return 执行结果
     */
    ResultDO<Void> updateRole(RoleDO roleDO);

    /**
     * 添加权限
     *
     * @param roleResourceDO 角色权限实体
     * @return 执行结果
     */
    ResultDO<Void> insertRoleResource(RoleResourceDO roleResourceDO);

    /**
     * 初始化角色权限
     *
     * @param roleResourceDO 角色权限实体
     * @return 执行结果
     */
    ResultDO<Void> deleteByRoleId(RoleResourceDO roleResourceDO);

    /**
     * 得到所有角色
     *
     * @return 角色链表
     */
    ResultDO<List<RoleDO>> getAllRole();

    /**
     * 通过角色id获得权限id
     *
     * @param roleResourceDO 角色权限实体
     * @return 权限链表
     */
    ResultDO<List<String>> getResourceIdByRoleId(RoleResourceDO roleResourceDO);

}
