package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.bo.MaterialExportBO;
import edu.yctc.erpsystem.entity.MaterialInventoryMasterDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialInventoryMasterVO;

import java.util.List;
import java.util.Map;

/**
 * 材料库存逻辑接口
 *
 * @author wjd
 */
public interface MaterialInventoryMasterInterService {

    /**
     * 获得所有材料库存信息
     *
     * @param params 过滤参数
     * @return 材料库存信息链表
     */
    ResultDO<PageUtils<MaterialInventoryMasterVO>> getMaterialRepertory(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的材料库存信息
     *
     * @param params 过滤参数
     * @return 材料库存表
     */
    ResultDO<PageUtils<MaterialInventoryMasterVO>> getMaterialRepertoryByConditions(Map<String, Object> params);

    /**
     * 生产出库
     *
     * @param materialExportParam 业务逻辑对象
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialExportBO materialExportParam);

    /**
     * 导出excel
     *
     * @param materialInventoryMasterVO 生成盘点信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialInventoryMasterVO[] materialInventoryMasterVO);

    /**
     * 导出excelDetail
     *
     * @param materialInventoryMasterVO 原材料待入库信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportDetailExcel(List<MaterialInventoryMasterVO> materialInventoryMasterVO);

}
