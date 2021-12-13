package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ManufactureProcessSlaveVO;

import java.util.Map;

/**
 * 制造流程单逻辑接口
 *
 * @author zzy
 */
public interface ManufactureProcessSlaveInterService {

    /**
     * 获取制造流程信息
     *
     * @param params 过滤参数
     * @return 制造流程单链表
     */
    ResultDO<PageUtils<ManufactureProcessSlaveVO>> getManufactureProcessSlave(Map<String, Object> params);

    /**
     * 通过materialInventoryMasterId获取制造流程信息
     *
     * @param params 过滤参数
     * @return 制造流程单链表
     */
    ResultDO<PageUtils<ManufactureProcessSlaveVO>> getManufactureProcessSlaveByMaterialInventoryMasterId(Map<String, Object> params);

    /**
     * 使用增加功能改变是否入库状态
     *
     * @param manufactureProcessSlaveVO 制造流程单实体
     * @return 是否成功
     */
    ResultDO<Void> changeIsZeroProductInHouseWhenInsert(ManufactureProcessSlaveVO manufactureProcessSlaveVO);

    /**
     * 使用删除功能改变是否入库状态
     *
     * @param manufactureProcessSlaveVO 制造流程单实体
     * @return 是否成功
     */
    ResultDO<Void> changeIsZeroProductInHouseWhenDelete(ManufactureProcessSlaveVO manufactureProcessSlaveVO);

    /**
     * 使用增加功能改变是否成品入库状态
     *
     * @param manufactureProcessSlaveVO 制造流程单实体
     * @return 是否成功
     */
    ResultDO<Void> changeIsInHouseWhenInsert(ManufactureProcessSlaveVO manufactureProcessSlaveVO);

    /**
     * 使用删除功能改变是否成品入库状态
     *
     * @param manufactureProcessSlaveVO 制造流程单实体
     * @return 是否成功
     */
    ResultDO<Void> changeIsInHouseWhenDelete(ManufactureProcessSlaveVO manufactureProcessSlaveVO);

}
