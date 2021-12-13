package edu.yctc.erpsystem.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.entity.ResultDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sessonId对应业务id的托管
 *
 * @author lqp
 */
public class SessionContentHolder {

    private final static Logger logger = LoggerFactory.getLogger("sessionContentHolderLogger");

    /**
     * sessionId对应登录用户userId的Map
     */
    private static final Map<String, String> SESSION_2_SIGN_IN_USER_ID_MAP = new HashMap<>();

    /**
     * sessionId对应登录用户userLoginTime的Map
     */
    private static final Map<String, Date> SESSION_SIGN_IN_USER_TIME_MAP = new HashMap<>();

    /**
     * session失效判断时间周期为30分钟，单位为秒
     */
    private static final int SURVIVE_TIME = 30 * 60;

    /**
     * 失效session线程池
     */
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
    /**
     * 服务端最大session容量
     */
    private static final int SESSION_MAX_QUANTITY = 10000;

    static {
        // 登录用户的session失效（ScheduledThreadPoolExecutor的 scheduleAtFixedRate方法）
        executorService.scheduleAtFixedRate(() -> {
            ArrayList<Map.Entry<String, Date>> loginTimeList = new ArrayList<>(SESSION_SIGN_IN_USER_TIME_MAP.entrySet());
            loginTimeList.sort((Map.Entry<String, Date> o1, Map.Entry<String, Date> o2) -> {
                try {
                    if (o2.getValue() != null && o1.getValue() != null && o2.getValue().compareTo(o1.getValue()) > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                } catch (Exception e) {
                    logger.error("login time sort error, e={}", e);
                }
                return 0;
            });

            // 判断是否大于浏览器最大容量并删除map中对应的permission
            while (loginTimeList.size() >= SESSION_MAX_QUANTITY) {
                for (int i = 0; i <= loginTimeList.size() - SESSION_MAX_QUANTITY; i++) {
                    // key是通过loginTimeList获得的对应要删除的userId
                    String key = (loginTimeList.get(i)).getKey();
                    SESSION_2_SIGN_IN_USER_ID_MAP.remove(key);
                    SESSION_SIGN_IN_USER_TIME_MAP.remove(key);
                }
            }

        }, SURVIVE_TIME, SURVIVE_TIME, TimeUnit.MILLISECONDS);

        logger.info("user session has been deleted");
    }

    /**
     * 通过sessionId获取已经登录的userId
     *
     * @param sessionId 会话id
     * @return 用户id
     */
    public static ResultDO<String> getSignInUserIdBySessionId(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        if (!SESSION_2_SIGN_IN_USER_ID_MAP.containsKey(sessionId)) {
            return new ResultDO<>(false, ResultCode.USER_NOT_SIGN_IN, ResultCode.MSG_USER_NOT_SIGN_IN, null);
        }
        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, SESSION_2_SIGN_IN_USER_ID_MAP.get(sessionId));
    }

    /**
     * 添加登录用户session
     *
     * @param sessionId 会话id
     * @param userId 用户id
     * @return 是否成功
     */
    public static ResultDO<Void> addSignInUserId(String sessionId, String userId) {
        if (StringUtils.isEmpty(sessionId) || StringUtils.isEmpty(userId)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        if (SESSION_2_SIGN_IN_USER_ID_MAP.containsKey(sessionId) || SESSION_2_SIGN_IN_USER_ID_MAP.containsValue(userId)) {
            SESSION_2_SIGN_IN_USER_ID_MAP.remove(sessionId);
            SESSION_2_SIGN_IN_USER_ID_MAP.put(sessionId, userId);
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        SESSION_2_SIGN_IN_USER_ID_MAP.put(sessionId, userId);

        if (StringUtils.isEmpty(sessionId)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        SESSION_SIGN_IN_USER_TIME_MAP.put(sessionId, new Date());
        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
    }

    /**
     * 用sessionId删除登录用户session
     *
     * @param sessionId 会话id
     * @return 是否成功
     */
    public static ResultDO<Void> deleteSignInSessionBySessionId(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        if (!SESSION_2_SIGN_IN_USER_ID_MAP.containsKey(sessionId)) {
            return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
        }
        SESSION_2_SIGN_IN_USER_ID_MAP.remove(sessionId);
        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
    }

    /**
     * 判断是否登陆,返回userDO
     *
     * @param sessionId 会话id
     * @return 是否登陆
     */
    public static ResultDO<String> judgeSignin(String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }
        if (SESSION_2_SIGN_IN_USER_ID_MAP.containsKey(sessionId)) {
            String id = SESSION_2_SIGN_IN_USER_ID_MAP.get(sessionId);
            if (StringUtils.isEmpty(id)) {
                return new ResultDO<>(false, ResultCode.NO_SUCH_USER, ResultCode.MSG_NO_SUCH_USER, null);
            }
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, id);
        }
        return new ResultDO<>(false, ResultCode.USER_NOT_SIGN_IN, ResultCode.MSG_USER_NOT_SIGN_IN, null);
    }

}
