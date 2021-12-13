package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialExportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料出库信息DAO接口
 *
 * @author wjd
 */
@Mapper
public interface MaterialExportDAO {

    /**
     * 获得材料出库数据的数量
     *
     * @param params 过滤参数
     * @return 材料出库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得材料出库数据信息
     *
     * @param params 过滤参数
     * @return 材料出库信息链表
     */
    List<MaterialExportDO> getMaterialExport(Map<String, Object> params);

    /**
     * 获得材料出库明细数据的数量
     *
     * @param params 过滤参数
     * @return 材料出库明细明细数据的数量
     */
    int counts(Map<String, Object> params);

    /**
     * 获得材料出库明细数据信息
     *
     * @param params 过滤参数
     * @return 材料出库明细信息链表
     */
    List<MaterialExportDO> getMaterialExportDetail(Map<String, Object> params);

    /**
     * 生产出库时插入信息
     *
     * @param materialExportDO 材料出库实体
     */
    void insert(MaterialExportDO materialExportDO);

    /**
     * 通过材料出库明细id删除记录
     *
     * @param id 材料出库明细主键
     */
    void delete(@Param("id") String id);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialExportDO 材料出库实体
     */
    void updateCheckerById(MaterialExportDO materialExportDO);

    /**
     * 通过id获取原材料出库信息
     *
     * @param id 主键
     * @return 原材料出库信息
     */
    MaterialExportDO getMaterialExportById(@Param("id") String id);

    /**
     * 通过id获取所有原材料出库信息
     *
     * @param ids 主键表
     * @return 原材料出库信息表
     */
    List<MaterialExportDO> getMaterialExportByIds(@Param("ids") List<String> ids);

    /**
     * 通过已列印流程单外键获得原材料出库信息
     *
     * @param manufactureProcessSlaveId 主键
     * @return 原材料出库信息
     */
    MaterialExportDO getMaterialExportByManufactureProcessSlaveId(@Param("manufactureProcessSlaveId")String manufactureProcessSlaveId);

}
