package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialExportDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 材料出库明细DAO接口
 *
 * @author wjd
 */
@Mapper
public interface MaterialExportDetailDAO {

    /**
     * 获得材料出库明细数据的数量
     *
     * @param params 过滤参数
     * @return 材料出库明细明细数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得查看信息
     *
     * @param params 过滤参数
     * @return 材料出库明细信息链表
     */
    List<MaterialExportDetailDO> getMaterialExportDetail(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialExportDetailDO 实体
     */
    void insert(MaterialExportDetailDO materialExportDetailDO);

    /**
     * 插入若干条记录
     *
     * @param materialExportDetailDO 实体
     */
    void insertAll(@Param("materialExportDetailDO") List<MaterialExportDetailDO> materialExportDetailDO);

    /**
     * 删除一条记录
     *
     * @param id 材料出库明细实体id
     */
    void delete(@Param("id") String id);

    /**
     * 删除若干条记录
     *
     * @param ids 材料出库明细实体ids
     */
    void deleteAll(@Param("ids") List<String> ids);

    /**
     * 通过materialExportId得到记录
     *
     * @param materialExportId 材料出库明细主键
     * @return 材料出库明细信息链表
     */
    List<MaterialExportDetailDO> getMaterialExportDetailByMaterialExportId(@Param("materialExportId") String materialExportId);

    /**
     * 通过materialExportId得到所有记录
     *
     * @param materialExportIds 材料出库明细主键链表
     * @return 材料出库明细信息链表
     */
    List<MaterialExportDetailDO> getMaterialExportDetailByMaterialExportIds(@Param("materialExportIds") List<String> materialExportIds);

}
