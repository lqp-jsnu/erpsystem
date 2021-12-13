package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialStorageDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialStorageDAOTest {

    @Resource
    private MaterialStorageDAO materialStorageDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialStorageDAO.count(params);
        assertEquals(result, 1797);
    }

    @Test
    void testGetMaterialStorage() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialStorageDO> result = materialStorageDAO.getMaterialStorage(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialStorageDO materialStorageDO = new MaterialStorageDO();

        materialStorageDO.setMaterialPurchaseToBeStorageId("7cc66ece-3f72-46f3-ab1b-8259ab247455");
        materialStorageDO.setInAmount("45.00");
        materialStorageDO.setWareHouseId("fa56dab1-9a5f-4097-89dd-da235c55129f");
        materialStorageDO.setUser("0");
        materialStorageDO.setDictId("85644279-4191-4ac8-b71e-a4c39669be2a");

        materialStorageDAO.insert(materialStorageDO);
    }

    @Test
    void testDelete() {
        materialStorageDAO.delete("554132307cd111ea98b954e1ad394a4a");
    }

    @Test
    void testUpdateCheckerById() {
        MaterialStorageDO materialStorageDO = new MaterialStorageDO();

        materialStorageDO.setId("554132307cd111ea98b954e1ad394a4a");
        materialStorageDO.setChecker("0");
        materialStorageDO.setCheckFlag("已通过");

        materialStorageDAO.updateCheckerById(materialStorageDO);
    }

    @Test
    void testGetMaterialStorageById() {
        MaterialStorageDO result = materialStorageDAO.getMaterialStorageById("0022546e-66ca-4356-9e0e-d5d9a526110b");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetMaterialStorageByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("0022546e-66ca-4356-9e0e-d5d9a526110b");
        ids.add("00251118-3874-470f-8ddd-71181e6ba3d7");

        List<MaterialStorageDO> result = materialStorageDAO.getMaterialStorageByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
