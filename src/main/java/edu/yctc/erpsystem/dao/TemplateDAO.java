package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.TemplateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 模板管理DAO接口
 *
 * @author qiang
 */
@Mapper
public interface TemplateDAO {

    /**
     * 获得模板管理数据的数量
     *
     * @param params 过滤参数
     * @return 模板管理数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得模板管理数据
     *
     * @param params 过滤参数
     * @return 模板管理链表
     */
    List<TemplateDO> getTemplate(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param templateDO 模板管理实体
     */
    void insert(TemplateDO templateDO);

    /**
     * 删除
     *
     * @param id 模板管理id
     */
    void delete(@Param("id") String id);

    /**
     * 修改模板信息
     *
     * @param templateDO 模板管理实体
     */
    void updateTemplate(TemplateDO templateDO);

    /**
     * 修改模板路径
     *
     * @param templateDO 模板路径
     */
    void updateUrl(TemplateDO templateDO);

    /**
     * 通过主键获得模板信息
     *
     * @param id 主键
     * @return 模板信息
     */
    TemplateDO getTemplateById(@Param("id") String id);

    /**
     * 通过主键获得所有模板信息
     *
     * @param ids 主键链表
     * @return 模板信息表
     */
    List<TemplateDO> getTemplateByIds(@Param("ids") List<String> ids);

}
