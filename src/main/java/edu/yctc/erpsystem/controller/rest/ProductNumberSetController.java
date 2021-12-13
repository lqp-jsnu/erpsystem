package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ProductNumberSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductNumberSetVO;

import java.util.Map;

/**
 * 产品数量设置接口
 *
 * @author qiang
 */
public interface ProductNumberSetController {

    /**
     * 获得成品数量设置数据
     *
     * @param params 过滤参数
     * @return 产品数量设置链表
     */
    ResultDO<PageUtils<ProductNumberSetVO>> getProductNumberSet(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品数量设置信息
     *
     * @param params 过滤参数
     * @return 成品数量设置信息链表
     */
    ResultDO<PageUtils<ProductNumberSetVO>> getProductNumberSetByConditions(Map<String, Object> params);

    /**
     * 删除
     *
     * @param productNumberSetDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(ProductNumberSetDO productNumberSetDO);

    /**
     * 插入一条记录
     *
     * @param productNumberSetDO 产品数量设置实体
     * @return 是否成功
     */
    ResultDO<Void> insert(ProductNumberSetDO productNumberSetDO);

    /**
     * 修改产品信息
     *
     * @param productNumberSetDO 产品数量设置实体
     * @return 是否成功
     */
    ResultDO<Void> updateProductNumberSet(ProductNumberSetDO productNumberSetDO);

}
