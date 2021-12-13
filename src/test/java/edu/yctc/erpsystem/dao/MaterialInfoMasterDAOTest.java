package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInfoMasterDO;
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
class MaterialInfoMasterDAOTest {

    @Autowired
    private MaterialInfoMasterDAO materialInfoMasterDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialInfoMasterDAO.count(params);
        assertEquals(result, 968);
    }

    @Test
    void getMaterialInfoMaster() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("limit", 10);
        params.put("offset", 0);
        List<MaterialInfoMasterDO> result = materialInfoMasterDAO.getMaterialInfoMaster(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialInfoMasterDO materialInfoMasterDO = new MaterialInfoMasterDO();

        materialInfoMasterDO.setRemark("xx");
        materialInfoMasterDO.setUnit("xx");
        materialInfoMasterDO.setSpec("xx");
        materialInfoMasterDO.setItemName("xx");

        materialInfoMasterDAO.insert(materialInfoMasterDO);
    }

    @Test
    void testDelete() {
        materialInfoMasterDAO.delete("cfb3e11f754e11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateMaterialInfoMaster() {
        MaterialInfoMasterDO materialInfoMasterDO = new MaterialInfoMasterDO();

        materialInfoMasterDO.setId("cfb3e11f754e11eaaf4254e1ad394a4a");
        materialInfoMasterDO.setItemName("yy");
        materialInfoMasterDO.setSpec("yy");
        materialInfoMasterDO.setUnit("yy");
        materialInfoMasterDO.setRemark("yy");

        materialInfoMasterDAO.updateMaterialInfoMaster(materialInfoMasterDO);
    }

    @Test
    void testGetMaterialInfoMasterById() {
        MaterialInfoMasterDO result = materialInfoMasterDAO.getMaterialInfoMasterById("0018129c-f2b3-4493-8198-4f87fb26f486");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetMaterialInfoMasterByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("0018129c-f2b3-4493-8198-4f87fb26f486");
        ids.add("014b3484-8ec5-43f7-a155-eab0619c1957");

        List<MaterialInfoMasterDO> result = materialInfoMasterDAO.getMaterialInfoMasterByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
