package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductNumberSetDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
 class ProductNumberSetDAOTest {

    @Autowired
    private ProductNumberSetDAO productNumberSetDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = productNumberSetDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void testGetProductNumberSet() {
        Map<String, Object> params = new HashMap<>(0);
        List<ProductNumberSetDO> productNumberSetDOS = productNumberSetDAO.getProductNumberSet(params);
        assertNotNull(productNumberSetDOS);
        productNumberSetDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ProductNumberSetDO productNumberSetDO = new ProductNumberSetDO();

        productNumberSetDO.setOriginalProductId("003b4027-ba12-47fa-a7dc-e2b3640e61d5");
        productNumberSetDO.setMinNumber("10");
        productNumberSetDO.setMaxNumber("70");
        productNumberSetDO.setRemark("新加1");

        productNumberSetDAO.insert(productNumberSetDO);
    }

    @Test
    void testDelete() {
        productNumberSetDAO.delete("9f26f5b7756611eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateProductNumberSet() {
        ProductNumberSetDO productNumberSetDO = new ProductNumberSetDO();

        productNumberSetDO.setId("9f26f5b7756611eaaf4254e1ad394a4a");
        productNumberSetDO.setOriginalProductId("003b4027-ba12-47fa-a7dc-e2b3640e61d5");
        productNumberSetDO.setMinNumber("10");
        productNumberSetDO.setMaxNumber("70");
        productNumberSetDO.setRemark("修改1");

        productNumberSetDAO.updateProductNumberSet(productNumberSetDO);
    }

}
