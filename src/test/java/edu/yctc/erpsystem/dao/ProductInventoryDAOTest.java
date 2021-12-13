package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductInventoryDO;
import edu.yctc.erpsystem.vo.MainProductExpiryDateVO;
import edu.yctc.erpsystem.vo.MainProductNumberVO;
import edu.yctc.erpsystem.vo.ProductInventoryRepertoryVO;
import edu.yctc.erpsystem.vo.ProductInventorySummaryVO;
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
class ProductInventoryDAOTest {

    @Autowired
    private ProductInventoryDAO productInventoryDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productInventoryDAO.count(params);
        assertEquals(result, 65);
    }

    @Test
    void testGetProductInventory() {
        Map<String, Object> params = new HashMap<>(0);
        List<MainProductExpiryDateVO> result = productInventoryDAO.getProductInventory(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testSummaryCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productInventoryDAO.summaryCount(params);
        assertEquals(result, 54);
    }

    @Test
    void testGetProductInventorySummary() {
        Map<String, Object> params = new HashMap<>(0);
        List<ProductInventorySummaryVO> result = productInventoryDAO.getProductInventorySummary(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testMainProductNumberCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productInventoryDAO.mainProductNumberCount(params);
        assertEquals(result, 48);
    }

    @Test
    void testGetMainProductNumber() {
        Map<String, Object> params = new HashMap<>(0);
        List<MainProductNumberVO> result = productInventoryDAO.getMainProductNumber(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testCountRepertory() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productInventoryDAO.countRepertory(params);
        assertEquals(result, 65);
    }

    @Test
    void testGetProductInventoryRepertory() {
        Map<String, Object> params = new HashMap<>(0);
        List<ProductInventoryRepertoryVO> result = productInventoryDAO.getProductInventoryRepertory(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ProductInventoryDO productInventoryDO = new ProductInventoryDO();

        productInventoryDO.setProductStorageId("041dab17-7aab-4d52-83de-afc59dc3e6cc");

        productInventoryDAO.insert(productInventoryDO);
    }

    @Test
    void testGetCustomerById() {
        ProductInventoryDO result = productInventoryDAO.getProductInventoryById("030a94b7-32fb-44c6-a10b-d3afccb1e04c");
        assertNotNull( result );
        System.out.println(result);
    }

    @Test
    void testGetCustomerByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("030a94b7-32fb-44c6-a10b-d3afccb1e04c");
        ids.add("04f2f8bf-d03e-4d16-b3ea-ebc4011b24d0");
        List<ProductInventoryDO> result = productInventoryDAO.getProductInventoryByIds(ids);
        assertNotNull( result );
        result.forEach( System.out::println );
    }

}
