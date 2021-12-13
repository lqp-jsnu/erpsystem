package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialExportDO;
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
class MaterialExportDAOTest {

    @Autowired
    private MaterialExportDAO materialExportDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialExportDAO.count(params);
        assertEquals(result, 2);
    }

    @Test
    void testGetMaterialExport() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialExportDO> result = materialExportDAO.getMaterialExport(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testCounts() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialExportDAO.counts(params);
        assertEquals(result, 69);
    }

    @Test
    void testGetMaterialExportDetail() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialExportDO> result = materialExportDAO.getMaterialExportDetail(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialExportDO materialExportDO = new MaterialExportDO();

        materialExportDO.setUser("0");

        materialExportDAO.insert(materialExportDO);
    }

    @Test
    void testDelete() {
        materialExportDAO.delete("a899acbc86e411ea8fc654e1ad394a4a");
    }

    @Test
    void testUpdateCheckerById() {
        MaterialExportDO materialExportDO = new MaterialExportDO();

        materialExportDO.setId("a899acbc86e411ea8fc654e1ad394a4a");
        materialExportDO.setChecker("a94fb015-2138-4c87-907e-e40c94382083");
        materialExportDO.setCheckFlag("已通过");

        materialExportDAO.updateCheckerById(materialExportDO);
    }

    @Test
    void testGetMaterialExportById() {
        MaterialExportDO result = materialExportDAO.getMaterialExportById("08d967d8-36cb-4c87-ae3c-c10b2450a202");
        assertNotNull( result );
        System.out.println(result);
    }

    @Test
    void testGetMaterialExportByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("08d967d8-36cb-4c87-ae3c-c10b2450a202");
        ids.add("0c5fc793-eeb3-411f-b645-e071c788fede");
        List<MaterialExportDO> result = materialExportDAO.getMaterialExportByIds(ids);
        assertNotNull( result );
        result.forEach( System.out::println );
    }

    @Test
    void testGetMaterialExportByManufactureProcessSlaveId() {
        MaterialExportDO result = materialExportDAO.getMaterialExportByManufactureProcessSlaveId("3aebae1d-3655-443e-84d3-085aebed8cf4");
        assertNotNull( result );
        System.out.println(result);
    }

}
