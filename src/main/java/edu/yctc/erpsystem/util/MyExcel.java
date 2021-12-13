package edu.yctc.erpsystem.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import edu.yctc.erpsystem.constant.ConstantHolder;
import edu.yctc.erpsystem.constant.ResultCode;
import edu.yctc.erpsystem.entity.ResultDO;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

import static edu.yctc.erpsystem.util.FileUtils.isDirExists;

/**
 * excel操作工具类
 *
 * @author lqp
 */
public class MyExcel {

    /**
     * 写入excel
     *
     * @param filePath 文件路径
     * @param model excel模型
     * @param date 数据
     * @param columnWidth 列宽
     * @param writeHandler 样式
     * @return 是否成功
     */
    static public ResultDO<Void> write(String filePath, Class<? extends com.alibaba.excel.metadata.BaseRowModel> model, List<? extends com.alibaba.excel.metadata.BaseRowModel> date, Map<Integer,Integer> columnWidth, WriteHandler writeHandler){
        // 判断文件夹是否存在 不存在创建文件夹
        if (!isDirExists(ConstantHolder.FILE_SAVE_PATH)) {
            return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
        }

        try (OutputStream out = new FileOutputStream(filePath)) {
            ExcelWriter writer = new ExcelWriter(null, out, ExcelTypeEnum.XLSX, true, writeHandler);
            Sheet sheet1 = new Sheet(1, 0, model);
            // 设置列宽 设置每列的宽度
            sheet1.setColumnWidthMap(columnWidth);

            sheet1.setSheetName("sheet1");
            writer.write(date, sheet1);
            writer.finish();
            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
        } catch (Exception e) {
            return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
        }
    }

    /**
     * 读取excel
     *
     * @param filePath 文件路径
     * @param model excel模型
     * @return 数据链表
     */
    static public ResultDO<List<Object>> read(String filePath, Class<? extends com.alibaba.excel.metadata.BaseRowModel> model) {
        try {
            // 将读取的数据，转化成对应的实体，需要 extends BaseRowModel
            Sheet sheet = new Sheet(1, 1, model);
            // 这里 取出来的是 ExcelModel实体 的集合
            List<Object> readList = EasyExcelFactory.read(new FileInputStream(filePath), sheet);

            return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, readList);
        } catch (FileNotFoundException e) {
            return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
        }
    }

    /**
     * 根据excel模板导出excel
     *
     * @param modelPath 模板路径
     * @param filePath 导出文件名
     * @param map 数据
     * @return 是否成功
     */
    static public ResultDO<Void> writeTemplate(String modelPath, String filePath, Map<String, Object> map) {
        // 判断文件夹是否存在 不存在创建文件夹
        if (!isDirExists(ConstantHolder.EXCEL_PATH) || !isDirExists(ConstantHolder.FILE_SAVE_PATH)) {
            return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
        }

        XLSTransformer transformer = new XLSTransformer();

        try (
            InputStream is = new FileInputStream(ConstantHolder.EXCEL_PATH + modelPath)
        ) {
            XSSFWorkbook workBook = (XSSFWorkbook) transformer.transformXLS(is, map);
            workBook.setActiveSheet(0);
            OutputStream os = new FileOutputStream(ConstantHolder.FILE_SAVE_PATH + filePath);
            workBook.write(os);
            os.flush();
        } catch (IOException e) {
            return new ResultDO<>(false, ResultCode.FILE_IS_NOT_EXIST, ResultCode.MSG_FILE_IS_NOT_EXIST, null);
        } catch (InvalidFormatException e) {
            return new ResultDO<>(false, ResultCode.FILE_CANNOT_BE_CREATE, ResultCode.MSG_FILE_CANNOT_BE_CREATE, null);
        }

        return new ResultDO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, null);
    }

}
