package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ManufactureProcessSlaveDO;
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
class ManufactureProcessSlaveDAOTest {

    @Autowired
    private ManufactureProcessSlaveDAO manufactureProcessSlaveDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = manufactureProcessSlaveDAO.count(params);
        assertEquals(result, 77);
    }

    @Test
    void testGetManufactureProcessSlaveInfo() {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("offset", 0);
        List<ManufactureProcessSlaveDO> result = manufactureProcessSlaveDAO.getManufactureProcessSlave(params);
        assertNotNull(result);

        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ManufactureProcessSlaveDO manufactureProcessSlaveDO = new ManufactureProcessSlaveDO();

        manufactureProcessSlaveDO.setManufactureProcessMasterId("c5039ec7-d97e-45ea-9d9e-0a26a52a52db");
        manufactureProcessSlaveDO.setEveryAmount("50");
        manufactureProcessSlaveDO.setJobTicketNumber("20200329003-001");
        manufactureProcessSlaveDO.setIsIntoMake("否");
        manufactureProcessSlaveDO.setEveryOrderAmount("50");

        manufactureProcessSlaveDAO.insert(manufactureProcessSlaveDO);
    }

    @Test
    void testInsertAll() {
        List<ManufactureProcessSlaveDO> manufactureProcessSlaveList = new ArrayList<>();

        for (int i = 0; i < 2; ++i) {
            ManufactureProcessSlaveDO manufactureProcessSlaveDO = new ManufactureProcessSlaveDO();

            manufactureProcessSlaveDO.setManufactureProcessMasterId("c5039ec7-d97e-45ea-9d9e-0a26a52a52db");
            manufactureProcessSlaveDO.setEveryAmount("50");
            manufactureProcessSlaveDO.setJobTicketNumber("20200329003-001");
            manufactureProcessSlaveDO.setIsIntoMake("否");
            manufactureProcessSlaveDO.setEveryOrderAmount("50");

            manufactureProcessSlaveList.add(manufactureProcessSlaveDO);
        }

        manufactureProcessSlaveDAO.insertAll(manufactureProcessSlaveList);
    }

    @Test
    void testUpdateIsMaterialExport() {
        manufactureProcessSlaveDAO.updateIsMaterialExport("029c7481-c8d7-42b0-91c6-60049a65c794", "是");
    }

    @Test
    void testUpdateIsGeneralManufactureProcess() {
        manufactureProcessSlaveDAO.updateIsGeneralManufactureProcess("029c7481-c8d7-42b0-91c6-60049a65c794");
    }

    @Test
    void testUpdateIsIntoMake() {
        manufactureProcessSlaveDAO.updateIsIntoMake("029c7481-c8d7-42b0-91c6-60049a65c794");
    }

    @Test
    void testUpdateIsExportCheckPass() {
        manufactureProcessSlaveDAO.updateIsExportCheckPass("029c7481-c8d7-42b0-91c6-60049a65c794");
    }

    @Test
    void testOriginalProductBySome() {
        List<ManufactureProcessSlaveDO> result = manufactureProcessSlaveDAO.getManufactureProcessSlaveBySome("a0b66c94-04d7-45b1-b8e5-8d89e0ca8ecc", null);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testGetManufactureProcessSlaveById() {
        ManufactureProcessSlaveDO result =  manufactureProcessSlaveDAO.getManufactureProcessSlaveById("029c7481-c8d7-42b0-91c6-60049a65c794");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetManufactureProcessSlaveByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("029c7481-c8d7-42b0-91c6-60049a65c794");
        ids.add("06475eab-e75e-4ca7-8d44-460568c5956b");

        List<ManufactureProcessSlaveDO> result =  manufactureProcessSlaveDAO.getManufactureProcessSlaveByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testChangeIsZeroProductInHouseByIdWhenInsert() {
        manufactureProcessSlaveDAO.changeIsZeroProductInHouseByIdWhenInsert("bbb14815-7cfa-4d46-957c-3a6b8c8a12d6");
    }

    @Test
    void testChangeIsZeroProductInHouseByIdWhenDelete() {
        manufactureProcessSlaveDAO.changeIsZeroProductInHouseByIdWhenDelete("bbb14815-7cfa-4d46-957c-3a6b8c8a12d6");
    }

}
