package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.BillOfSaleHistoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 出货单历史DAO接口
 *
 * @author zzy
 */
@Mapper
public interface BillOfSaleHistoryDAO {

    /**
     * 获得出货单历史数据的数量
     *
     * @param params 过滤参数
     * @return 出货单历史数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有出货单历史信息
     *
     * @param params 过滤参数
     * @return 出货单历史信息链表
     */
    List<BillOfSaleHistoryDO> getBillOfSaleHistory(Map<String, Object> params);

    /**
     * 生成出库单功能
     *
     * @param billOfSaleHistoryDO  出货单历史实体
     */
    void insert(BillOfSaleHistoryDO billOfSaleHistoryDO);

}
