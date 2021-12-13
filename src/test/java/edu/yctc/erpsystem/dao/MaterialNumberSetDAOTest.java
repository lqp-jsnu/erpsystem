package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialNumberSetDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialNumberSetDAOTest {

    @Autowired
    private MaterialNumberSetDAO materialNumberSetDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialNumberSetDAO.count(params);
        assertEquals(result, 3);
    }

    @Test
    void testGetMaterialNumberSet() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialNumberSetDO> materialNumberSetDOS = materialNumberSetDAO.getMaterialNumberSet(params);
        assertNotNull(materialNumberSetDOS);
        materialNumberSetDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialNumberSetDO materialNumberSetDO = new MaterialNumberSetDO();

        materialNumberSetDO.setMinNumber("10");
        materialNumberSetDO.setMaxNumber("70");
        materialNumberSetDO.setRemark("新加1");
        materialNumberSetDO.setMaterialInfoMasterId("0199d52a-8607-44e4-b725-8fc7f1087821");

        materialNumberSetDAO.insert(materialNumberSetDO);
    }

    @Test
    void testDelete() {
        materialNumberSetDAO.delete("3cced688755011eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateMaterialNumberSet() {
        MaterialNumberSetDO materialNumberSetDO = new MaterialNumberSetDO();

        materialNumberSetDO.setId("3cced688755011eaaf4254e1ad394a4a");
        materialNumberSetDO.setMaterialInfoMasterId("190d7f5c-eb97-4533-b875-d8230c0b3e15");
        materialNumberSetDO.setMinNumber("10");
        materialNumberSetDO.setMaxNumber("70");
        materialNumberSetDO.setRemark("修改1");

        materialNumberSetDAO.updateMaterialNumberSet(materialNumberSetDO);
    }

}
