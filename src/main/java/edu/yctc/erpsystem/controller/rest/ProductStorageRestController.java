package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ProductStorageDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ProductStorageVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 成品入库信息数据接口
 *
 * @author zzy
 */
public interface ProductStorageRestController {

    /**
     * 获得所有成品入库信息
     *
     *@param params 过滤参数
     * @return 成品入库信息链表
     */
    ResultDO<PageUtils<ProductStorageVO>> getProductStorage(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的成品入库信息
     *
     * @param params 过滤参数
     * @return 成品入库信息链表
     */
    ResultDO<PageUtils<ProductStorageVO>> getProductStorageByConditions(Map<String, Object> params);

    /**
     *  增加成品入库信息
     *
     * @param productStorageDO 成品入库实体
     * @param httpSession session
     * @return  是否成功
     */
    ResultDO<Void> insert(ProductStorageDO productStorageDO, HttpSession httpSession);

    /**
     * 删除成品入库记录
     *
     * @param productStorageDO 成品入库实体
     * @return 是否成功
     */
    ResultDO<Void> delete(ProductStorageDO productStorageDO);

    /**
     *  编辑成品入库信息
     *
     * @param productStorageDO 成品入库实体
     * @return 是否成功
     */
    ResultDO<Void> updateProductStorage(ProductStorageDO productStorageDO);

    /**
     * 审核从入库信息
     *
     * @param productStorageDO 成品入库实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(ProductStorageDO productStorageDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param productStorageVO 成品入库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ProductStorageVO[] productStorageVO);

}
