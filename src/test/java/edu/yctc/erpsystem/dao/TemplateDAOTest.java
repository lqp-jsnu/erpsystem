package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.TemplateDO;
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
class TemplateDAOTest {

    @Autowired
    private TemplateDAO templateDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = templateDAO.count(params);
        assertEquals(result, 3);
    }

    @Test
    void testGetTemplate() {
        Map<String, Object> params = new HashMap<>(0);
        List<TemplateDO> supplierDOS = templateDAO.getTemplate(params);
        assertNotNull(supplierDOS);
        supplierDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        TemplateDO templateDO = new TemplateDO();

        templateDO.setName("模板管理.xlsx");
        templateDO.setDictId("4e420c23-9e95-4967-92be-332afaad1193");
        templateDO.setUrl("xx");
        templateDO.setRemark("333333");

        templateDAO.insert(templateDO);
    }

    @Test
    void testDelete() {
        templateDAO.delete("92850944756a11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateTemplate() {
        TemplateDO templateDO = new TemplateDO();

        templateDO.setId("92850944756a11eaaf4254e1ad394a4a");
        templateDO.setDictId("4e420c23-9e95-4967-92be-332afaad1193");
        templateDO.setRemark("修改");

        templateDAO.updateTemplate(templateDO);
    }

    @Test
    void testUpdateUrl() {
        TemplateDO templateDO = new TemplateDO();

        templateDO.setId("92850944756a11eaaf4254e1ad394a4a");
        templateDO.setName("修改");
        templateDO.setUrl("修改");

        templateDAO.updateUrl(templateDO);
    }

    @Test
    void testGetCustomerById() {
        TemplateDO result = templateDAO.getTemplateById("caa526a7-c9e4-4100-8392-3253f25c95b8");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetCustomerByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("caa526a7-c9e4-4100-8392-3253f25c95b8");
        ids.add("d9e44b4c-7f5e-4f5a-8155-dae186e68528");
        List<TemplateDO> result = templateDAO.getTemplateByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
