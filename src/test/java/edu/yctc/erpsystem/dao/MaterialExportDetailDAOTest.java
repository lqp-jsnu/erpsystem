package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialExportDetailDO;
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
class MaterialExportDetailDAOTest {

    @Autowired
    MaterialExportDetailDAO materialExportDetailDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialExportDetailDAO.count(params);
        assertEquals(result, 71);
    }

    @Test
    void testGetMaterialExportDetail() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialExportDetailDO> result = materialExportDetailDAO.getMaterialExportDetail(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialExportDetailDO result = new MaterialExportDetailDO();
        result.setMaterialExportId("08d967d8-36cb-4c87-ae3c-c10b2450a202");
        materialExportDetailDAO.insert(result);
    }

    @Test
    void testInsertAll() {
        List<MaterialExportDetailDO> materialExportDetailDOS = new ArrayList<>();
        MaterialExportDetailDO result = new MaterialExportDetailDO();
        result.setMaterialExportId("08d967d8-36cb-4c87-ae3c-c10b2450a202");
        materialExportDetailDOS.add(result);
        materialExportDetailDAO.insertAll(materialExportDetailDOS);
    }

    @Test
    void testDelete() {
        materialExportDetailDAO.delete("3025f3f086ec11ea8fc654e1ad394a4a");
    }

    @Test
    void testDeleteAll() {
        List<String> ids = new ArrayList<>();
        ids.add("0c0d22fc86ec11ea8fc654e1ad394a4a");
        materialExportDetailDAO.deleteAll(ids);
    }

    @Test
    void testGetMaterialExportDetailByMaterialExportId() {
        List<MaterialExportDetailDO> result = materialExportDetailDAO.getMaterialExportDetailByMaterialExportId("76da0d7f-41f9-41c9-96c8-1e84dd7717c2");
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetMaterialExportDetailByMaterialExportIds() {
        List<String> ids = new ArrayList<>();
        ids.add("76da0d7f-41f9-41c9-96c8-1e84dd7717c2");
        List<MaterialExportDetailDO> result = materialExportDetailDAO.getMaterialExportDetailByMaterialExportIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
