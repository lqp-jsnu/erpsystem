package edu.yctc.erpsystem.controller;

import javax.servlet.http.HttpSession;

/**
 * 系统监控页面接口
 *
 * @author lqp
 */
public interface SystemMonitoringController {

    /**
     * 展示项目监控页面
     *
     * @param httpSession session
     * @return 项目监控页面
     */
    String showProjectMonitoring(HttpSession httpSession);

    /**
     * 展示数据源监控页面
     *
     * @param httpSession session
     * @return 数据源监控页面
     */
    String showDataSourceMonitoring(HttpSession httpSession);

    /**
     * 展示用户登录历史监控页面
     *
     * @param httpSession session
     * @return 用户登录历史监控页面
     */
    String showUserLoginHistoryMonitoring(HttpSession httpSession);

    /**
     * 展示数据库备份页面
     *
     * @param httpSession session
     * @return 数据库备份页面
     */
    String showDbBackUp(HttpSession httpSession);

}
