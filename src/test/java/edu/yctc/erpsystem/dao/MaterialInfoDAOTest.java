package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInfoDO;
import edu.yctc.erpsystem.vo.MaterialInfoVO;
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
class MaterialInfoDAOTest {

    @Autowired
    private MaterialInfoDAO materialInfoDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialInfoDAO.count(params);
        assertEquals(result, 963);
    }

    @Test
    void testGetMaterialInfo() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialInfoDO> result = materialInfoDAO.getMaterialInfo(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialInfoDO materialInfoDO = new MaterialInfoDO();

        materialInfoDO.setSpecialRequire("xx");
        materialInfoDO.setRemark("xx");
        materialInfoDO.setUnitPrice("1");
        materialInfoDO.setSupplierId("073300c5-0906-4eb1-b0af-89ae66973450");
        materialInfoDO.setMaterialInfoMasterId("0018129c-f2b3-4493-8198-4f87fb26f486");

        materialInfoDAO.insert(materialInfoDO);
    }

    @Test
    void testDelete() {
        materialInfoDAO.delete("db1f96a5754d11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateMaterialInfo() {
        MaterialInfoDO materialInfoDO = new MaterialInfoDO();

        materialInfoDO.setMaterialInfoMasterId("0018129c-f2b3-4493-8198-4f87fb26f486");
        materialInfoDO.setSupplierId("073300c5-0906-4eb1-b0af-89ae66973450");
        materialInfoDO.setUnitPrice("2");
        materialInfoDO.setSpecialRequire("xx");
        materialInfoDO.setRemark("yy");
        materialInfoDO.setId("db1f96a5754d11eaaf4254e1ad394a4a");

        materialInfoDAO.updateMaterialInfo(materialInfoDO);
    }

    @Test
    void testUpdateCheckerById() {
        MaterialInfoDO materialInfoDO = new MaterialInfoDO();

        materialInfoDO.setChecker("0");
        materialInfoDO.setCheckFlag("未通过");
        materialInfoDO.setRemark("xx");
        materialInfoDO.setId("db1f96a5754d11eaaf4254e1ad394a4a");

        materialInfoDAO.updateCheckerById(materialInfoDO);
    }

    @Test
    void testGetMaterialInfoById() {
        MaterialInfoDO result =  materialInfoDAO.getMaterialInfoById("00149c84-1887-401a-9613-f2ce66de3d58");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetMaterialInfoByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("00149c84-1887-401a-9613-f2ce66de3d58");
        ids.add("002d6622-7d1e-48ec-be62-9b4d567619f5");

        List<MaterialInfoDO> result =  materialInfoDAO.getMaterialInfoByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetMaterialInfoBySome() {
        List<String> supplierId = new ArrayList<>();
        supplierId.add("80c243f3-3a9e-4a29-ac00-acc32ff03d7c");
        List<String> spec = new ArrayList<>();
        spec.add("0.7mm");
        spec.add("0.6mm");
        List<String> itemName = new ArrayList<>();
        itemName.add("不锈钢镀镍雾面304");
        List<Double> unitPrice = new ArrayList<>();
        unitPrice.add(50.0);

        List<MaterialInfoVO> result = materialInfoDAO.getMaterialInfoBySome(supplierId, spec, itemName, unitPrice);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
