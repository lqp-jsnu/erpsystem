package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.OriginalProductDO;
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
 class OriginalProductDAOTest {

    @Autowired
    private OriginalProductDAO originalProductDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = originalProductDAO.count(params);
        assertEquals(result, 3948);
    }

    @Test
    void testGetOriginalProduct() {
        Map<String, Object> params = new HashMap<>(3);
        params.put("limit", 10);
        params.put("offset", 0);
        List<OriginalProductDO> result = originalProductDAO.getOriginalProduct(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        OriginalProductDO originalProductDO = new OriginalProductDO();

        originalProductDO.setItemName("1111");
        originalProductDO.setSpec("222");
        originalProductDO.setCustomerId("123021a6-1905-437f-bed0-d2960a28130e");
        originalProductDO.setRemark("新加1");

        originalProductDAO.insert(originalProductDO);
    }

    @Test
    void testDelete() {
        originalProductDAO.delete("2ff6bd387bcf11ea98b954e1ad394a4a");
    }

    @Test
    void testUpdateOriginalProduct() {
        OriginalProductDO originalProductDO = new OriginalProductDO();

        originalProductDO.setId("2ff6bd387bcf11ea98b954e1ad394a4a");
        originalProductDO.setItemName("2");
        originalProductDO.setSpec("2");
        originalProductDO.setRemark("2");
        originalProductDO.setColorCode("2");
        originalProductDO.setLabel("2");
        originalProductDO.setUnitPrice("2");
        originalProductDO.setUnit("2");
        originalProductDO.setProductNumber("2");

        originalProductDAO.updateOriginalProduct(originalProductDO);
    }

    @Test
    void testUpdateDrawById() {
        OriginalProductDO originalProductDO = new OriginalProductDO();

        originalProductDO.setId("2ff6bd387bcf11ea98b954e1ad394a4a");
        originalProductDO.setDrawingUrl("qq");

        originalProductDAO.updateDrawById(originalProductDO);
    }

    @Test
    void testUpdateCheckerById() {
        OriginalProductDO originalProductDO = new OriginalProductDO();

        originalProductDO.setId("2ff6bd387bcf11ea98b954e1ad394a4a");
        originalProductDO.setRemark("2");
        originalProductDO.setCheckFlag("已通过");
        originalProductDO.setChecker("0");

        originalProductDAO.updateCheckerById(originalProductDO);
    }

    @Test
    void testGetOriginalProductById() {
        OriginalProductDO result = originalProductDAO.getOriginalProductById("00141258-de37-441e-9545-242491dd3154");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetOriginalProductByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("00141258-de37-441e-9545-242491dd3154");
        ids.add("001ae941-c639-4fa7-a731-c21e052349b5");
        List<OriginalProductDO> result = originalProductDAO.getOriginalProductByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
