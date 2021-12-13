package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialStorageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料入库表
 *
 * @author xiatao
 */
@Mapper
public interface MaterialStorageDAO {

    /**
     * 获得原材料入库数据的数量
     *
     * @param params 过滤参数
     * @return 原材料入库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有原材料入库信息
     *
     * @param params 过滤参数
     * @return 原材料入库信息链表
     */
    List<MaterialStorageDO> getMaterialStorage(Map<String, Object> params);

    /**
     * 插入一条记录
     * @param materialStorageDO 实体
     * */
    void insert(MaterialStorageDO materialStorageDO);

    /**
     * 通过原材料入库id删除记录
     *
     * @param id 原材料入库信息主键
     */
    void delete(@Param("id") String id);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialStorageDO 原材料入库实体
     */
    void updateCheckerById(MaterialStorageDO materialStorageDO);

    /**
     * 通过主键获得原材料入库信息
     *
     * @param id 主键
     * @return 客户信息
     */
    MaterialStorageDO getMaterialStorageById(@Param("id") String id);

    /**
     * 通过主键获得所有原材料入库信息
     *
     * @param ids 主键链表
     * @return 原材料入库信息链表
     */
    List<MaterialStorageDO> getMaterialStorageByIds(@Param("ids") List<String> ids);

}
