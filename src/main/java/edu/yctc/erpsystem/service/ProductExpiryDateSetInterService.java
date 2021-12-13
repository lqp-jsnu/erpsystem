package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ProductExpiryDateSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductExpiryDateSetVO;

import java.util.Map;

/**
 * 产品保质期设置逻辑接口
 *
 * @author qiang
 */
public interface ProductExpiryDateSetInterService {

    /**
     * 获得成品保质期设置数据
     *
     * @param params 过滤参数
     * @return 产品保质期设置链表
     */
    ResultDO<PageUtils<ProductExpiryDateSetVO>> getExpiryDateSet(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品保质期设置信息
     *
     * @param params 过滤参数
     * @return 成品保质期设置信息链表
     */
    ResultDO<PageUtils<ProductExpiryDateSetVO>> getProductExpiryDateSetByConditions(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param productExpiryDateSetDO 产品保质期设置实体
     * @return 是否成功
     */
    ResultDO<Void> insert(ProductExpiryDateSetDO productExpiryDateSetDO);

    /**
     * 删除
     *
     * @param  id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改产品信息
     *
     * @param productExpiryDateSetDO 产品保质期设置实体
     * @return 是否成功
     */
    ResultDO<Void> updateExpiryDateSet(ProductExpiryDateSetDO productExpiryDateSetDO);

}
