package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.BillOfSaleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 出货单信息DAO接口
 *
 * @author zzy
 */
@Mapper
public interface BillOfSaleDAO {

    /**
     * 获得出货单数据的数量
     *
     * @param params 过滤参数
     * @return 出货单数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有出货单信息
     *
     * @param params 过滤参数
     * @return 出货单信息链表
     */
    List<BillOfSaleDO> getBillOfSale(Map<String, Object> params);

    /**
     * 插入若干条记录
     *
     * @param billOfSaleDO 实体
     */
    void insertAll(@Param("billOfSaleDO") List<BillOfSaleDO> billOfSaleDO);

    /**
     * 通过主键改变出库状态
     *
     * @param id 出货单信息主键
     *
     */
    void changeExportStatusById(@Param("id") String id);

    /**
     *
     * 通过主键更新备注信息
     *
     * @param billOfSaleDO 出货单实体
     *
     */
    void updateRemarkById(BillOfSaleDO billOfSaleDO);

    /**
     * 通过主键获取出货单信息
     *
     * @param id  主键
     * @return  出货单信息
     */
    BillOfSaleDO getBillOfSaleById(@Param("id") String id);

    /**
     * 通过主键获取出货单信息
     *
     * @param ids  主键数组
     * @return  出货单信息链表
     */
    List<BillOfSaleDO> getBillOfSaleByIds(@Param("ids") List<String> ids);

}
