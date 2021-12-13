package edu.yctc.erpsystem.interceptor;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * excel样式设置
 *
 * @author xcg
 */
public class MaterialInfoExcelStyleHandler implements WriteHandler {

    /** 标题索引 */
    private static final int TITLE_INDEX = 0;

    /** 表头索引 */
    private static final int HEADER_INDEX = 1;

    /** 内容索引 */
    private static final int CONTENT_INDEX = 2;

    /** 单元格样式 */
    private CellStyle titleCellStyle;
    private CellStyle headerCellStyle;
    private CellStyle contentCellStyle;

    @Override
    public void sheet(int i, Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();

        titleCellStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setFontName("华文楷体");
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)20);
        titleFont.setColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
        titleCellStyle.setFont(titleFont);

        headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontName("黑体");
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short)10);
        headerFont.setColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
        headerCellStyle.setFont(headerFont);

        contentCellStyle = workbook.createCellStyle();
        Font contentFont = workbook.createFont();
        contentFont.setFontName("黑体");
        contentFont.setFontHeightInPoints((short)10);
        contentFont.setColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
        contentCellStyle.setFont(contentFont);

        setTitleStyle();
        setHeaderStyle();
        setContentStyle();
    }

    @Override
    public void row(int i, Row row) {
        if (i == 0) {
            row.setHeight((short)(4*256));
        } else {
            row.setHeight((short)(1.7*256));
        }
    }

    @Override
    public void cell(int i, Cell cell) {
        // 标题
        if (cell.getRowIndex() == TITLE_INDEX) {
            cell.setCellStyle(titleCellStyle);
        }

        // 标题
        if (cell.getRowIndex() == HEADER_INDEX) {
            cell.setCellStyle(headerCellStyle);
        }

        // 内容
        if (cell.getRowIndex() >= CONTENT_INDEX) {
            cell.setCellStyle(contentCellStyle);
        }
    }

    /**
     * 设置标题样式
     */
    private void setTitleStyle() {
        // 水平对齐方式
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFillBackgroundColor((short)22);
        // 垂直对齐方式
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 设置表头样式
     */
    private void setHeaderStyle() {
        // 边框颜色
        headerCellStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        headerCellStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        headerCellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        headerCellStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());

        // 下边框
        headerCellStyle.setBorderBottom(BorderStyle.THICK);
        // 左边框
        headerCellStyle.setBorderLeft(BorderStyle.THICK);
        // 右边框
        headerCellStyle.setBorderRight(BorderStyle.THICK);
        // 上边框
        headerCellStyle.setBorderTop(BorderStyle.THICK);
        // 设置自动换行
        headerCellStyle.setWrapText(false);
        // 水平对齐方式
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFillBackgroundColor((short)22);
        // 垂直对齐方式
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 设置内容样式
     */
    private void setContentStyle() {
        // 边框颜色
        contentCellStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        contentCellStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        contentCellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        contentCellStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());

        // 下边框
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        // 右边框
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        // 上边框
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        // 设置自动换行
        contentCellStyle.setWrapText(false);
        // 水平对齐方式
        contentCellStyle.setAlignment(HorizontalAlignment.CENTER);
        contentCellStyle.setFillBackgroundColor((short)22);
        // 垂直对齐方式
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

}
