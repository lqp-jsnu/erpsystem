package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialStorageVO;

import java.util.Map;

/**
 * 原材料入库逻辑接口
 *
 * @author wjd
 */
public interface MaterialStorageInterService {

    /**
     * 获得所有原材料入库信息
     *
     * @param params 过滤参数
     * @return 原材料入库信息链表
     */
    ResultDO<PageUtils<MaterialStorageVO>> getMaterialStorage(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料入库信息
     *
     * @param params 过滤参数
     * @return 原材料入库信息链表
     */
    ResultDO<PageUtils<MaterialStorageVO>> getMaterialStorageByConditions(Map<String, Object> params);

    /**
     * 料品入库
     *
     * @param materialStorageDO 原材料入库实体
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialStorageDO materialStorageDO);

    /**
     * 删除记录
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialStorageDO 原材料入库实体
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialStorageDO materialStorageDO);

    /**
     * 导出excel
     *
     * @param materialStorageVO 原材料入库实体
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialStorageVO[] materialStorageVO);

}
