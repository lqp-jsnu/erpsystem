package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialExpiryDateSetDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 原材料保质期设置DAO接口
 *
 * @author qiang
 */
public interface MaterialExpiryDateSetDAO {

    /**
     * 获得原材料保质期设置数据的数量
     *
     * @param params 过滤参数
     * @return 原材料保质期设置数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得原材料保质期设置数据
     *
     * @param params 过滤参数
     * @return 原材料数量设置链表
     */
    List<MaterialExpiryDateSetDO> getMaterialDateSet(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialExpiryDateSetDO 原材料保质期设置实体
     */
    void insert(MaterialExpiryDateSetDO materialExpiryDateSetDO);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(@Param("id") String id);

    /**
     * 修改原材料信息
     *
     * @param materialExpiryDateSetDO 原材料保质期设置实体
     */
    void updateMaterialDateSet(MaterialExpiryDateSetDO materialExpiryDateSetDO);

}
