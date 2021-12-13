package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialNumberSetDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料数量设置DAO接口
 *
 * @author qiang
 */
@Mapper
public interface MaterialNumberSetDAO {

    /**
     * 获得原材料数量设置数据的数量
     *
     * @param params 过滤参数
     * @return 原材料数量设置数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得原材料数量设置数据
     *
     * @param params 过滤参数
     * @return 原材料数量设置链表
     */
    List<MaterialNumberSetDO> getMaterialNumberSet(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialNumberSetDO 原材料数量设置实体
     */
    void insert(MaterialNumberSetDO materialNumberSetDO);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(@Param("id") String id);

    /**
     * 修改原材料信息
     *
     * @param materialNumberSetDO 原材料数量设置实体
     */
    void updateMaterialNumberSet(MaterialNumberSetDO materialNumberSetDO);

}
