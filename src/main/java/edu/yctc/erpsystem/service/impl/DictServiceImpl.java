package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.DictDAO;
import edu.yctc.erpsystem.entity.DictDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.DictInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 数据字典逻辑接口实现
 *
 * @author xcg
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("dictService")
public class DictServiceImpl implements DictInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private DictDAO dictDAO;

    @Override
    public ResultDO<PageUtils<DictDO>> getDict(Map<String, Object> params) {
        return CallbackUtils.getCallback("getDict", params.toString(), () -> {
            List<DictDO> result = dictDAO.getDict(params);
            if (result == null) {
                logger.error("getDict exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(dictDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<Void> insert(DictDO dictDO) {
        if (StringUtils.isBlank(dictDO.getName()) || StringUtils.isBlank(dictDO.getValue()) ||StringUtils.isBlank(dictDO.getType())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        return CallbackUtils.insertCallback("Dict", dictDO.toString(), () -> dictDAO.insert(dictDO));
    }

    @Override
    public ResultDO<Void> delete(String id) {
        return CallbackUtils.deleteCallback("Dict", id, () -> dictDAO.delete(id));
    }

    @Override
    public ResultDO<Void> updateDict(DictDO dictDO) {
        if (StringUtils.isBlank(dictDO.getName()) || StringUtils.isBlank(dictDO.getValue()) ||StringUtils.isBlank(dictDO.getType())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.updateCallback("updateDict", dictDO.toString(), () -> dictDAO.updateDict(dictDO));
    }

}
