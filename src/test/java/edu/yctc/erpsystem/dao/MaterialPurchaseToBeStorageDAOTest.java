package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialPurchaseToBeStorageDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialPurchaseToBeStorageDAOTest {

    @Autowired
    private MaterialPurchaseToBeStorageDAO materialPurchaseToBeStorageDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = materialPurchaseToBeStorageDAO.count(params);
        assertEquals(result, 166);
    }

    @Test
    void testGetMaterialPurchaseToBeStorage() {
        Map<String, Object> params = new HashMap<>(0);
        List<MaterialPurchaseToBeStorageDO> result = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorage(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsertAll() {
        List<MaterialPurchaseToBeStorageDO> materialPurchaseToBeStorageDOList = new ArrayList<>();

        MaterialPurchaseToBeStorageDO materialPurchaseToBeStorageDO = new MaterialPurchaseToBeStorageDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2019);  // 设置年
        gc.set(Calendar.MONTH, 1);  // 这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);// 设置天
        Date date = gc.getTime();

        materialPurchaseToBeStorageDO.setMaterialPurchaseId("0078e40a-7017-4577-8cd4-2a620f8c31cf");
        materialPurchaseToBeStorageDO.setInvoiceTitleId("4932da7c-a9a0-4d79-8970-fc456ecd7e83");
        materialPurchaseToBeStorageDO.setOrderNumber("111");
        materialPurchaseToBeStorageDO.setHopeDeliveryDate(date);
        materialPurchaseToBeStorageDO.setChecker(null);
        materialPurchaseToBeStorageDO.setCheckFlag("未审核");

        materialPurchaseToBeStorageDOList.add(materialPurchaseToBeStorageDO);

        materialPurchaseToBeStorageDAO.insertAll(materialPurchaseToBeStorageDOList);
    }

    @Test
    void testUpdateInAmountByMaterialPurchaseToBeStorageId() {
        MaterialPurchaseToBeStorageDO materialPurchaseToBeStorageDO = new MaterialPurchaseToBeStorageDO();
        materialPurchaseToBeStorageDO.setId("7d68b58f-f38c-4a17-8444-d92e637ef4c");
        materialPurchaseToBeStorageDO.setInAmount("50000.00");
        materialPurchaseToBeStorageDAO.updateInAmountByMaterialPurchaseToBeStorageId(materialPurchaseToBeStorageDO);
    }

    @Test
    void testUpdateAllInByMaterialPurchaseToBeStorageId() {
        MaterialPurchaseToBeStorageDO materialPurchaseToBeStorageDO = new MaterialPurchaseToBeStorageDO();
        materialPurchaseToBeStorageDO.setId("7d68b58f-f38c-4a17-8444-d92e637ef4c");
        materialPurchaseToBeStorageDAO.updateAllInByMaterialPurchaseToBeStorageId(materialPurchaseToBeStorageDO);
    }

    @Test
    void testGetMaterialPurchaseToBeStorageById() {
        MaterialPurchaseToBeStorageDO result = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageById("09be98b2-75fd-4542-b8c4-f38497a6f546");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetMaterialPurchaseToBeStorageByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("09be98b2-75fd-4542-b8c4-f38497a6f546");
        ids.add("0af7dc0d-5e70-45e1-a1e8-f078d0e93fd9");

        List<MaterialPurchaseToBeStorageDO> result = materialPurchaseToBeStorageDAO.getMaterialPurchaseToBeStorageByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
