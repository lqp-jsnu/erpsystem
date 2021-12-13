package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ManufactureProcessMasterDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ManufactureProcessMasterDAOTest {

    @Resource
    private ManufactureProcessMasterDAO manufactureProcessMasterDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = manufactureProcessMasterDAO.count(params);
        assertEquals(result, 1237);
    }

    @Test
    void testGetMaterialInventory() {
        Map<String, Object> params = new HashMap<>(0);
        List<ManufactureProcessMasterDO> result = manufactureProcessMasterDAO.getManufactureProcessMaster(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ManufactureProcessMasterDO manufactureProcessSlaveDO = new ManufactureProcessMasterDO();

        manufactureProcessSlaveDO.setUser("0");
        manufactureProcessSlaveDO.setCustomerOrderId("b19d5438-f722-48fa-bf2d-5f56fd26caed");

        manufactureProcessMasterDAO.insert(manufactureProcessSlaveDO);
    }

    @Test
    void testGetManufactureProcessMasterById() {
        ManufactureProcessMasterDO result = manufactureProcessMasterDAO.getManufactureProcessMasterById("00b209ac-e6e1-4f0e-b17d-552ce0635f45");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetManufactureProcessMasterByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("00b209ac-e6e1-4f0e-b17d-552ce0635f45");
        ids.add("0a651ad1-d3a0-4311-a9d4-f3375d0524bc");

        List<ManufactureProcessMasterDO> result = manufactureProcessMasterDAO.getManufactureProcessMasterByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
