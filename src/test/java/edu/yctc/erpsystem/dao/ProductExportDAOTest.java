package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductExportDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductExportDAOTest {

    @Resource
    private ProductExportDAO productExportDAO;

    @Test
    void testInsert() {
        ProductExportDO productExportDO = new ProductExportDO();

        productExportDO.setUser("0");
        productExportDO.setProductInventoryId("0d7e338c-5c43-48fe-8baf-390105d862c1");
        productExportDO.setCustomerId("9a524d78-3361-45f3-908d-4ed22212e67b");

        productExportDAO.insert(productExportDO);
    }

    @Test
    void testGetCustomerById() {
        ProductExportDO result = productExportDAO.getProductExportById("211c6335-1762-45f5-b615-64fc8e944539");
        assertNotNull( result );
        System.out.println(result);
    }

    @Test
    void testGetCustomerByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("211c6335-1762-45f5-b615-64fc8e944539");
        ids.add("75ac35b8-7d0f-4f43-9ce8-24d259c6be77");
        List<ProductExportDO> result = productExportDAO.getProductExportByIds(ids);
        assertNotNull( result );
        result.forEach( System.out::println );
    }

}
