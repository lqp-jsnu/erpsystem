package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.BillOfSaleHistoryDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BillOfSaleHistoryDAOTest {

    @Autowired
    private BillOfSaleHistoryDAO billOfSaleHistoryDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = billOfSaleHistoryDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void getBillOfSaleHistory() {
        Map<String, Object> params = new HashMap<>(0);
        List<BillOfSaleHistoryDO> result = billOfSaleHistoryDAO.getBillOfSaleHistory(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        BillOfSaleHistoryDO billOfSaleHistoryDO = new BillOfSaleHistoryDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2019);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        billOfSaleHistoryDO.setBillOfSaleId("ce981972-809e-40bf-a4e0-4180be258f8d");
        billOfSaleHistoryDO.setDate(date);
        billOfSaleHistoryDO.setNumber("14474");
        billOfSaleHistoryDO.setInvoiceTitleId("873a546c-c15b-40ab-b3a7-3c27943ddfaf");

        billOfSaleHistoryDAO.insert(billOfSaleHistoryDO);
    }

}
