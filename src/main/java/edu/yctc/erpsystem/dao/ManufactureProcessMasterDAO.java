package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ManufactureProcessMasterDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 制造流程单主表DAO接口
 *
 * @author lqp
 */
@Mapper
public interface ManufactureProcessMasterDAO {

    /**
     * 获得制造流程单主表数据的数量
     *
     * @param params 过滤参数
     * @return 制造流程单主表数据数量
     */
    int count(Map<String, Object> params);

    /**
     * 搜索制造流程单主表信息
     *
     * @param params 过滤参数
     * @return 制造流程单主表信息链表
     */
    List<ManufactureProcessMasterDO> getManufactureProcessMaster(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param manufactureProcessMasterDO 制造流程单主表信息实体
     */
    void insert(ManufactureProcessMasterDO manufactureProcessMasterDO);

    /**
     * 通过主键获得制造流程单主表信息
     *
     * @param id 主键
     * @return 制造流程单主表信息
     */
    ManufactureProcessMasterDO getManufactureProcessMasterById(@Param("id") String id);

    /**
     * 通过主键获得所有制造流程单主表信息
     *
     * @param ids 主键链表
     * @return 制造流程单信息主表链表
     */
    List<ManufactureProcessMasterDO> getManufactureProcessMasterByIds(@Param("ids") List<String> ids);

}
