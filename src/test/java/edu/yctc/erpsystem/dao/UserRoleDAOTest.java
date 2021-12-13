package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.UserRoleDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRoleDAOTest {

    @Autowired
    UserRoleDAO userRoleDAO;

    @Test
    void testGetRoleIdByUserId() {
        List<UserRoleDO> userRoles = userRoleDAO.getRoleIdByUserId("0");
        assertNotNull(userRoles);
        userRoles.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        UserRoleDO userRoleDO = new UserRoleDO();

        userRoleDO.setSyRoleId("0");
        userRoleDO.setSyUserId("0");

        userRoleDAO.insert(userRoleDO);
    }

    @Test
    void testInsertAll() {
        List<UserRoleDO> userRoleList = new ArrayList<>();

        for (int i = 0; i < 2; ++i) {
            UserRoleDO userRoleDO = new UserRoleDO();

            userRoleDO.setSyRoleId("0");
            userRoleDO.setSyUserId("0");

            userRoleList.add(userRoleDO);
        }

        userRoleDAO.insertAll(userRoleList);
    }

    @Test
    void testDeleteByRoleId() {
        userRoleDAO.deleteByRoleId("943a28b2-114f-487e-b697-629ac5983cf7");
    }

    @Test
    void testDeleteByUserId() {
        userRoleDAO.deleteByUserId("1131232");
    }

}
