package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialNumberSetDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialNumberSetVO;

import java.util.Map;

/**
 * 原材料数量设置逻辑接口
 *
 * @author qiang
 */
public interface MaterialNumberSetInterService {

    /**
     * 获得原材料数量设置数据
     *
     * @param params 过滤参数
     * @return 原材料数量设置链表
     */
    ResultDO<PageUtils<MaterialNumberSetVO>> getMaterialNumberSet(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料数量设置信息
     *
     * @param params 过滤参数
     * @return 原材料数量设置信息链表
     */
    ResultDO<PageUtils<MaterialNumberSetVO>> getMaterialNumberSetByConditions(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialNumberSetDO 原材料数量设置实体
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialNumberSetDO materialNumberSetDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改原材料预警信息
     *
     * @param materialNumberSetDO 原材料数量设置实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialNumberSet(MaterialNumberSetDO materialNumberSetDO);

}
