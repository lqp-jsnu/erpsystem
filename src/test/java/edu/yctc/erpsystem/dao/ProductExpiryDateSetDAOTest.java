package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductExpiryDateSetDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductExpiryDateSetDAOTest {

    @Autowired
    private ProductExpiryDateSetDAO productExpiryDateSetDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productExpiryDateSetDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void testGetExpiryDateSet() {
        Map<String, Object> params = new HashMap<>(0);
        List<ProductExpiryDateSetDO> productExpiryDateSetDOS = productExpiryDateSetDAO.getExpiryDateSet(params);
        assertNotNull(productExpiryDateSetDOS);
        productExpiryDateSetDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ProductExpiryDateSetDO productExpiryDateSetDO = new ProductExpiryDateSetDO();

        productExpiryDateSetDO.setOriginalProductId("007f326a-8126-4981-bf6e-96a635934030");
        productExpiryDateSetDO.setExpiryDate(40);
        productExpiryDateSetDO.setRemark("新加1");

        productExpiryDateSetDAO.insert(productExpiryDateSetDO);
    }

    @Test
    void testDelete() {
        productExpiryDateSetDAO.delete("0a2c3269755411eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateExpiryDateSet() {
        ProductExpiryDateSetDO productExpiryDateSetDO = new ProductExpiryDateSetDO();

        productExpiryDateSetDO.setId("0a2c3269755411eaaf4254e1ad394a4a");
        productExpiryDateSetDO.setOriginalProductId("007f326a-8126-4981-bf6e-96a635934030");
        productExpiryDateSetDO.setExpiryDate(40);
        productExpiryDateSetDO.setRemark("修改1");

        productExpiryDateSetDAO.updateExpiryDateSet(productExpiryDateSetDO);
    }

}
