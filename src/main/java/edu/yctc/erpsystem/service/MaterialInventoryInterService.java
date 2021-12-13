package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MainMaterialExpiryDateVO;
import edu.yctc.erpsystem.vo.MainMaterialNumberVO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;

import java.util.Map;

/**
 * 材料盘点逻辑接口
 *
 * @author lqp
 */
public interface MaterialInventoryInterService {

    /**
     * 获得所有获得所有材料盘点信息
     *
     * @param params 过滤参数
     * @return 材料信息链表
     */
    ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventory(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的材料库存信息
     *
     * @param params 过滤参数
     * @return 材料库存表
     */
    ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryByConditions(Map<String, Object> params);

    /**
     * 导出excel
     *
     * @param materialInventories 生成盘点信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialInventoryVO[] materialInventories);

    /**
     * 通过供应商搜索材料信息
     *
     * @param params 搜索信息
     * @return 材料信息链表
     */
    ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryBySearchSuppId(Map<String, Object> params);

    /**
     * 获得材料保质期监控信息
     *
     * @param params 过滤参数
     * @return 材料保质期监控信息链表
     */
    ResultDO<PageUtils<MainMaterialExpiryDateVO>> getMainMaterialExpiryDate(Map<String, Object> params);

    /**
     * 获得材料数量监控信息
     *
     * @param params 过滤参数
     * @return 材料数量监控信息链表
     */
    ResultDO<PageUtils<MainMaterialNumberVO>> getMainMaterialNumber(Map<String, Object> params);

    /**
     * 通过仓库名称搜索材料信息
     *
     * @param params 搜索信息
     * @return 材料信息链表
     */
    ResultDO<PageUtils<MaterialInventoryVO>> getMaterialInventoryBySearchWarehouse(Map<String, Object> params);

}
