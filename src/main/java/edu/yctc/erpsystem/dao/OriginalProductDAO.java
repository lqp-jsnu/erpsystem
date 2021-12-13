package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.OriginalProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原始成品DAO接口
 *
 * @author qiang
 */
@Mapper
public interface OriginalProductDAO {

    /**
     * 获得原始成品信息数据的数量
     *
     * @param params 过滤参数
     * @return 原始成品信息数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有原成品信息
     *
     * @param params 过滤参数
     * @return 原成品信息链表
     */
    List<OriginalProductDO> getOriginalProduct(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param originalProductDO 实体
     */
    void insert(OriginalProductDO originalProductDO);

    /**
     * 通过成品原始信息id删除记录
     *
     * @param id 成品原始信息主键
     */
    void delete(@Param("id")String id);

    /**
     * 修改产品原始成品信息
     *
     * @param originalProductDO 修改实体
     */
    void updateOriginalProduct(OriginalProductDO originalProductDO);

    /**
     * 根据用id修改图片
     *
     * @param originalProductDO 成品原始信息实体
     */
    void updateDrawById(OriginalProductDO originalProductDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param originalProductDO 成品原始信息实体
     */
    void updateCheckerById(OriginalProductDO originalProductDO);

    /**
     * 通过id获得原始成品信息
     *
     * @param id 主键
     * @return 原始成品信息
     */
    OriginalProductDO getOriginalProductById(@Param("id") String id);

    /**
     * 通过id获得原始成品信息
     *
     * @param ids 主键
     * @return 原始成品信息
     */
    List<OriginalProductDO> getOriginalProductByIds(@Param("ids") List<String> ids);

}
