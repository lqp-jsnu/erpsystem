package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 发票抬头接口
 *
 * @author xcg
 */
@Mapper
public interface InvoiceTitleDAO {

    /**
     * 获得发票抬头数据的数量
     *
     * @param params 过滤参数
     * @return 原材料报废数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有发票抬头信息
     *
     * @param params 过滤参数
     * @return 发票台头信息列表
     */
    List<InvoiceTitleDO> getInvoiceTitle(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param invoiceTitleDO 发票抬头实体
     */
    void insert(InvoiceTitleDO invoiceTitleDO);

    /**
     * 删除
     *
     * @param id 发票抬头记录id
     */
    void delete(@Param("id") String id);

    /**
     * 根据发票抬头id恢复
     *
     * @param id 发票抬头记录id
     */
    void restore(@Param("id") String id);

    /**
     * 修改发票抬头信息
     *
     * @param invoiceTitleDO 修改实体
     */
    void updateInvoiceTitle(InvoiceTitleDO invoiceTitleDO);

    /**
     * 通过主键获得发票抬头
     *
     * @param id 主键
     * @return 发票抬头信息链表
     */
    InvoiceTitleDO getInvoiceTitleById(@Param("id") String id);

    /**
     * 通过主键获得所有发票抬头
     *
     * @param ids 主键数组
     * @return 发票抬头信息链表
     */
    List<InvoiceTitleDO> getInvoiceTitleByIds(@Param("ids") List<String> ids);

}
