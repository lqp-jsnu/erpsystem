package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.DictDAO;
import edu.yctc.erpsystem.dao.TemplateDAO;
import edu.yctc.erpsystem.entity.DictDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.TemplateDO;
import edu.yctc.erpsystem.service.TemplateInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.FileUtils;
import edu.yctc.erpsystem.util.PageUtils;
import edu.yctc.erpsystem.vo.TemplateVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 模板管理逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("templateService")
public class TemplateServiceImpl implements TemplateInterService {
    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private TemplateDAO templateDAO;

    @Resource
    private DictDAO dictDAO;

    @Override
    public ResultDO<PageUtils<TemplateVO>> getTemplate(Map<String, Object> params) {
        return CallbackUtils.getCallback("getTemplate", params.toString(), () -> {
            List<TemplateVO> templateViewList = new ArrayList<>();

            // 获得所有模板信息
            List<TemplateDO> templateList = templateDAO.getTemplate(params);
            if (templateList == null) {
                logger.error("getTemplate exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }
            // 如果没有记录直接返回
            if (0 == templateList.size()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(0, templateViewList));
            }

            // 获得所有供应商信息
            List<DictDO> dicList = dictDAO.getDictByIds( templateList.stream().map( TemplateDO::getDictId ).collect( Collectors.toList() ) );
            Map<String, DictDO> dicMap = dicList.stream().collect( Collectors.toMap( DictDO::getId, dic -> dic ) );

            for (TemplateDO temp : templateList){
                TemplateVO templateView = new TemplateVO();

                templateView.setTypeText(dicMap.get(temp.getDictId()).getValue());
                templateView.setId(temp.getId());
                templateView.setName(temp.getName());
                templateView.setDictId(temp.getDictId());
                templateView.setUrl(temp.getUrl());
                templateView.setRemark(temp.getRemark());
                templateView.setCreateTime(temp.getCreateTime());
                templateView.setUpdateTime(temp.getUpdateTime());

                templateViewList.add(templateView);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(templateDAO.count(params), templateViewList));
        });
    }

    @Override
    public ResultDO<Void> insert(TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getName()) || StringUtils.isBlank(templateDO.getDictId())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Template", templateDO.toString(), () -> templateDAO.insert(templateDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Template", id, () -> {
            String url = templateDAO.getTemplateById(id).getUrl();
            templateDAO.delete(id);
            FileUtils.deleteFile(ConstantHolder.TEMPLATE_SAVE_PATH, url);
        });
    }

    @Override
    public ResultDO<Void> updateTemplate(TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getId()) || StringUtils.isBlank(templateDO.getDictId()) || StringUtils.isBlank(templateDO.getRemark())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateTemplate", templateDO.toString(), () -> templateDAO.updateTemplate(templateDO));
    }

    @Override
    public ResultDO<Void> updateUrl(TemplateDO templateDO) {
        if (StringUtils.isBlank(templateDO.getId()) || StringUtils.isBlank(templateDO.getName()) || StringUtils.isBlank(templateDO.getUrl())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateUrl", templateDO.toString(), () -> {
            String url = templateDAO.getTemplateById(templateDO.getId()).getUrl();
            templateDAO.updateUrl(templateDO);
            FileUtils.deleteFile(ConstantHolder.TEMPLATE_SAVE_PATH, url);
        });
    }

}
