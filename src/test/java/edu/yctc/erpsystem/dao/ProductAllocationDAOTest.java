package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ProductAllocationDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ProductAllocationDAOTest {

    @Resource
    private ProductAllocationDAO productAllocationDAO;

    @Test
    void testInsert() {
        ProductAllocationDO productAllocationDO = new ProductAllocationDO();

        productAllocationDO.setUser("0");
        productAllocationDO.setProductInventoryId("0d7e338c-5c43-48fe-8baf-390105d862c1");
        productAllocationDO.setWarehouseId("0126f760-b49b-46a3-9d49-ce98c97ddf9e");
        productAllocationDO.setAmount("123");

        productAllocationDAO.insert(productAllocationDO);
    }

}
