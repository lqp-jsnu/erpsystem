package edu.yctc.erpsystem.shiro;

import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.UserDO;
import edu.yctc.erpsystem.service.ResourceInterService;
import edu.yctc.erpsystem.service.UserInterService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 *
 * @author xiaotao
 */
public class UserRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger("controller");

    @Autowired
    private UserInterService userService;

    @Autowired
    private ResourceInterService resourceService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户id
        String userId;
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject == null) {
                logger.error("authorization subject is null, principalCollection={}", principals);
                return null;
            }
            userId = (String) subject.getPrincipal();
            if (StringUtils.isBlank(userId)) {
                logger.error("authorization subject is null, principalCollection={}, subject={}", principals, subject);
                return null;
            }
        } catch (Exception e) {
            logger.error("get userDO error, principalCollection={}", principals, e);
            return null;
        }

        // 获取用户权限资源
        List<ResourceDO> permissions;
        try {
            ResultDO<List<ResourceDO>> result = resourceService.getAllResourcesByUserId(userId);
            if (!result.isSuccess()) {
                logger.error("get permissions by userDO error, principalCollection={}, userId={}", principals, userId);
                return null;
            }
            permissions = result.getModule();
        } catch (Exception e) {
            logger.error("get permissions error, principalCollection={}, userId={}", principals, userId, e);
            return null;
        }

        // 添加资源的授权字符串
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        for (ResourceDO resource : permissions) {
            info.addStringPermission(resource.getId());
        }

        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断用户名
        UserDO userDO;
        try {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

            if (token == null) {
                logger.error("token is null");
                return null;
            }

            ResultDO<UserDO> getUserByNumberResultDO = userService.getUserByLoginName(token.getUsername());
            if (!getUserByNumberResultDO.isSuccess()) {
                // shiro底层会抛出UnknowAccountException
                return null;
            }

            userDO = getUserByNumberResultDO.getModule();
            if (userDO == null) {
                logger.error("authentication userDO is null, authenticationToken={}", authenticationToken);
                return null;
            }
        } catch (Exception e) {
            logger.error("authentication error, authenticationToken={}", authenticationToken, e);
            return null;
        }
        // 判断密码
        logger.info("shiro authentication success, authenticationToken={}, userDO={}", authenticationToken, userDO);
        return new SimpleAuthenticationInfo(userDO.getId(), userDO.getPwd(), "");
    }

}
