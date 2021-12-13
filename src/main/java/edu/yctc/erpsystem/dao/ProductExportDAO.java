package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductExportDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成品出库DAO接口
 *
 * @author zzy
 */
public interface ProductExportDAO {

    /**
     * 新增成品出库信息
     *
     * @param productExportDO 成品出库实体
     */
    void insert(ProductExportDO productExportDO);

    /**
     * 通过主键获得成品出库信息
     *
     * @param id 主键
     * @return 成品出库信息
     */
    ProductExportDO getProductExportById(@Param("id") String id);

    /**
     * 通过主键获得所有成品出库信息
     *
     * @param ids 主键链表
     * @return 成品出库信息表
     */
    List<ProductExportDO> getProductExportByIds(@Param("ids") List<String> ids);

}
