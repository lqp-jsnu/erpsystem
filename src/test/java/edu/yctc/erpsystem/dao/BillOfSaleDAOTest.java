package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.BillOfSaleDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BillOfSaleDAOTest {

    @Autowired
    private BillOfSaleDAO billOfSaleDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = billOfSaleDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void getBillOfSale() {
        Map<String, Object> params = new HashMap<>(0);
        List<BillOfSaleDO> result = billOfSaleDAO.getBillOfSale(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsertAll() {
        List<BillOfSaleDO> billOfSaleDOS = new ArrayList<>();

        for (int i = 0; i < 2; ++i) {
            BillOfSaleDO temp = new BillOfSaleDO();

            temp.setProductExportId("75ac35b8-7d0f-4f43-9ce8-24d259c6be77");
            temp.setExportStatus("未出库");
            temp.setChecker("0");
            temp.setRemark("11111111");
            temp.setProductNumber("11222222");
            temp.setOrderNumber("22222222");

            billOfSaleDOS.add(temp);
        }

        billOfSaleDAO.insertAll(billOfSaleDOS);
    }

    @Test
    void testChangeExportStatusById() {
        billOfSaleDAO.changeExportStatusById("06fc4cf1930711eab4bc54e1ad394a4a");
    }

    @Test
    void testUpdateCheckerById() {
        BillOfSaleDO billOfSaleDO = new BillOfSaleDO();

        billOfSaleDO.setId("06fc4cf1930711eab4bc54e1ad394a4a");
        billOfSaleDO.setRemark("14");

        billOfSaleDAO.updateRemarkById(billOfSaleDO);
    }

    @Test
    void testGetBillOfSaleById() {
        BillOfSaleDO result =  billOfSaleDAO.getBillOfSaleById("ce981972-809e-40bf-a4e0-4180be258f8d");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetBillOfSaleByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("ce981972-809e-40bf-a4e0-4180be258f8d");

        List<BillOfSaleDO> result =  billOfSaleDAO.getBillOfSaleByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
