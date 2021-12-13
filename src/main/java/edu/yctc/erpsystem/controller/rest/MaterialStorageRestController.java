package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.MaterialStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialStorageVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 原材料入库逻辑数据接口
 *
 * @author wjd
 */
public interface MaterialStorageRestController {

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
     * @param materialStorageDO 待修改的信息
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialStorageDO materialStorageDO, HttpSession httpSession);

    /**
     * 删除记录
     *
     * @param materialStorage 原材料入库实体
     * @return 是否成功
     */
    ResultDO<Void> delete(MaterialStorageVO materialStorage);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialStorageDO 原材料入库实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialStorageDO materialStorageDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param materialStorage 原材料入库明细信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialStorageVO[] materialStorage);

}
