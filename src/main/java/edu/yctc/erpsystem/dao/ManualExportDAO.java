package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ManualExportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 手动出库表
 *
 * @author smile
 */
@Mapper
public interface ManualExportDAO {

    /**
     * 获得手动出库数据的数量
     *
     * @param params 过滤参数
     * @return 手动出库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有手动出库信息
     *
     * @param params 过滤参数
     * @return 手动出库信息链表
     */
    List<ManualExportDO> getManualExport(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param manualExportDO 手动出库实体
     */
    void insert(ManualExportDO manualExportDO);

    /**
     * 通过手动出库id删除记录
     *
     * @param id 手动出库息主键
     */
    void delete(@Param("id") String id);

    /**
     * 通过id修改amount exportDate remark
     *
     * @param manualExportDO 手动出库实体
     */
    void updateManualExport(ManualExportDO manualExportDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param manualExportDO 手动出库废实体
     */
    void updateCheckerById(ManualExportDO manualExportDO);

    /**
     * 通过主键获得手动出库信息
     *
     * @param id 主键
     * @return 手动出库信息
     */
    ManualExportDO getManualExportById(@Param("id") String id);

    /**
     * 通过主键获得所有手动出库信息
     *
     * @param ids 主键链表
     * @return 手动出库表
     */
    List<ManualExportDO> getManualExportByIds(@Param("ids") List<String> ids);

}
