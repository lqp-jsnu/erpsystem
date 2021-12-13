package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductAllocationDO;

/**
 * 成品调拨DAO接口
 *
 * @author zzy
 */
public interface ProductAllocationDAO {

    /**
     * 新增成品调拨信息
     *
     * @param productAllocationDO 成品调拨实体
     */
    void insert(ProductAllocationDO productAllocationDO);

}
