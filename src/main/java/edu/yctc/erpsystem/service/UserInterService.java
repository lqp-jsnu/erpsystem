package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.entity.UserOrganizationDO;
import edu.yctc.erpsystem.entity.UserRoleDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 用户逻辑接口
 *
 * @author lqp
 */
public interface UserInterService {

    /**
     * 登录
     *
     * @param userDO 用户DO
     * @return ResultDO
     */
    ResultDO<Void> login(UserDO userDO);

    /**
     * 通过登录名找到UserDO
     *
     * @param loginName 用户登陆名
     * @return ResultDO
     */
    ResultDO<UserDO> getUserByLoginName(String loginName);

    /**
     * 通过id找到UserDO
     *
     * @param id 用户id
     * @return 用户DO
     */
    ResultDO<UserDO> getUserById(String id);

    /**
     * 修改密码
     *
     * @param userDO 密码
     * @return 是否成功
     */
    ResultDO<Void> changePassword(UserDO userDO);

    /**
     * 获得所有用户信息
     *
     * @param params 过滤参数
     * @return 用户信息
     */
    ResultDO<PageUtils<UserVO>> getUsers(Map<String, Object> params);

    /**
     * 添加用户记录
     *
     * @param userDO 用户实体
     * @return 是否成功
     */
    ResultDO<Void> insert(UserDO userDO);

    /**
     * 根据用户id删除用户
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 根据用户id修改用户头像
     *
     * @param userDO 用户实体
     * @return 是否成功
     */
    ResultDO<Void> updatePhotoById(UserDO userDO);

    /**
     * 根据用户id修改用户信息
     *
     * @param userDO 用户实体
     * @return 是否成功
     */
    ResultDO<Void> updateUserInfo(UserDO userDO);

    /**
     * 添加用户角色关系
     *
     * @param userRoleDO 用户角色关系
     * @return 执行结果
     */
    ResultDO<Void> insertUserRole(List<UserRoleDO> userRoleDO);

    /**
     * 初始化用户角色实体
     *
     * @param userRoleDO 用户角色实体
     * @return 执行结果
     */
    ResultDO<Void> initUserRole(UserRoleDO userRoleDO);

    /**
     * 添加用户机构关系
     *
     * @param userOrganizationDO 用户关系实体
     * @return 执行结果
     */
    ResultDO<Void> insertUserOrganization(List<UserOrganizationDO> userOrganizationDO);

    /**
     * 初始化用户机构
     *
     * @param userOrganizationDO 用户机构实体
     * @return 执行结果
     */
    ResultDO<Void> initUserOrganization(UserOrganizationDO userOrganizationDO);

    /**
     * 通过用户id得到角色Id
     *
     * @param userId 用户id
     * @return 执行结果
     */
    ResultDO<List<UserRoleDO>> getRoleIdByUserId(String userId);

    /**
     * 通过用户id得到机构id
     *
     * @param userId 用户id
     * @return 执行结果
     */
    ResultDO<List<UserOrganizationDO>> getOrganizationIdByUserId(String userId);

}