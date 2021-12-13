package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.RoleDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoleDAOTest {

    @Autowired
    private RoleDAO roleDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = roleDAO.count(params);
        assertEquals(result, 10);
    }

    @Test
    void testGetRole() {
        Map<String, Object> params = new HashMap<>(0);
        List<RoleDO> result = roleDAO.getRole(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        RoleDO roleDO = new RoleDO();

        roleDO.setIcons(null);
        roleDO.setDescription("xx");
        roleDO.setSeq(100);
        roleDO.setName("xx");

        roleDAO.insert(roleDO);
    }

    @Test
    void testDelete() {
        roleDAO.delete("9dc869e0753911eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateRole() {
        RoleDO roleDO = new RoleDO();

        roleDO.setName("yy");
        roleDO.setSeq(100);
        roleDO.setDescription("yyy");
        roleDO.setId("9dc869e0753911eaaf4254e1ad394a4a");

        roleDAO.updateRole(roleDO);
    }

}
