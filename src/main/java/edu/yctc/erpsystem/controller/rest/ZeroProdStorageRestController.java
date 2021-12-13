package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ZeroProductStorageDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ZeroProductStorageVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 零品入库数据接口
 *
 * @author zzy
 */
public interface ZeroProdStorageRestController {

    /**
     * 获得所有零品入库信息
     *
     *@param params 过滤参数
     * @return 零品入库信息链表
     */
    ResultDO<PageUtils<ZeroProductStorageVO>> getZeroProductStorage(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的零品入库信息
     *
     * @param params 过滤参数
     * @return 零品入库信息链表
     */
    ResultDO<PageUtils<ZeroProductStorageVO>> getZeroProductStorageByConditions(Map<String, Object> params);

    /**
     * 增加零品入库信息
     *
     * @param zeroProdStorageDO 零品入库实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(ZeroProductStorageDO zeroProdStorageDO, HttpSession httpSession);

    /**
     * 删除
     *
     * @param zeroProdStorageDO 零品入库id
     * @return 是否成功
     */
    ResultDO<Void> delete(ZeroProductStorageDO zeroProdStorageDO);

    /**
     * 编辑零品入库信息
     *
     * @param zeroProdStorageDO 零品入库实体
     * @return 是否成功
     */
    ResultDO<Void> updateZeroProductStorage(ZeroProductStorageDO zeroProdStorageDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param zeroProdStorageDO 零品入库实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(ZeroProductStorageDO zeroProdStorageDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param zeroProdStorageVO 零品入库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ZeroProductStorageVO[] zeroProdStorageVO);

}
