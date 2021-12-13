package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 材料主信息DAO接口
 *
 * @author qiang
 */
@Mapper
public interface MaterialInfoMasterDAO {

    /**
     * 获得原材料信息数据的数量
     *
     * @param params 过滤参数
     * @return 原材料信息数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有原材料信息
     *
     * @param params 过滤参数
     * @return 原材料信息链表
     * */
    List<MaterialInfoMasterDO> getMaterialInfoMaster(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialInfoMasterDO 材料主信息实体
     */
    void insert(MaterialInfoMasterDO materialInfoMasterDO);

    /**
     * 删除
     *
     * @param id 原材料信息id
     */
    void delete(@Param("id") String id);

    /**
     * 修改料品主信息
     *
     * @param materialInfoMasterDO 料品主信息实体
     */
    void updateMaterialInfoMaster(MaterialInfoMasterDO materialInfoMasterDO);

    /**
     * 通过id获得原材料信息
     *
     * @param id 主键
     * @return 原材料信息
     */
    MaterialInfoMasterDO getMaterialInfoMasterById(@Param("id") String id);

    /**
     * 通过id获得原材料信息
     *
     * @param ids 主键链表
     * @return 原材料信息链表
     */
    List<MaterialInfoMasterDO> getMaterialInfoMasterByIds(@Param("ids") List<String> ids);

}
