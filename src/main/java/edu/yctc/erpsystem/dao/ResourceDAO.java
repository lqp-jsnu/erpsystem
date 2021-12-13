package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单资源DAO接口
 *
 * @author lqp
 */
@Mapper
public interface ResourceDAO {

    /**
     * 根据用户id确定能显示的所有资源
     *
     * @param id 用户id
     * @return 菜单资源链表
     */
    List<ResourceDO> getMenuResourcesByUserId(@Param("id") String id);

    /**
     * 根据用户id确定所有资源
     *
     * @param id 用户id
     * @return 菜单资源链表
     */
    List<ResourceDO> getAllResourcesByUserId(@Param("id") String id);

    /**
     * 得到所有资源
     *
     * @return 资源链表
     */
    List<ResourceDO> getAllResources();

    /**
     * 插入一条记录
     *
     * @param resourceDO 资源实体
     */
    void insert(ResourceDO resourceDO);

    /**
     * 删除
     *
     * @param id 资源id
     */
    void delete(@Param("id") String id);

    /**
     * 修改资源记录
     *
     * @param resourceDO 资源实体
     */
    void updateResources(ResourceDO resourceDO);

    /**
     * 通过主键查找资源记录
     *
     * @param id 主键
     * @return 资源记录链表
     */
    ResourceDO getResourceById(@Param("id") String id);

    /**
     * 通过主键查找资源记录
     *
     * @param ids 主键链表
     * @return 资源记录链表
     */
    List<ResourceDO> getResourceByIds(@Param("ids") List<String> ids);

}
