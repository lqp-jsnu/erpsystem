package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.entity.ResultDO;

import static edu.yctc.erpsystem.util.FileUtils.isDirExists;

/**
 * 数据库备份工具类
 *
 * @author lqp
 */
public class MySqlDump {

    /**
     * 数据库备份
     *
     * @param saveName 数据库名字
     * @return 是否成功
     */
    public static ResultDO<String> mySqlDump(String saveName, String userName, String password) {
        // 判断文件夹是否存在 不存在创建文件夹
        if (!isDirExists(ConstantHolder.DB_SAVE_PATH)) {
            return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
        }

        try {
            // 组建创建命令
            String cmd = "mysqldump -u" + userName + " -p" + password + " --databases "+ ConstantHolder.DB_NAME + " -r " + ConstantHolder.DB_SAVE_PATH + "/" + saveName;
            // 执行命令
            Process process = Runtime.getRuntime().exec(cmd);
            if (0 == process.waitFor()) {
                return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
            }
            return new ResultDO<>(false, ResultCode.ENVIRONMENT_VARIABLE_ERROR, ResultCode.MSG_ENVIRONMENT_VARIABLE_ERROR, "");
        } catch (Exception e) {
            return new ResultDO<>(false, ResultCode.ENVIRONMENT_VARIABLE_ERROR, ResultCode.MSG_ENVIRONMENT_VARIABLE_ERROR, "");
        }
    }

}
