package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.ZeroProductStorageDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.ZeroProductStorageVO;

import java.util.Map;

/**
 * 零品入库逻辑接口
 *
 * @author zzy
 */
public interface ZeroProductStorageInterService {

    /**
     * 获得所有零品入库信息
     *
     * @param params  过滤参数
     * @return  零品入库信息链表
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
     * 插入零品入库信息
     *
     * @param zeroProdStorageDO 零品入库实体
     * @return 是否成功
     */
    ResultDO<Void> insert(ZeroProductStorageDO zeroProdStorageDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 编辑零品入库信息
     *
     * @param zeroProdStorageDO 零品入库实体
     * @return 是否成功
     */
    ResultDO<Void> updateZeroProductStorage(ZeroProductStorageDO zeroProdStorageDO);

    /**
     *  通过id修改checkerId checkerFlag
     *
     * @param zeroProdStorageDO 待审核的信息
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(ZeroProductStorageDO zeroProdStorageDO);

    /**
     * 导出excel
     *
     * @param zeroProdStorageVO 零品入库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(ZeroProductStorageVO[] zeroProdStorageVO);

}
