package edu.yctc.erpsystem.service.impl;

import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.dao.DbBackUpDAO;
import edu.yctc.erpsystem.entity.DbBackUpDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.interceptor.ConfigBeanValue;
import edu.yctc.erpsystem.service.DbBackUpInterService;
import edu.yctc.erpsystem.util.CallbackUtils;
import edu.yctc.erpsystem.util.MySqlDump;
import edu.yctc.erpsystem.util.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据库备份逻辑接口实现
 *
 * @author lqp
 */
@ComponentScan({"edu.yctc.erpsystem.dao"})
@Service("dbBackUpService")
public class DbBackUpServiceImpl implements DbBackUpInterService {

    private final static Logger logger = LoggerFactory.getLogger("service");

    @Resource
    private DbBackUpDAO dbBackUpDAO;

    @Resource
    private ConfigBeanValue configBeanValue;

    @Override
    public ResultDO<PageUtils<DbBackUpDO>> getDbBackUps(Map<String, Object> params) {
        return CallbackUtils.getCallback("getMaterialDump", params.toString(), () -> {
            List<DbBackUpDO> result = dbBackUpDAO.getDbBackUp(params);
            if (result == null) {
                logger.error("getDbBackUps exception, db error, params={}", params);
                return new ResultDO<>(false, ResultCode.DB_ERROR, ResultCode.MSG_DB_ERROR, null);
            }

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, new PageUtils<>(dbBackUpDAO.count(params), result));
        });
    }

    @Override
    public ResultDO<Void> deleteByFileNames(List<String> fileNames) {
        if (fileNames == null || fileNames.size() == 0) {
            return new ResultDO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID, null);
        }

        try {
            // 删除记录
            dbBackUpDAO.deleteByFileNames(fileNames);

            // 删除数据库备份文件
            for (String filename: fileNames) {
                File file = new File(ConstantHolder.DB_SAVE_PATH + filename);
                if (file.exists() && file.isFile()) {
                    if (!file.delete()){
                        return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_DELETE, ResultCode.MSG_FILE_CANNOT_BE_DELETE, null);
                    }
                } else {
                    logger.error("deleteByFileNames info, dbName={} is not exist!", filename);
                    return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
                }
            }

            logger.info("delete dbBackUp by fileNames success");
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            logger.error("deleteByFileNames exception, system error, fileNames={}, e={}", fileNames, e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

    @Override
    public ResultDO<String> insert() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String dbName = "ERPSystem" + simpleDateFormat.format(date) + ".sql";
        String dbUrl = ConstantHolder.DB_SAVE_PATH + dbName;

        DbBackUpDO dbBackUpDO = new DbBackUpDO();
        dbBackUpDO.setFileName(dbName);
        dbBackUpDO.setFileUrl(dbUrl);

        // 数据库备份
        try {
            ResultDO<String> result = MySqlDump.mySqlDump(dbName, configBeanValue.getUsername(), configBeanValue.getPassword());
            if (!result.isSuccess()) {
                return result;
            }
        } catch (Exception e) {
            logger.error("mySqlDump error, dbName={}", dbName, e);
        }

        try {
            dbBackUpDAO.insert(dbBackUpDO);
            logger.info("insert dbBackUp success, fileName={}", dbBackUpDO.getFileName());
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, dbUrl);
        } catch (Exception e) {
            logger.error("dbBackUp insert exception, system error, fileName={}, e={}", dbBackUpDO.getFileName(), e);
            return new ResultDO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION, null);
        }
    }

}
