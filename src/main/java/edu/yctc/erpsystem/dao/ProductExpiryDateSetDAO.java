package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductExpiryDateSetDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 产品保质期设置DAO接口
 *
 * @author qiang
 */
public interface ProductExpiryDateSetDAO {

    /**
     * 获得成品保质期设置数据的数量
     *
     * @param params 过滤参数
     * @return 成品保质期设置数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得成品保质期设置数据
     *
     * @param params 过滤参数
     * @return 产品保质期设置链表
     */
    List<ProductExpiryDateSetDO> getExpiryDateSet(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param productExpiryDateSetDO 产品保质期设置实体
     */
    void insert(ProductExpiryDateSetDO productExpiryDateSetDO);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(@Param("id") String id);

    /**
     * 修改产品信息
     *
     * @param productExpiryDateSetDO 产品保质期设置实体
     */
    void updateExpiryDateSet(ProductExpiryDateSetDO productExpiryDateSetDO);

}
