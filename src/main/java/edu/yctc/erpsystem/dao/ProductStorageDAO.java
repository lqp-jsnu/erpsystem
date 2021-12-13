package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductStorageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 成品入库DAO接口
 *
 * @author lqp
 */
@Mapper
public interface ProductStorageDAO {

    /**
     * 获得成品入库数据的数量
     *
     * @param params 过滤参数
     * @return 成品入库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获取成品入库信息
     *
     * @param params 过滤参数
     * @return 成品入库信息链表
     */
    List<ProductStorageDO> getProductStorage(Map<String, Object> params);

    /**
     * 获得成品入库明细数据的数量
     *
     * @param params 过滤参数
     * @return 成品入库明细数据的数量
     */
    int countDetail(Map<String, Object> params);

    /**
     * 获取成品入库明细信息
     *
     * @param params 过滤参数
     * @return 成品入库明细信息链表
     */
    List<ProductStorageDO> getProductStorageDetail(Map<String, Object> params);

    /**
     * 新增成品入库信息
     *
     * @param productStorageDO 成品入库实体
     *
     */
    void insert(ProductStorageDO productStorageDO);

    /**
     * 通过主键删除成品入库信息
     *
     * @param id 成品入库信息主键
     *
     */
    void delete(@Param("id")String id);

    /**
     * 编辑成品入库信息
     *
     * @param productStorageDO 成品入库实体
     *
     */
    void updateProductStorage(ProductStorageDO productStorageDO);

    /**
     * 修改库存数量
     *
     * @param productStorageDO 成品入库实体
     *
     */
    void updateStorageAmount(ProductStorageDO productStorageDO);

    /**
     * 编辑审核信息
     *
     * @param productStorageDO 成品入库信息实体
     *
     */
    void updateCheckerById(ProductStorageDO productStorageDO);

    /**
     * 通过主键获得成品入库信息
     *
     * @param id 主键
     * @return 成品入库信息
     */
    ProductStorageDO getProductStorageById(@Param("id") String id);

    /**
     * 通过主键获得所有成品入库信息
     *
     * @param ids 主键链表
     * @return 成品入库信息链表
     */
    List<ProductStorageDO> getProductStorageByIds(@Param("ids") List<String> ids);

}
