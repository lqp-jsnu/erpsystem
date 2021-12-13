package edu.yctc.erpsystem.constant;

/**
 * 一些返回值
 *
 * @author lqp
 */
public class ResultCode {

    /**
     * 成功
     */
    public static final int SUCCESS = 1;
    public static final String MSG_SUCCESS = "success";

    // 业务中细分的code 1000-1999
    /**
     * 用户未登录
     */
    public static final int USER_NOT_SIGN_IN = 1000;
    public static final String MSG_USER_NOT_SIGN_IN = "user not sign in";

    /**
     * 登陆错误
     */
    public static final int SIGN_IN_ERROR = 1001;
    public static final String MSG_SIGN_IN_ERROR = "sign in error";

    /**
     * 无用户错误
     */
    public static final int NO_SUCH_USER = 1002;
    public static final String MSG_NO_SUCH_USER = "no such user";

    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 1021;
    public static final String MSG_PASSWORD_ERROR = "password error";

    /**
     * 没有此用户权限缓存
     */
    public static final Integer NO_PERMISSION_CACHE = 1026;
    public static final String MSG_NO_PERMISSION_CACHE = "no permission cache";

    // 一些可能共性的异常code 9000~9999
    /**
     * 接口已下线
     */
    public static final int INTERFACE_OFFLINE = 9000;
    public static final String MSG_INTERFACE_OFFLINE = "interface is offline";

    /**
     * 数据库错误
     */
    public static final int DB_ERROR = 9001;
    public static final String MSG_DB_ERROR = "db error";

    /**
     * 参数非法
     */
    public static final int PARAMETER_INVALID = 9002;
    public static final String MSG_PARAMETER_INVALID = "parameter invalid";

    /**
     * 非法访问
     */
    public static final int ILLEGAL_ACCESS = 9003;
    public static final String MSG_ILLEGAL_ACCESS = "illegal access";

    /**
     * 外键约束
     */
    public static final int FOREIGN_KEY_CONSTRAINT = 9004;
    public static final String MSG_FOREIGN_KEY_CONSTRAINT = "foreign key constraint";

    /**
     * 文件无法删除
     */
    public static final int FILE_CANNOT_BE_DELETE = 9005;
    public static final String MSG_FILE_CANNOT_BE_DELETE = "file cannot be deleted";

    /**
     * 文件没有创建
     */
    public static final int FILE_CANNOT_BE_CREATE = 9006;
    public static final String MSG_FILE_CANNOT_BE_CREATE = "file cannot be create";

    /**
     * 文件不存在
     */
    public static final int FILE_IS_NOT_EXIST = 9007;
    public static final String MSG_FILE_IS_NOT_EXIST = "file is not exist";

    /**
     * 环境变量错误
     */
    public static final int ENVIRONMENT_VARIABLE_ERROR = 9008;
    public static final String MSG_ENVIRONMENT_VARIABLE_ERROR = "environment variable error";

    /**
     * 唯一约束条件错误
     */
    public static final int UNIQUE_CONSTRAINT_ERROR = 9009;
    public static final String MSG_UNIQUE_CONSTRAINT_ERROR = "unique constraint error";

    /**
     * 验证码错误
     */
    public static final int VERIFICATION_CODE_ERROR = 9010;
    public static final String MSG_VERIFICATION_CODE_ERROR = "verification code error";

    /**
     * 系统错误
     */
    public static final int ERROR_SYSTEM_EXCEPTION = 9999;
    public static final String MSG_ERROR_SYSTEM_EXCEPTION = "system error";

}
