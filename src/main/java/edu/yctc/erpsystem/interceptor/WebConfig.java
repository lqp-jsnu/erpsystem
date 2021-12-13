package edu.yctc.erpsystem.interceptor;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.TemplatePath;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 进入登陆页面
 *
 * @author xiaotao
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(ConstantHolder.INDEX_PATH).setViewName(TemplatePath.INDEX_PATH);
    }

}

