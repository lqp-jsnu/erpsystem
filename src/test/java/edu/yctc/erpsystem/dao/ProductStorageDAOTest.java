package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductStorageDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductStorageDAOTest {

    @Resource
    private ProductStorageDAO productStorageDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productStorageDAO.count(params);
        assertEquals(result, 0);
    }

    @Test
    void testGetProductStorage() {
        Map<String, Object> params = new HashMap<>(0);
        List<ProductStorageDO> result = productStorageDAO.getProductStorage(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testCountDetail() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productStorageDAO.countDetail(params);
        assertEquals(result, 65);
    }

    @Test
    void testGetProductStorageDetail() {
        Map<String, Object> params = new HashMap<>(0);
        List<ProductStorageDO> result = productStorageDAO.getProductStorageDetail(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ProductStorageDO productStorageDO = new ProductStorageDO();

        productStorageDO.setManufactureProcessSlaveId("fae90efc-63d4-4e47-933a-2f2db542b94f");
        productStorageDO.setStorageDate(new Date());
        productStorageDO.setStorageAmount("564");
        productStorageDO.setWareHouseId("fe239fdd-72f6-4b60-b5af-3580b51913ff");
        productStorageDO.setUser("0");
        productStorageDO.setProductQuantity("15616");
        productStorageDO.setIsZeroProductStorage("是");

        productStorageDAO.insert(productStorageDO);
    }

    @Test
    void testDelete() {
        productStorageDAO.delete("1af3afc5922911eab4bc54e1ad394a4a");
    }

    @Test
    void testUpdateProductStorage() {
        ProductStorageDO productStorageDO = new ProductStorageDO();

        productStorageDO.setId("1af3afc5922911eab4bc54e1ad394a4a");
        productStorageDO.setRemark("100");
        productStorageDO.setStorageDate(new Date());
        productStorageDO.setStorageAmount("123");
        productStorageDO.setWareHouseId("fe239fdd-72f6-4b60-b5af-3580b51913ff");

        productStorageDAO.updateProductStorage(productStorageDO);
    }

    @Test
    void testUpdateStorageAmount() {
        ProductStorageDO productStorageDO = new ProductStorageDO();

        productStorageDO.setStorageAmount("123");

        productStorageDAO.updateStorageAmount(productStorageDO);
    }

    @Test
    void testUpdateCheckerById() {
        ProductStorageDO productStorageDO = new ProductStorageDO();

        productStorageDO.setId("1af3afc5922911eab4bc54e1ad394a4a");
        productStorageDO.setChecker("0");
        productStorageDO.setCheckFlag("未通过");

        productStorageDAO.updateCheckerById(productStorageDO);
    }

    @Test
    void testGetProductStorageById() {
        ProductStorageDO result = productStorageDAO.getProductStorageById("03e544a2-7581-44e3-9761-d70fec38c2e1");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetProductStorageByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("03e544a2-7581-44e3-9761-d70fec38c2e1");
        ids.add("041dab17-7aab-4d52-83de-afc59dc3e6cc");

        List<ProductStorageDO> result = productStorageDAO.getProductStorageByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
