package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialDumpDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料报废DAO接口
 *
 * @author lqp
 */
@Mapper
public interface MaterialDumpDAO {

    /**
     * 获得原材料报废数据的数量
     *
     * @param params 过滤参数
     * @return 原材料报废数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有原材料报废信息
     *
     * @param params 过滤参数
     * @return 原材料报废信息链表
     */
    List<MaterialDumpDO> getMaterialDump(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialDumpDO 原材料报废实体
     */
    void insert(MaterialDumpDO materialDumpDO);

    /**
     * 删除
     *
     * @param id 原材料报废信息主键
     */
    void delete(@Param("id") String id);

    /**
     * 通过id修改dumpAmount dumpDate dumpReason
     *
     * @param materialDumpDO 原材料报废实体
     */
    void updateMaterialDump(MaterialDumpDO materialDumpDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialDumpDO 原材料报废实体
     */
    void updateCheckerById(MaterialDumpDO materialDumpDO);

    /**
     * 通过主键获得原材料报废信息
     *
     * @param id 主键
     * @return 原材料报废信息
     */
    MaterialDumpDO getMaterialDumpById(@Param("id") String id);

    /**
     * 通过主键获得所有原材料报废信息
     *
     * @param ids 主键链表
     * @return 原材料报废信息表
     */
    List<MaterialDumpDO> getMaterialDumpByIds(@Param("ids") List<String> ids);

}
