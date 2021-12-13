package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.OrganizationDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrganizationDAOTest {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Test
    void testGetAllOrganization() {
        List<OrganizationDO> result = organizationDAO.getAllOrganization();
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        OrganizationDO organizationDO = new OrganizationDO();

        organizationDO.setSeq(0);
        organizationDO.setAddress("xx");
        organizationDO.setCode("xx");
        organizationDO.setIcons("ext-icon-anchor");
        organizationDO.setName("xx");
        organizationDO.setSyOrganizationId(null);

        organizationDAO.insert(organizationDO);
    }

    @Test
    void testDelete() {
        organizationDAO.delete("ec709846753111eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateOrganization() {
        OrganizationDO organizationDO = new OrganizationDO();

        organizationDO.setName("yy");
        organizationDO.setSyOrganizationId(null);
        organizationDO.setIcons("ext-icon-bullet-wrench");
        organizationDO.setCode("yy");
        organizationDO.setAddress("yy");
        organizationDO.setSeq(1);
        organizationDO.setId("ec709846753111eaaf4254e1ad394a4a");

        organizationDAO.updateOrganization(organizationDO);
    }
}
