package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 制造流程单DAO接口
 *
 * @author zzy
 */
@Mapper
public interface ManufactureProcessSlaveDAO {

    /**
     * 获得制造流程单数据的数量
     *
     * @param params 过滤参数
     * @return 制造流程单数据数量
     */
    int count(Map<String, Object> params);

    /**
     * 搜索制造流程单信息
     *
     * @param params 过滤参数
     * @return 制造流程单信息链表
     */
    List<ManufactureProcessSlaveDO> getManufactureProcessSlave(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param manufactureProcessSlaveDO 制造流程单信息实体
     */
    void insert(ManufactureProcessSlaveDO manufactureProcessSlaveDO);

    /**
     * 插入若干条记录
     *
     * @param manufactureProcessSlaveDO 实体
     */
    void insertAll(@Param("manufactureProcessSlaveDO") List<ManufactureProcessSlaveDO> manufactureProcessSlaveDO);

    /**
     * 通过id修改IsMaterialExport
     *
     * @param id 制造流程单信息主键
     * @param isMaterialExport 是/否
     */
    void updateIsMaterialExport(@Param("id") String id, @Param("isMaterialExport") String isMaterialExport);

    /**
     * 通过id修改isGeneralManufactureProcess
     *
     * @param id 制造流程单信息主键
     */
    void updateIsGeneralManufactureProcess(@Param("id") String id);

    /**
     * 通过id修改isIntoMake
     *
     * @param id 制造流程单信息主键
     */
    void updateIsIntoMake(@Param("id") String id);

    /**
     * 通过id修改isExportCheckPass
     *
     * @param id 制造流程单信息主键
     */
    void updateIsExportCheckPass(@Param("id") String id);

    /**
     * 获得工作传票号
     *
     * @param serialnumberString 工作传票号
     * @return 制造流程单工作传票号
     */
    List<ManufactureProcessSlaveDO> getJobTicketNumberBySerialnumberString(@Param("serialnumberString") String serialnumberString);

    /**
     * 通过客户id，生产补单获得
     *
     * @param customerOrderId 客户订单id
     * @param isIssueOrder 是否为补单
     * @return 制造流程单信息
     */
    List<ManufactureProcessSlaveDO> getManufactureProcessSlaveBySome(@Param("customerOrderId")String customerOrderId, @Param("isIssueOrder")String isIssueOrder);

    /**
     * 通过主键获得制造流程单信息
     *
     * @param id 主键
     * @return 制造流程单信息
     */
    ManufactureProcessSlaveDO getManufactureProcessSlaveById(@Param("id") String id);

    /**
     * 通过主键获得所有制造流程单信息
     *
     * @param ids 主键数组
     * @return 制造流程单信息链表
     */
    List<ManufactureProcessSlaveDO> getManufactureProcessSlaveByIds(@Param("ids") List<String> ids);

    /**
     * 修改是否零品入库状态(增加)
     *
     * @param id 制造流程单id
     */
    void changeIsZeroProductInHouseByIdWhenInsert(@Param("id") String id);

    /**
     * 修改是否零品入库状态(删除)
     *
     * @param id 制造流程单id
     */
    void changeIsZeroProductInHouseByIdWhenDelete(@Param("id") String id);

    /**
     * 修改是否成品入库状态(增加)
     *
     * @param id 制造流程单id
     */
    void changeIsInHouseByIdWhenInsert(@Param("id") String id);

    /**
     * 修改是否成品入库状态(删除)
     *
     * @param id 制造流程单id
     */
    void changeIsInHouseByIdWhenDelete(@Param("id") String id);

}
