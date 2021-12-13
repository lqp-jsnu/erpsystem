package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.DictDO;
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
class DictDAOTest {

    @Autowired
    private DictDAO dictDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = dictDAO.count(params);
        assertEquals(result, 7);
    }

    @Test
    void testGetDict() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("offset", 0);
        params.put("limit", 10);
        List<DictDO> dictDOS = dictDAO.getDict(params);
        assertNotNull(dictDOS);
        dictDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        DictDO dictDO = new DictDO();

        dictDO.setType("xx");
        dictDO.setRemark("xx");
        dictDO.setValue("xx");
        dictDO.setName("xx");

        dictDAO.insert(dictDO);
    }

    @Test
    void testDelete() {
        dictDAO.delete("86b635b4754911eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateDict() {
        DictDO dictDO = new DictDO();

        dictDO.setId("86b635b4754911eaaf4254e1ad394a4a");
        dictDO.setName("yy");
        dictDO.setRemark("y");
        dictDO.setValue("yy");
        dictDO.setType("yy");

        dictDAO.updateDict(dictDO);
    }

    @Test
    void testGetDictById() {
        DictDO result = dictDAO.getDictById("1cad8d68-4d85-4353-aeac-ad992a01d03a");
        assertNotNull( result );
        System.out.println(result);
    }

    @Test
    void testGetDictByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("1cad8d68-4d85-4353-aeac-ad992a01d03a");
        ids.add("287c574e-6998-48f6-bc73-0bcacf9396fc");
        List<DictDO> result = dictDAO.getDictByIds(ids);
        assertNotNull( result );
        result.forEach( System.out::println );
    }

}
