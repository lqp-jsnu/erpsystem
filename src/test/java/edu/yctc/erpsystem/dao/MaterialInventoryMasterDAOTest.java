package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInventoryMasterDO;
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
class MaterialInventoryMasterDAOTest {

    @Autowired
    private MaterialInventoryMasterDAO materialInventoryMasterDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialInventoryMasterDAO.count(params);
        assertEquals(result, 654);
    }

    @Test
    void testGetMaterialInventory() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialInventoryMasterDO> result = materialInventoryMasterDAO.getMaterialInventoryMaster(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialInventoryMasterDO materialInventoryMasterDO = new MaterialInventoryMasterDO();
        materialInventoryMasterDO.setMaterialInfoMasterId("0018129c-f2b3-4493-8198-4f87fb26f486");
        materialInventoryMasterDAO.insert(materialInventoryMasterDO);
    }

    @Test
    void testGetCustomerById() {
        MaterialInventoryMasterDO result = materialInventoryMasterDAO.getMaterialInventoryMasterById("01110461-7cc8-47a0-b323-6f634beddb5e");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetCustomerByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("01110461-7cc8-47a0-b323-6f634beddb5e");
        ids.add("014f0890-07c3-4cf9-b8f1-c4fa8c8bf7c5");
        List<MaterialInventoryMasterDO> result = materialInventoryMasterDAO.getMaterialInventoryMasterByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetMaterialInventoryMasterByMaterialInfoMasterId() {
        MaterialInventoryMasterDO result = materialInventoryMasterDAO.getMaterialInventoryMasterByMaterialInfoMasterId("0018129c-f2b3-4493-8198-4f87fb26f486");
        System.out.println(result);
    }

}
