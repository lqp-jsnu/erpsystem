package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO接口
 *
 * @author lqp
 */
@Mapper
public interface UserDAO {

    /**
     * 获得用户数据的数量
     *
     * @param params 过滤参数
     * @return 用户数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得用户
     *
     * @param params 过滤参数
     * @return 用户DO
     */
    List<UserDO> getUser(Map<String, Object> params);

    /**
     * 登陆
     *
     * @param loginName 登录名
     * @param pwd 密码
     * @return 用户DO
     */
    UserDO login(@Param("loginName") String loginName, @Param("pwd") String pwd);

    /**
     * 通过登录名获得用户DO
     *
     * @param loginName 登录名
     * @return 用户DO
     */
    UserDO getUserByLoginName(@Param("loginName") String loginName);

    /**
     * 通过主键查找用户名称
     *
     * @param id 主键
     * @return 用户名称
     */
    UserDO getUserById(@Param("id") String id);

    /**
     * 通过主键查找用户名称
     *
     * @param ids 主键链表
     * @return 用户名称链表
     */
    List<UserDO> getUserByIds(@Param("ids") List<String> ids);

    /**
     * 插入一条记录
     *
     * @param userDO 用户实体
     */
    void insert(UserDO userDO);

    /**
     * 删除
     *
     * @param id 用户id
     */
    void delete(@Param("id") String id);

    /**
     * 根据用户id修改用户信息
     *
     * @param userDO 用户实体
     */
    void updateUserInfo(UserDO userDO);

    /**
     * 根据用户id修改用户头像
     *
     * @param userDO 用户实体
     */
    void updatePhotoById(UserDO userDO);

    /**
     * 根据用户id修改用户密码
     *
     * @param userDO 用户实体
     */
    void updatePasswordById(UserDO userDO);

}
