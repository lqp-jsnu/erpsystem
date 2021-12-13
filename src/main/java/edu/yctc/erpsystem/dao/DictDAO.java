package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.DictDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据字典接口
 *
 * @author xcg
 */
@Mapper
public interface DictDAO {

    /**
     * 获得数据字典数据的数量
     *
     * @param params 过滤参数
     * @return 原材料报废数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有数据字典信息
     *
     * @param params 过滤参数
     * @return 数据字典信息链表
     */
    List<DictDO> getDict(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param dictDO 数据库备份实体
     */
    void insert(DictDO dictDO);

    /**
     * 删除
     *
     * @param id 数据字典记录id
     */
    void delete(@Param("id") String id);

    /**
     * 修改数据字典信息
     *
     * @param dictDO 修改实体
     */
    void updateDict(DictDO dictDO);

    /**
     * 通过主键获得数据字典信息
     *
     * @param id 主键
     * @return 数据字典信息
     */
    DictDO getDictById(@Param("id") String id);

    /**
     * 通过主键获得所有数据字典信息
     *
     * @param ids 主键链表
     * @return 数据字典信息表
     */
    List<DictDO> getDictByIds(@Param("ids") List<String> ids);

}
