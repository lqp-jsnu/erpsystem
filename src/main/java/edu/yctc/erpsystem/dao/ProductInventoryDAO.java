package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductInventoryDO;
import edu.yctc.erpsystem.vo.MainProductExpiryDateVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;
import edu.yctc.erpsystem.vo.ProductInventorySummaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 成品库存DAO接口
 *
 * @author qiang
 */
@Mapper
public interface ProductInventoryDAO {

    /**
     * 获得首页产品保质期监控视图
     *
     * @param params 过滤参数
     * @return 首页产品保质期监控视图的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得首页产品保质期监控视图
     *
     * @param params 过滤参数
     * @return 首页产品保质期监控视图链表
     */
    List<MainProductExpiryDateVO> getProductInventory(Map<String, Object> params);

    /**
     * 获得成品库存汇总视图的数量
     *
     * @param params 过滤参数
     * @return 成品库存汇总视图的数量
     */
    int summaryCount(Map<String, Object> params);

    /**
     * 获得成品库存汇总视图
     *
     * @param params 过滤参数
     * @return 成品库存汇总视图链表
     */
    List<ProductInventorySummaryVO> getProductInventorySummary(Map<String, Object> params);

    /**
     * 获得首页产品数量监控视图的数量
     *
     * @param params 过滤参数
     * @return 首页产品数量监控视图的数量
     */
    int mainProductNumberCount(Map<String, Object> params);

    /**
     * 获得首页产品数量监控视图
     *
     * @param params 过滤参数
     * @return 首页产品数量监控视图链表
     */
    List<MainProductNumberVO> getMainProductNumber(Map<String, Object> params);

    /**
     * 获得成品库存视图
     *
     * @param params 过滤参数
     * @return 成品库存视图的数量
     */
    int countRepertory(Map<String, Object> params);

    /**
     * 获得成品库存视图
     *
     * @param params 过滤参数
     * @return 成品库存视图链表
     */
    List<ProductInventoryRepertoryVO> getProductInventoryRepertory(Map<String, Object> params);

    /**
     * 新增成品入库信息
     *
     * @param productInventoryDO 成品入库实体
     *
     */
    void insert(ProductInventoryDO productInventoryDO);

    /**
     * 通过主键获得成品入库信息
     *
     * @param id 主键
     * @return 成品入库信息
     */
    ProductInventoryDO getProductInventoryById(@Param("id") String id);

    /**
     * 通过主键获得所有成品入库信息
     *
     * @param ids 主键链表
     * @return 成品入库信息表
     */
    List<ProductInventoryDO> getProductInventoryByIds(@Param("ids") List<String> ids);

}
