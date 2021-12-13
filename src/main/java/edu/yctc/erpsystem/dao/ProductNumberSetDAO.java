package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductNumberSetDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 产品数量设置DAO接口
 *
 * @author qiang
 */
@Mapper
public interface ProductNumberSetDAO {

    /**
     * 获得成品数量设置数据的数量
     *
     * @param params 过滤参数
     * @return 成品数量设置数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得成品数量设置
     *
     * @param params 过滤参数
     * @return 产品数量设置链表
     */
    List<ProductNumberSetDO> getProductNumberSet(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param productNumberSetDO 实体
     */
    void insert(ProductNumberSetDO productNumberSetDO);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(@Param("id") String id);

    /**
     * 修改产品信息
     *
     * @param productNumberSetDO 修改实体
     */
    void updateProductNumberSet(ProductNumberSetDO productNumberSetDO);

}

