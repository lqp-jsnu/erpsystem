package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInventoryDO;
import edu.yctc.erpsystem.vo.MaterialInventoryVO;
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
class MaterialInventoryDAOTest {

    @Autowired
    private MaterialInventoryDAO materialInventoryDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialInventoryDAO.count(params);
        assertEquals(result, 1237);
    }

    @Test
    void testGetMaterialInventory() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialInventoryVO> result = materialInventoryDAO.getMaterialInventory(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetMaterialInventoryById() {
        MaterialInventoryDO result = materialInventoryDAO.getMaterialInventoryById("0007ff88-ba1f-4caf-af6b-7628aa85e8dc");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetMaterialInventoryByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("0007ff88-ba1f-4caf-af6b-7628aa85e8dc");
        ids.add("00859fd1-7aac-49c8-bb66-abc021599764");

        List<MaterialInventoryDO> result = materialInventoryDAO.getMaterialInventoryByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetMaterialInventoryByMasterId() {
        List<MaterialInventoryDO> result = materialInventoryDAO.getMaterialInventoryByMasterId("47082589-8437-4733-b337-23019b249833");
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialInventoryDO materialInventoryDO = new MaterialInventoryDO();

        materialInventoryDO.setMaterialStorageId("28aad275-148a-4661-8af7-67fdccf37014");

        materialInventoryDAO.insert(materialInventoryDO);
    }

    @Test
    void testUpdateLeftAmountById() {
        MaterialInventoryDO materialInventoryDO = new MaterialInventoryDO();

        materialInventoryDO.setId("2f87d2bc7d9d11ea98b954e1ad394a4a");
        materialInventoryDO.setLeftAmount("20");

        materialInventoryDAO.updateLeftAmountById(materialInventoryDO);
    }

    @Test
    void testUpdateAllLeftAmountByIds() {
        List<MaterialInventoryDO> materialInventoryList = new ArrayList<>();

        MaterialInventoryDO materialInventory = new MaterialInventoryDO();
        materialInventory.setId("4a29607e-aa2f-4670-96b2-4c7b04fbf13d");
        materialInventory.setLeftAmount("50.00");
        materialInventoryList.add(materialInventory);

        materialInventoryDAO.updateAllLeftAmountByIds(materialInventoryList);
    }

}
