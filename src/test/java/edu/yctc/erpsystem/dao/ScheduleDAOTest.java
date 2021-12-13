package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ScheduleDO;
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
class ScheduleDAOTest {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        params.put("isFinish", "是");
        int result = scheduleDAO.count(params);
        assertEquals(result, 66);
    }

    @Test
    void testGetSchedule() {
        Map<String, Object> params = new HashMap<>(0);
        List<ScheduleDO> result = scheduleDAO.getSchedule(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ScheduleDO materialExportDO = new ScheduleDO();

        materialExportDO.setMaterialExportId("08d967d8-36cb-4c87-ae3c-c10b2450a202");

        scheduleDAO.insert(materialExportDO);
    }

    @Test
    void testUpdateSchedule() {
        ScheduleDO scheduleDO = new ScheduleDO();

        scheduleDO.setId("00bcfb81-2f50-42b2-84d0-b5323ba1fb1a");
        scheduleDO.setQie("是");
        scheduleDO.setHan("是");
        scheduleDO.setRao("是");
        scheduleDO.setChai("是");
        scheduleDO.setTu("是");

        scheduleDAO.updateSchedule(scheduleDO);
    }

    @Test
    void testUpdateIsFinish() {
        scheduleDAO.updateIsFinish("00bcfb81-2f50-42b2-84d0-b5323ba1fb1a");
    }

    @Test
    void testGetScheduleById() {
        ScheduleDO result = scheduleDAO.getScheduleById("02f16a0c-3248-4b9d-bd1f-0a81c37468f5");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetScheduleByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("02f16a0c-3248-4b9d-bd1f-0a81c37468f5");
        ids.add("0662e78a-0248-414a-a697-2c81b7dab10d");
        List<ScheduleDO> result = scheduleDAO.getScheduleByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
