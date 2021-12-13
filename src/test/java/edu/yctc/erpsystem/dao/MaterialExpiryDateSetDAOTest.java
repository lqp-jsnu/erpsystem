package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialExpiryDateSetDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialExpiryDateSetDAOTest {
    @Autowired
    private MaterialExpiryDateSetDAO materialExpiryDateSetDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialExpiryDateSetDAO.count(params);
        assertEquals(result, 8);
    }

    @Test
    void testGetMaterialDateSet() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialExpiryDateSetDO> materialExpiryDateSetDOS = materialExpiryDateSetDAO.getMaterialDateSet(params);
        assertNotNull(materialExpiryDateSetDOS);
        materialExpiryDateSetDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialExpiryDateSetDO materialExpiryDateSetDO = new MaterialExpiryDateSetDO();

        materialExpiryDateSetDO.setExpiryDate(40);
        materialExpiryDateSetDO.setRemark("新加1");
        materialExpiryDateSetDO.setMaterialInfoMasterId("01bd50d9-ad66-44a6-90cd-a41a3fb05fd1");

        materialExpiryDateSetDAO.insert(materialExpiryDateSetDO);
    }

    @Test
    void testDelete() {
        materialExpiryDateSetDAO.delete("3b63243a754d11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateMaterialDateSet() {
        MaterialExpiryDateSetDO materialExpiryDateSetDO = new MaterialExpiryDateSetDO();

        materialExpiryDateSetDO.setId("3b63243a754d11eaaf4254e1ad394a4a");
        materialExpiryDateSetDO.setMaterialInfoMasterId("01bd50d9-ad66-44a6-90cd-a41a3fb05fd1");
        materialExpiryDateSetDO.setExpiryDate(30);
        materialExpiryDateSetDO.setRemark("修改1");

        materialExpiryDateSetDAO.updateMaterialDateSet(materialExpiryDateSetDO);
    }

}
