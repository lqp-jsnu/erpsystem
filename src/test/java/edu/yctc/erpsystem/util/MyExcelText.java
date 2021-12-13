package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.dao.InvoiceTitleDAO;
import edu.yctc.erpsystem.dao.SupplierDAO;
import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.entity.SupplierDO;
import edu.yctc.erpsystem.excel.CustomerModel;
import edu.yctc.erpsystem.excel.MaterialDumpModel;
import edu.yctc.erpsystem.excel.MaterialPurchaseModel;
import edu.yctc.erpsystem.interceptor.MaterialDumpExcelStyleHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class MyExcelText {

    @Autowired
    InvoiceTitleDAO invoiceTitleDAO;

    @Autowired
    private SupplierDAO supplierDAO;

    @Test
    void testWrite() {
        List<MaterialDumpModel> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MaterialDumpModel item = new MaterialDumpModel();
            item.setIndex(Integer.toString(i));
            item.setCode("code" + i);
            item.setName("name" + i);
            item.setItemName("name" + i);
            item.setSpec("spec" + i);
            item.setAmount("amount" + i);
            item.setDate("date" + i);
            item.setReason("reason" + i);
            data.add(item);
        }

        Map<Integer,Integer> columnWidth = new HashMap<>(8);
        columnWidth.put(0,(int)(4.57*256));
        columnWidth.put(1,(int)(10.86*256));
        columnWidth.put(2,(int)(26.14*256));
        columnWidth.put(3,(int)(23.43*256));
        columnWidth.put(4,(int)(24.86*256));
        columnWidth.put(5,(int)(9.0*256));
        columnWidth.put(6,(int)(10.14*256));
        columnWidth.put(7,(int)(8.71*256));

        ResultDO<Void> result = MyExcel.write("原材料报废信息.xlsx", MaterialDumpModel.class, data, columnWidth, new MaterialDumpExcelStyleHandler());
        assert(result.isSuccess());
    }

    @Test
    void testRead() {
        ResultDO<List<Object>> result = MyExcel.read("C:/ERPSystem/file/客户信息.xlsx", CustomerModel.class);
        result.getModule().forEach(System.out::println);
    }

    @Test
    void testWriteTemplate() {
        Map<String, Object> map = new HashMap<>(9);

        InvoiceTitleDO invoiceTitleDO = invoiceTitleDAO.getInvoiceTitleById("4932da7c-a9a0-4d79-8970-fc456ecd7e83");

        SupplierDO supplierDO = supplierDAO.getSupplierById("ee55aae8-2713-450c-a166-00db7e16774e");

        map.put("titleName", invoiceTitleDO.getName());
        map.put("orderNumber", "123456");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("orderDate", simpleDateFormat.format(new Date()));

        map.put("code", supplierDO.getCode());
        map.put("name", supplierDO.getName());
        map.put("contact", supplierDO.getContact());
        map.put("fixedTelephone", supplierDO.getFixedTelephone());
        map.put("fax", supplierDO.getFax());

        List<MaterialPurchaseModel> materialPurchaseModelList = new ArrayList<>();

        MaterialPurchaseModel materialPurchaseModel = new MaterialPurchaseModel();
        materialPurchaseModel.setIndex("1");
        materialPurchaseModel.setItemName("MGR3.5*10");
        materialPurchaseModel.setSpec("30-50K");
        materialPurchaseModel.setAmount("123.00");
        materialPurchaseModel.setUnit("PCS");
        materialPurchaseModel.setUnitPrice("0.06920");
        materialPurchaseModel.setTotalPrice("" + Double.parseDouble("123.00") * Double.parseDouble("0.06920"));
        materialPurchaseModel.setHopeDeliveryDate(simpleDateFormat.format(new Date()));
        materialPurchaseModel.setRemark("");

        materialPurchaseModelList.add(materialPurchaseModel);

        map.put("materialPurchase", materialPurchaseModelList);

        MyExcel.writeTemplate("原材料订购单（发，亿，永）.xlsx", "原材料订购单（发，亿，永）.xlsx",map);
    }

}
