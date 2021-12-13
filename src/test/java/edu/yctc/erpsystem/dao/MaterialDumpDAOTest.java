package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialDumpDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialDumpDAOTest {

    @Autowired
    private MaterialDumpDAO materialDumpDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialDumpDAO.count(params);
        assertEquals(result, 24);
    }

    @Test
    void testGetMaterialDump() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialDumpDO> result = materialDumpDAO.getMaterialDump(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialDumpDO materialDumpDO = new MaterialDumpDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2019);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        materialDumpDO.setMaterialInventoryId("6cd47753-edf9-4a74-b96e-45f6a176ea69");
        materialDumpDO.setAmount("45.00");
        materialDumpDO.setDate(date);
        materialDumpDO.setReason("qqqqqqqq");
        materialDumpDO.setEnter("0");

        materialDumpDAO.insert(materialDumpDO);
    }

    @Test
    void testDelete() {
        materialDumpDAO.delete("78a9b1ce754c11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateMaterialDump() {
        MaterialDumpDO materialDumpDO = new MaterialDumpDO();

        materialDumpDO.setId("78a9b1ce754c11eaaf4254e1ad394a4a");
        materialDumpDO.setAmount("100");
        materialDumpDO.setReason("zxcvbb");

        materialDumpDAO.updateMaterialDump(materialDumpDO);
    }

    @Test
    void testUpdateCheckerById() {
        MaterialDumpDO materialDumpDO = new MaterialDumpDO();

        materialDumpDO.setId("78a9b1ce754c11eaaf4254e1ad394a4a");
        materialDumpDO.setChecker("0");
        materialDumpDO.setCheckFlag("已通过");

        materialDumpDAO.updateCheckerById(materialDumpDO);
    }

}
