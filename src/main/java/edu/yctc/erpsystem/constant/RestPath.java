package edu.yctc.erpsystem.constant;

/**
 * Rest路径
 *
 * @author lqp
 */
public class RestPath {

    /** 锁定窗口 */
    public static final String LOCK = "/user/lock";
    /** 解锁窗口 */
    public static final String UNLOCK = "/user/unlock";
    /** 修改密码 */
    public static final String CHANGE_PASSWORD = "/user/changePassword";

    /** 下载文件 */
    public static final String DOWNLOAD = "/file/download";
    /** 上传文件 */
    public static final String UPLOAD = "/file/upload";
    /** 获取图片 */
    public static final String GET_IMAGE = "/file/img/{imageName}";

}
