package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.OnlineDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OnlineDAOTest {

    @Resource
    private OnlineDAO onlineDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = onlineDAO.count(params);
        assertEquals(result, 3665);
    }

    @Test
    void testGetOnline() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("offset", 0);
        params.put("limit", 10);
        List<OnlineDO> onlineDOS = onlineDAO.getOnline(params);
        assertNotNull(onlineDOS);
        onlineDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        OnlineDO onlineDO = new OnlineDO();

        onlineDO.setIp("192.168.1.1");
        onlineDO.setLoginName("许志强");
        onlineDO.setType("1");

        onlineDAO.insert(onlineDO);
    }

}
