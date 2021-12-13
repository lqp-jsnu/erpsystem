package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialPurchaseDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialPurchaseDAOTest {

    @Autowired
    private MaterialPurchaseDAO materialPurchaseDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialPurchaseDAO.count(params);
        assertEquals( result, 1 );
    }

    @Test
    void testGetMaterialPurchase() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("offset", 0);
        params.put("limit", 10);
        List<MaterialPurchaseDO> result = materialPurchaseDAO.getMaterialPurchase(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        MaterialPurchaseDO materialPurchaseDO = new MaterialPurchaseDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2019);  // 设置年
        gc.set(Calendar.MONTH, 1);  // 这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);// 设置天
        Date date = gc.getTime();

        materialPurchaseDO.setAmount("500.00");
        materialPurchaseDO.setUser("0");
        materialPurchaseDO.setRemark(null);
        materialPurchaseDO.setHopeDeliveryDate(date);
        materialPurchaseDO.setMaterialInfoId("807d78e6-e6d1-40f3-88a1-7f8ab4d744a6");
        materialPurchaseDO.setChecker(null);
        materialPurchaseDO.setCheckFlag("未审核");
        materialPurchaseDO.setSupplierId("8973f5f8-05b4-4341-9e38-73ac5322f38d");

        materialPurchaseDAO.insert(materialPurchaseDO);
    }

    @Test
    void testInsertAll() {
        List<MaterialPurchaseDO> materialPurchaseList = new ArrayList<>();

        MaterialPurchaseDO materialPurchaseDO = new MaterialPurchaseDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2019);  // 设置年
        gc.set(Calendar.MONTH, 1);  // 这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);// 设置天
        Date date = gc.getTime();

        materialPurchaseDO.setAmount("500.00");
        materialPurchaseDO.setUser("0");
        materialPurchaseDO.setRemark(null);
        materialPurchaseDO.setHopeDeliveryDate(date);
        materialPurchaseDO.setMaterialInfoId("807d78e6-e6d1-40f3-88a1-7f8ab4d744a6");
        materialPurchaseDO.setChecker(null);
        materialPurchaseDO.setCheckFlag("未审核");
        materialPurchaseDO.setSupplierId("8973f5f8-05b4-4341-9e38-73ac5322f38d");

        materialPurchaseList.add(materialPurchaseDO);

        materialPurchaseDAO.insertAll(materialPurchaseList);
    }

    @Test
    void testDelete() {
        materialPurchaseDAO.delete("e1f1e1b6755011eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateCheckerById() {
        MaterialPurchaseDO materialPurchaseDO = new MaterialPurchaseDO();

        materialPurchaseDO.setId("e1f1e1b6755011eaaf4254e1ad394a4a");
        materialPurchaseDO.setChecker("0");
        materialPurchaseDO.setCheckFlag("已通过");

        materialPurchaseDAO.updateCheckerById(materialPurchaseDO);
    }

    @Test
    void testUpdateMaterialPurchase() {
        MaterialPurchaseDO materialPurchaseDO = new MaterialPurchaseDO();

        materialPurchaseDO.setId("e1f1e1b6755011eaaf4254e1ad394a4a");
        materialPurchaseDO.setAmount("100");
        materialPurchaseDO.setRemark("aaaa");

        materialPurchaseDAO.updateMaterialPurchase(materialPurchaseDO);
    }

    @Test
    void testUpdateIsExportByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("e1f1e1b6755011eaaf4254e1ad394a4a");

        materialPurchaseDAO.updateIsExportByIds(ids);
    }

    @Test
    void getMaterialPurchaseById() {
        MaterialPurchaseDO result = materialPurchaseDAO.getMaterialPurchaseById("4a28f30b-cb21-4fe5-bcc0-d7371a351099");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void getMaterialPurchaseByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("4a28f30b-cb21-4fe5-bcc0-d7371a351099");
        ids.add("46600d5f-c8f8-42e6-a75a-46c15296ba75");

        List<MaterialPurchaseDO> result = materialPurchaseDAO.getMaterialPurchaseByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
