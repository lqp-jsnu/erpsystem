package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.WarehouseDO;
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
class WarehouseDAOTest {

    @Autowired
    private WarehouseDAO warehouseDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = warehouseDAO.count(params);
        assertEquals(result, 16);
    }

    @Test
    void testGetWarehouse() {
        Map<String, Object> params = new HashMap<>(0);
        List<WarehouseDO> warehouseDOS = warehouseDAO.getWarehouse(params);
        assertNotNull(warehouseDOS);
        warehouseDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        WarehouseDO warehouseDO = new WarehouseDO();

        warehouseDO.setName("许志强");
        warehouseDO.setType("材料仓库");
        warehouseDO.setAddress("邳州");
        warehouseDO.setUse("车库");
        warehouseDO.setSyUserId("0");
        warehouseDO.setDeleteFlag("0");
        warehouseDO.setIsZero("否");

        warehouseDAO.insert(warehouseDO);
    }

    @Test
    void testDelete() {
        warehouseDAO.delete("1b9abff8756b11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateWareHouse() {
        WarehouseDO warehouseDO = new WarehouseDO();

        warehouseDO.setId("1b9abff8756b11eaaf4254e1ad394a4a");
        warehouseDO.setName("许");
        warehouseDO.setType("成品仓品");
        warehouseDO.setSyUserId("0");
        warehouseDO.setIsZero("1");

        warehouseDAO.updateWareHouse(warehouseDO);
    }

    @Test
    void testGetWarehouseById() {
        WarehouseDO result = warehouseDAO.getWarehouseById("0126f760-b49b-46a3-9d49-ce98c97ddf9e");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetWarehouseByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("0126f760-b49b-46a3-9d49-ce98c97ddf9e");
        ids.add("089da594-d2bd-4086-a971-3e2cd4ac85dc");
        List<WarehouseDO> result = warehouseDAO.getWarehouseByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
