package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.entity.UserOrganizationDO;
import edu.yctc.erpsystem.entity.UserRoleDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.UserVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户信息数据接口
 *
 * @author lqp
 */
public interface UserRestController {

    /**
     * 锁定窗口
     *
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> lock(HttpSession httpSession);

    /**
     * 解锁窗口
     *
     * @param userDO 登录名 和 密码
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> unlock(UserDO userDO, HttpSession httpSession);

    /**
     * 修改密码
     *
     * @param userDO 密码
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> changePassword(UserDO userDO, HttpSession httpSession);

    /**
     * 获得用户信息
     *
     * @param params 分页信息
     * @return 用户信息链表
     */
    ResultDO<PageUtils<UserVO>> getUsers(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param userDO 用户实体
     * @return 是否成功
     */
    ResultDO<Void> insert(UserDO userDO);

    /**
     * 删除
     *
     * @param userDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(UserDO userDO);

    /**
     * 修改用户头像
     *
     * @param userDO 头像
     * @return 是否成功
     */
    ResultDO<Void> updatePhotoById(UserDO userDO);

    /**
     * 修改用户信息
     *
     * @param userDO 用户实体
     * @return 是否成功
     */
    ResultDO<Void> updateUserInfo(UserDO userDO);

    /**
     * 插入用户角色
     *
     * @param userRoleDO 用户角色实体
     * @return 执行结果
     */
    ResultDO<Void> insertUserRole(List<UserRoleDO> userRoleDO);

    /**
     * 初始化用户角色
     *
     * @param userRoleDO 用户角色实体
     * @return 执行结果
     */
    ResultDO<Void> initUserRole(UserRoleDO userRoleDO);

    /**
     * 插入用户机构
     *
     * @param userOrganizationDO 用户机构实体
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
     * 通过用户id得到角色id
     *
     * @param userRoleDO 用户角色实体
     * @return 执行结果
     */
    ResultDO<List<UserRoleDO>> getRoleIdByUserId(UserRoleDO userRoleDO);

    /**
     * 通过用户id得到机构id
     *
     * @param userOrganizationDO 用户机构实体
     * @return 执行结果
     */
    ResultDO<List<UserOrganizationDO>> getOrganizationIdByUserId(UserOrganizationDO userOrganizationDO);

    /**
     * 获取验证码图片
     * @param response 响应
     */
    void verifyCode(HttpServletResponse response);

}