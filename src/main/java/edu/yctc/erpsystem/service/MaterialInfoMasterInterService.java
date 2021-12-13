package edu.yctc.erpsystem.service;

import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;

import java.util.Map;

/**
 * 原材料主表业务逻辑接口
 *
 * @author qiang
 */
public interface MaterialInfoMasterInterService {

    /**
     * 获得所有料品主信息
     *
     * @param params 过滤参数
     * @return 数据字典信息链表
     */
    ResultDO<PageUtils<MaterialInfoMasterDO>> getMaterialInfoMaster(Map<String, Object> params);

    /**
     * 添加一条料品主信息
     *
     * @param materialInfoMasterDO 料品主信息实体
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialInfoMasterDO materialInfoMasterDO);

    /**
     * 删除
     *
     * @param id 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(String id);

    /**
     * 修改料品主信息
     *
     * @param materialInfoMasterDO 数据字典实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialInfoMaster(MaterialInfoMasterDO materialInfoMasterDO);

    /**
     * 导出excel
     *
     * @param materialInfoMasterDO 数据字典实体
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialInfoMasterDO[] materialInfoMasterDO);

}
