package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 原材料数据接口
 *
 * @author qiang
 */
public interface MaterialInfoMasterRestController {

    /**
     * 获得料品主信息
     *
     * @param params 过滤参数
     * @return 料品主信息链表
     */
    ResultDO<PageUtils<MaterialInfoMasterDO>> getMaterialInfoMaster(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialInfoMasterDO 料品主信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialInfoMasterDO materialInfoMasterDO);


    /**
     * 删除
     *
     * @param materialInfoMasterDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(MaterialInfoMasterDO materialInfoMasterDO);

    /**
     * 修改料品主信息
     *
     * @param materialInfoMasterDO 料品主信息实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialInfoMaster(MaterialInfoMasterDO materialInfoMasterDO);

    /**
     * 导出excel
     *
     * @param materialInfoMasterDO 料品主信息实体
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialInfoMasterDO[] materialInfoMasterDO);

}
