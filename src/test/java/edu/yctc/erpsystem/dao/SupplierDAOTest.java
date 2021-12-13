package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.SupplierDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SupplierDAOTest {

    @Autowired
    private SupplierDAO supplierDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = supplierDAO.count(params);
        assertEquals(result, 42);
    }

    @Test
    void testGetSupplier() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("limit", 10);
        params.put("offset", 0);
        List<SupplierDO> result = supplierDAO.getSupplier(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        SupplierDO supplierDO = new SupplierDO();

        supplierDO.setCode("0000");
        supplierDO.setName("邳州");
        supplierDO.setProductCategory("石头");
        supplierDO.setFixedTelephone("333333");
        supplierDO.setDeleteFlag("0");

        supplierDAO.insert(supplierDO);
    }

    @Test
    void testDelete() {
        supplierDAO.delete("beb9269f756911eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateSupplierById() {
        SupplierDO supplierDO = new SupplierDO();

        supplierDO.setId("beb9269f756911eaaf4254e1ad394a4a");
        supplierDO.setCode("修改");
        supplierDO.setName("修改");
        supplierDO.setContact("修改");
        supplierDO.setFixedTelephone("修改");

        supplierDAO.updateSupplier(supplierDO);
    }

    @Test
    void testGetSupplierByCode() {
        List<String> codes = new ArrayList<>();
        codes.add("L-001");
        codes.add("L-002");
        List<SupplierDO> result = supplierDAO.getSupplierByCodes(codes);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetSupplierById() {
        SupplierDO result = supplierDAO.getSupplierById("073300c5-0906-4eb1-b0af-89ae66973450");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetSupplierByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("073300c5-0906-4eb1-b0af-89ae66973450");
        ids.add("118051ce-a54c-4e7d-9c94-930fa40b1a24");
        List<SupplierDO> result = supplierDAO.getSupplierByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
