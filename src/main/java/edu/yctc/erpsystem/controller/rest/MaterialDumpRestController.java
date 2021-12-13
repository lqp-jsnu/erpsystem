package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.MaterialDumpDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.MaterialDumpVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 原材料报废逻辑数据接口
 *
 * @author lqp
 */
public interface MaterialDumpRestController {

    /**
     * 获得所有原材料报废信息
     *
     * @param params 过滤参数
     * @return 原材料报废信息链表
     */
    ResultDO<PageUtils<MaterialDumpVO>> getMaterialDump(Map<String, Object> params);

    /**
     * 通过条件获得过滤后的原材料报废信息
     *
     * @param params 过滤参数
     * @return 原材料报废信息链表
     */
    ResultDO<PageUtils<MaterialDumpVO>> getMaterialDumpByConditions(Map<String, Object> params);

    /**
     * 增加原材料报废信息
     *
     * @param materialDumpDO 原材料报废实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> insert(MaterialDumpDO materialDumpDO, HttpSession httpSession);

    /**
     * 删除
     *
     * @param materialDumpDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(MaterialDumpDO materialDumpDO);

    /**
     * 通过id修改dumpAmount dumpDate dumpReason
     *
     * @param materialDumpDO 原材料报废实体
     * @return 是否成功
     */
    ResultDO<Void> updateMaterialDump(MaterialDumpDO materialDumpDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialDumpDO 原材料报废实体
     * @param httpSession session
     * @return 是否成功
     */
    ResultDO<Void> updateCheckerById(MaterialDumpDO materialDumpDO, HttpSession httpSession);

    /**
     * 导出excel
     *
     * @param materialDumpVO 原材料报废信息视图
     * @return 是否成功
     */
    ResultDO<Void> exportExcel(MaterialDumpVO[] materialDumpVO);

}
