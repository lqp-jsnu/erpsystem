package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ZeroProductStorageDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ZeroProdStorageDAOTest {

    @Autowired
    private ZeroProdStorageDAO zeroProdStorageDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = zeroProdStorageDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void testGetZeroProdStorage() {
        Map<String, Object> params = new HashMap<>(0);
        List<ZeroProductStorageDO> result = zeroProdStorageDAO.getZeroProdStorage(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ZeroProductStorageDO zeroProdStorageDO = new ZeroProductStorageDO();

        zeroProdStorageDO.setManufactureProcessSlaveId("80c8a7ee-048a-4010-9fc8-2f90c5878e1a");
        zeroProdStorageDO.setStorageAmount("100000");
        zeroProdStorageDO.setWarehouseId("94f215f9-c028-489c-a92c-fa146a194ac6");
        zeroProdStorageDO.setUser("0");
        zeroProdStorageDO.setRemark("10012");
        zeroProdStorageDO.setProductQuantity("100");
        zeroProdStorageDO.setIsProductStorage("是");

        zeroProdStorageDAO.insert(zeroProdStorageDO);
    }

    @Test
    void testDelete() {
        zeroProdStorageDAO.delete("b83e1e4a756b11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateZeroProdStorage() {
        ZeroProductStorageDO zeroProdStorageDO = new ZeroProductStorageDO();

        zeroProdStorageDO.setId("b83e1e4a756b11eaaf4254e1ad394a4a");
        zeroProdStorageDO.setRemark("12654");
        zeroProdStorageDO.setProductQuantity("651");
        zeroProdStorageDO.setWarehouseId("089da594-d2bd-4086-a971-3e2cd4ac85dc");
        zeroProdStorageDO.setStorageAmount("1556");

        zeroProdStorageDAO.updateZeroProdStorage(zeroProdStorageDO);
    }

    @Test
    void testUpdateCheckerById() {
        ZeroProductStorageDO zeroProdStorageDO = new ZeroProductStorageDO();

        zeroProdStorageDO.setId("b83e1e4a756b11eaaf4254e1ad394a4a");
        zeroProdStorageDO.setChecker("0");
        zeroProdStorageDO.setCheckFlag("已通过");

        zeroProdStorageDAO.updateCheckerById(zeroProdStorageDO);
    }

}
