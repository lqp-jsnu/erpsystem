package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialExpiryDateSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialExpiryDateSetVO;

import java.util.Map;

/**
 * 原材料保质期设置逻辑接口
 *
 * @author qiang
 */
public interface MaterialExpiryDateSetInterService {

    /**
     * 获得原材料保质期设置数据
     *
     * @param params 过滤参数
     * @return 原材料保质期设置链表
     */
    ResultDO<PageUtils<MaterialExpiryDateSetVO>> getMaterialDateSet(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料保质期设置信息
     *
     * @param params 过滤参数
     * @return 原材料保质期设置信息链表
     */
    ResultDO<PageUtils<MaterialExpiryDateSetVO>> getMaterialExpiryDateSetByConditions(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialExpiryDateSetDO 原材料保质期设置实体
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialExpiryDateSetDO materialExpiryDateSetDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改原材料保质期信息
     *
     * @param materialExpiryDateSetDO 原材料保质期设置
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialDateSet(MaterialExpiryDateSetDO materialExpiryDateSetDO);

}
