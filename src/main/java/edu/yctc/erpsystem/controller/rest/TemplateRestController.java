package edu.yctc.erpsystem.controller.rest;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.TemplateDO;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.TemplateVO;

import java.util.Map;

/**
 * 模板管理接口
 *
 * @author qiang
 */
public interface TemplateRestController {

    /**
     * 获得模板管理数据
     *
     * @param params 过滤参数
     * @return 模板管理链表
     */
    ResultDO<PageUtils<TemplateVO>> getTemplate(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param templateDO 实体
     * @return 是否成功
     */
    ResultDO<Void> insert(TemplateDO templateDO);

    /**
     * 删除
     *
     * @param templateDO 记录id
     * @return 是否成功
     */
    ResultDO<Void> delete(TemplateDO templateDO);

    /**
     * 修改模板管理信息
     *
     * @param templateDO 实体
     * @return 是否成功
     */
    ResultDO<Void> updateTemplate(TemplateDO templateDO);

    /**
     * 修改模板文件
     *
     * @param templateDO 模板文件路径
     * @return 是否成功
     */
    ResultDO<Void> updateUrl(TemplateDO templateDO);

}
