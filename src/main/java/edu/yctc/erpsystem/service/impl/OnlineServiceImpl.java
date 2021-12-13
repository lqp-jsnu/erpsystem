package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.OnlineDAO;
import edu.yctc.erpsystem.entity.OnlineDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.service.OnlineInterService;
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
 * 用户登录历史逻辑接口实现
 *
 * @author qiang
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("onlineService")
public class OnlineServiceImpl implements OnlineInterService {

    private final static Logger logger = LoggerFactory.getLogger("serviceLogger");

    @Resource
    private OnlineDAO onlineDAO;

    @Override
    public ResultDO<PageUtils<OnlineDO>> getOnline(Map<String, Object> params) {
        return CallbackUtils.getCallback("getOnline", params.toString(), () -> {
            // 获得所有客户信息
            List<OnlineDO> onlineList = onlineDAO.getOnline(params);

            if (onlineList == null) {
                logger.error("getOnline exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(onlineDAO.count(params), onlineList));
        });
    }

    @Override
    public ResultDO<Void> insert(OnlineDO onlineDO) {
        if (StringUtils.isBlank(onlineDO.getLoginName()) || StringUtils.isBlank(onlineDO.getIp()) || StringUtils.isBlank(onlineDO.getType())) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        return CallbackUtils.insertCallback("Online", onlineDO.toString(), () -> onlineDAO.insert(onlineDO));
    }

}
