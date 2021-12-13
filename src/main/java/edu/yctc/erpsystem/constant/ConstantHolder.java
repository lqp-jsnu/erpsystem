package edu.yctc.erpsystem.constant;

/**
 * 一些常量
 *
 * @author lqp
 */
public class ConstantHolder {

    /**
     * 登陆地址
     */
    public static final String INDEX_PATH = "/ERPSystem";

    /**
     * 数据库保存路径
     */
    public static final String DB_SAVE_PATH = "C:/ERPSystem/dbBackUp/";

    /**
     * 数据库名
     */
    public static final String DB_NAME = "newerpsystem";

    /**
     * 主界面表格
     */
    public static final String MAIN_TABLE_HTML = "/admin/main-table";

    /**
     * 文件上传下载路径
     */
    public static final String FILE_SAVE_PATH = "C:/ERPSystem/file/";

    /**
     * 图片保存路径
     */
    public static final String IMG_SAVE_PATH = "C:/ERPSystem/img/";

    /**
     * 图纸保存路径
     */
    public static final String PRODUCT_IMG_SAVE_PATH = "C:/ERPSystem/productImg/";

    /**
     * 模板保存路径
     */
    public static final String TEMPLATE_SAVE_PATH = "C:/ERPSystem/file/template/";

    /**
     * excel模板路径
     */
    public static final String EXCEL_PATH = "C:/ERPSystem/excel/";

    /**
     * 文件类型--图片
     */
    public static final String IMG_FILE = "img";

    /**
     * 文件类型--图纸
     */
    public static final String PRODUCT_IMG_FILE = "productImg";

    /**
     * 文件类型--excel
     */
    public static final String EXCEL_FILE = "excel";

    /**
     * 文件类型--模板
     */
    public static final String TEMPLATE_FILE = "template";

    /**
     * 默认显示图片
     */
    public static final String DEFAULT_IMAGE = "default.jpg";

    /**
     * 外键约束错误码
     */
    public static final int FOREIGN_KEY_CONSTRAINT_ERROR_CODE = 1451;

    /**
     * 唯一约束条件错误码
     */
    public static final int UNIQUE_CONSTRAINT_ERROR_CODE = 1062;

    /**
     * 颜色最大值
     */
    public static final int MAX_COLOR_CODE = 255;

    /**
     * 原材料报废表名字
     */
    public static final String MATERIAL_DUMP_FILE_NAME = "原材料报废信息.xlsx";

    /**
     * 生产补单表名字
     */
    public static final String MATERIAL_INVENTORY_FILE_NAME = "原材料盘点信息.xlsx";

    /**
     * 料品主信息表名字
     */
    public static final String MATERIAL_INFO_MASTER_FILE_NAME = "料品主信息.xlsx";

    /**
     * 料品原始信息表名字
     */
    public static final String MATERIAL_INFO_FILE_NAME = "原材料信息.xlsx";

    /**
     * 客户信息表名字
     */
    public static final String CUSTOMER_FILE_NAME = "客户信息.xlsx";

    /**
     * 质量抽检表名字
     */
    public static final String QUALITY_SAMPLING_FILE_NAME = "质量抽检信息.xlsx";

    /**
     * 原材料采购表名字
     */
    public static final String MATERIAL_PURCHASE_FILE_NAME = "原材料订购单（发，亿，永）.xlsx";

    /**
     * 成品库存汇总表名字
     */
    public static final String PRODUCT_INVENTORY_SUMMARY_FILE_NAME = "成品库存汇总信息.xlsx";

    /**
     * 零品入库表名字
     */
    public static final String ZERO_PROD_STORAGE_FILE_NAME = "零品入库信息.xlsx";

    /**
     * 成品库存汇总表名字
     */
    public static final String MATERIAL_MATERIAL_INVENTORY_FILE_NAME = "成品原始信息.xlsx";

    /**
     * 原材料待入库表名字
     */
    public static final String MATERIAL_PURCHASE_STORED_FILE_NAME = "原材料采购单（发，亿，永）.xlsx";

    /**
     * 原材料入库名字
     */
    public static final String MATERIAL_STORED_FILE_NAME = "原材料入库表.xlsx";

    /**
     * 原材料库存信息表名字
     */
    public static final String MATERIAL_REPERTORY_FILE_NAME = "原材料库存信息.xlsx";

    /**
     * 原材料库存详细信息表名字
     */
    public static final String MATERIAL_REPERTORY_DETAIL_FILE_NAME = "原材料库存详细信息.xlsx";

    /**
     * 手动出库表名字
     */
    public static final String MANUAL_EXPORT_FILE_NAME = "手动出库信息.xlsx";

    /**
     * 未完结排程表名字
     */
    public static final String OPEN_SCHEDULE_FILE_NAME = "未完结排程信息.xlsx";

    /**
     * 已完结排程表名字
     */
    public static final String COMPLETED_SCHEDULE_FILE_NAME = "已完结排程信息.xlsx";

    /**
     * 制造流程单电阻表名字
     */
    public static final String FLOW_SHEET_PRINTED_SERVICE_IMPL = "制造流程单（电阻）（亿）.xlsx";

    /**
     * 制造流程单表弹簧名字
     */
    public static final String FLOW_SHEET_TAN_HUANG_SERVICE_IMPL = "制造流程单（弹簧）（永）.xlsx";

    /**
     * 客户订单表名字
     */
    public static final String CUSTOMER_ORDER_FILE_NAME = "客户订单信息.xlsx";

    /**
     * 客户订单明细表名字
     */
    public static final String CUSTOMER_DETAILS_FILE_NAME = "客户订单明细信息.xlsx";

    /**
     * 订单查询表名字
     */
    public static final String CUSTOMER_QUERY_FILE_NAME = "订单查询信息.xlsx";

    /**
     * 生产补单表名字
     */
    public static final String ISSUE_ORDER_FILE_NAME = "生产补单信息.xlsx";

    /**
     * 手动出库表名字
     */
    public static final String DETAILED_MANUAL_FACE_PROCESS_NAME = "制造流程单详细信息.xlsx";

    /**
     * 原材料出库信息表名字
     */
    public static final String MATERIAL_EXPORT_FILE_NAME = "原材料出库存单.xlsx";

    /**
     * 原材料出库明细信息表名字
     */
    public static final String MATERIAL_EXPORT_DETAIL_FILE_NAME = "材料出库详细存单.xlsx";

    /**
     * 成品入库表名字
     */
    public static final String PRODUCT_STORAGE_FILE_NAME = "成品入库信息.xlsx";

    /**
     * 成品入库明细表名字
     */
    public static final String PRODUCT_STORAGE_DETAILS_FILE_NAME = "成品入库明细信息.xlsx";

    /**
     * 成品合格率表名字
     */
    public static final String QUALIFICATION_RATE_OF_PRODUCT_FILE_NAME = "成品合格率信息.xlsx";

    /**
     * 成品库存表名字
     */
    public static final String PRODUCT_INVENTORY_REPERTORY_FILE_NAME = "成品库存信息.xlsx";

    /**
     * 成品盘点表名字
     */
    public static final String PRODUCT_INVENTORY_FILE_NAME = "成品库存汇总单.xlsx";

    /**
     * 出货单历史表名字
     */
    public static final String BILL_OF_SALE_HISTORY_FILE_NAME = "出货单历史信息.xlsx";

    /**
     * 送货单表名字
     */
    public static final String BILL_OF_SALE_ORDER_FILE_NAME = "送货单（发，亿，永）.xlsx";

    /**
     * 出货单表名字
     */
    public static final String BILL_OF_SALE_FILE_NAME = "出货单信息.xlsx";

}
