package edu.yctc.erpsystem.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.RestPath;
import edu.yctc.erpsystem.constant.StaticPath;
import edu.yctc.erpsystem.constant.TemplatePath;
import edu.yctc.erpsystem.service.ResourceInterService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * Shiro内置过滤器，可以实现权限相关的拦截器
 * 常用的过滤器：
 * anon: 无需认证（登录）可以访问
 * authc: 必须认证才可以访问
 * user: 如果使用rememberMe的功能可以直接访问
 * perms： 该资源必须得到资源权限才可以访问
 * role: 该资源必须得到角色权限才可以访问
 *
 * @author xiaotao
 */
@Configuration
public class ShiroConfig {

    @Resource
    private ResourceInterService resourceService;

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加Shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 无需登录的页面
        filterMap.put("/" + TemplatePath.INDEX_PATH, "anon");
        filterMap.put("/" + StaticPath.ERROR, "anon");
        filterMap.put("/img/*", "anon");

        // 需要资源权限的页面
        resourceService.getAllResources().getModule().forEach(item -> {
            if (null != item.getUrl() && !StringUtils.isBlank(item.getUrl()) && !ConstantHolder.MAIN_TABLE_HTML.equals(item.getUrl())) {
                filterMap.put(item.getUrl(), "perms[" + item.getId() + "]");
            }
        });

        // Rest请求需要的权限
        filterMap.put(RestPath.LOCK, "authc");
        filterMap.put(RestPath.UNLOCK, "anon");
        filterMap.put(RestPath.CHANGE_PASSWORD, "authc");

        filterMap.put(RestPath.DOWNLOAD, "authc");
        filterMap.put(RestPath.UPLOAD, "authc");
        filterMap.put(RestPath.GET_IMAGE, "authc");

        // 需要登录的页面
        filterMap.put("/" + TemplatePath.MAIN_HTML, "authc");
        filterMap.put("/" + TemplatePath.MAIN_TABLE_HTML, "authc");

        // 设置自定义的登录页面
        shiroFilterFactoryBean.setLoginUrl("/" + TemplatePath.USER_LOGIN);

        // 设置自定义的未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/" + TemplatePath.NO_AUTHORIZATION);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
