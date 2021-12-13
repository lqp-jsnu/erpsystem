package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.UserOrganizationDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserOrganizationDAOTest {

    @Autowired
    private UserOrganizationDAO userOrganizationDAO;

    @Test
    void testGetOrganizationIdByUserId() {
        List<UserOrganizationDO> userOrganizations = userOrganizationDAO.getOrganizationIdByUserId("0");
        assertNotNull(userOrganizations);
        userOrganizations.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        UserOrganizationDO userOrganizationDO = new UserOrganizationDO();

        userOrganizationDO.setSyOrganizationId("a86e41366d3311eab3f154e1ad394a4a");
        userOrganizationDO.setSyUserId("0");

        userOrganizationDAO.insert(userOrganizationDO);
    }

    @Test
    void testInsertAll() {
        List<UserOrganizationDO> userOrganizationList = new ArrayList<>();

        for (int i = 0; i < 2; ++i) {
            UserOrganizationDO userOrganizationDO = new UserOrganizationDO();

            userOrganizationDO.setSyOrganizationId("a86e41366d3311eab3f154e1ad394a4a");
            userOrganizationDO.setSyUserId("0");

            userOrganizationList.add(userOrganizationDO);
        }

        userOrganizationDAO.insertAll(userOrganizationList);
    }

    @Test
    void testDeleteUserOrganizationByOrganizationId() {
        userOrganizationDAO.deleteUserOrganizationByOrganizationId("a86e41366d3311eab3f154e1ad394a4a");
    }

    @Test
    void testDeleteUserOrganizationByUserId() {
        userOrganizationDAO.deleteUserOrganizationByUserId("0");
    }

}
