package edu.yctc.erpsystem.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 读取配置文件
 *
 * @author xiaotao
 */
@Configuration
public class ConfigBeanValue {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ConfigBeanValue{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}