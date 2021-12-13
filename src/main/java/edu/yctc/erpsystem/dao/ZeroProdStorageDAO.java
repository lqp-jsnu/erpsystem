package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ZeroProductStorageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 零品入库信息DAO接口
 *
 * @author zzy
 */
@Mapper
public interface ZeroProdStorageDAO {

    /**
     * 获得零品入库数据的数量
     *
     * @param params 过滤参数
     * @return 零品入库数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有零品入库信息
     *
     * @param params  过滤参数
     * @return 零品入库信息链表
     */
    List<ZeroProductStorageDO> getZeroProdStorage(Map<String, Object> params);

    /**
     * 新增零品入库信息
     *
     * @param zeroProdStorageDO 零品入库实体
     */
    void insert(ZeroProductStorageDO zeroProdStorageDO);

    /**
     * 删除零品入库信息
     *
     * @param id 零品入库主键
     */
    void delete(@Param("id") String id);

    /**
     * 编辑零品入库信息
     *
     * @param zeroProdStorageDO 零品入库实体
     */
    void updateZeroProdStorage(ZeroProductStorageDO zeroProdStorageDO);

    /**
     * 编辑审核信息
     *
     * @param zeroProdStorageDO 零品入库信息实体
     */
    void updateCheckerById(ZeroProductStorageDO zeroProdStorageDO);

}
