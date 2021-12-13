package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ResourceDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResourceDAOTest {

    @Autowired
    private ResourceDAO resourceDAO;

    @Test
    void testGetMenuResourcesByUserId() {
        List<ResourceDO> resources = resourceDAO.getMenuResourcesByUserId("0f5a3b72-5f3e-42ee-b4f8-2eec1fa40b98");
        assertNotNull(resources);
        resources.forEach(System.out::println);
    }

    @Test
    void testGetAllResourcesByUserId() {
        List<ResourceDO> resources = resourceDAO.getAllResourcesByUserId("0f5a3b72-5f3e-42ee-b4f8-2eec1fa40b98");
        assertNotNull(resources);
        resources.forEach(System.out::println);
    }

    @Test
    void testGetAllResources() {
        List<ResourceDO> resources = resourceDAO.getAllResources();
        assertNotNull(resources);
        resources.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ResourceDO resourceDO = new ResourceDO();

        resourceDO.setIcons("xxx");
        resourceDO.setName("xxx");
        resourceDO.setSeq(1);
        resourceDO.setSyResourceTypeId("0");
        resourceDO.setUrl("xxx");

        resourceDAO.insert(resourceDO);

        System.out.println(resourceDO.getId());
    }

    @Test
    void testDelete() {
        resourceDAO.delete("0a79d667753411eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateResourcesById() {
        ResourceDO resourceDO = new ResourceDO();

        resourceDO.setId("0a79d667753411eaaf4254e1ad394a4a");
        resourceDO.setIcons("yyy");
        resourceDO.setName("yyy");
        resourceDO.setSeq(10);
        resourceDO.setSyResourceTypeId("1");
        resourceDO.setUrl("yyy");
        resourceDO.setDescription("yyy");

        resourceDAO.updateResources(resourceDO);
    }

    @Test
    void testGetResourceById() {
        ResourceDO resources = resourceDAO.getResourceById("019fbe89-dd3a-491f-aace-4a5c4eefa5f6");
        assertNotNull(resources);
        System.out.println(resources);
    }

    @Test
    void testGetResourceByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("019fbe89-dd3a-491f-aace-4a5c4eefa5f6");
        ids.add("01b6d16b-d90f-4237-bc63-863cd603e17b");

        List<ResourceDO> resources = resourceDAO.getResourceByIds(ids);
        assertNotNull(resources);
        resources.forEach(System.out::println);
    }

}
