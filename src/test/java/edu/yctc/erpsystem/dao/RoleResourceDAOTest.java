package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.RoleResourceDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoleResourceDAOTest {
    @Autowired
    private RoleResourceDAO roleResourceDAO;

    @Test
    void testGetResourceIdByRoleId() {
        List<RoleResourceDO> result = roleResourceDAO.getResourceIdByRoleId("0");
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        RoleResourceDO roleResourceDO = new RoleResourceDO();

        roleResourceDO.setSyRoleId("0");
        roleResourceDO.setSyResourceId("zytj");

        roleResourceDAO.insert(roleResourceDO);
    }

    @Test
    void testDeleteByRoleId() {
        roleResourceDAO.deleteByRoleId("943a28b2-114f-487e-b697-629ac5983cf7");
    }

}
