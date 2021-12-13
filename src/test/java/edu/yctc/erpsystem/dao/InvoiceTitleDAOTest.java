package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.InvoiceTitleDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InvoiceTitleDAOTest {

    @Autowired
    InvoiceTitleDAO invoiceTitleDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = invoiceTitleDAO.count(params);
        assertEquals(result, 3);
    }

    @Test
    void testGetInvoiceTitle() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("offset", 0);
        params.put("limit", 10);
        List<InvoiceTitleDO> result = invoiceTitleDAO.getInvoiceTitle(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        InvoiceTitleDO invoiceTitleDO = new InvoiceTitleDO();

        invoiceTitleDO.setRemark("xx");
        invoiceTitleDO.setName("xx");

        invoiceTitleDAO.insert(invoiceTitleDO);
    }

    @Test
    void testDelete() {
        invoiceTitleDAO.delete("3dc2e656754a11eaaf4254e1ad394a4a");
    }

    @Test
    void testRestore() {
        invoiceTitleDAO.restore("3dc2e656754a11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateInvoiceTitleInfo() {
        InvoiceTitleDO invoiceTitleDO = new InvoiceTitleDO();

        invoiceTitleDO.setId("3dc2e656754a11eaaf4254e1ad394a4a");
        invoiceTitleDO.setName("yy");
        invoiceTitleDO.setRemark("yy");

        invoiceTitleDAO.updateInvoiceTitle(invoiceTitleDO);
    }

    @Test
    void testGetInvoiceTitleById() {
        InvoiceTitleDO result = invoiceTitleDAO.getInvoiceTitleById("4932da7c-a9a0-4d79-8970-fc456ecd7e83");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetInvoiceTitleByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("4932da7c-a9a0-4d79-8970-fc456ecd7e83");
        ids.add("873a546c-c15b-40ab-b3a7-3c27943ddfaf");

        List<InvoiceTitleDO> result = invoiceTitleDAO.getInvoiceTitleByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
