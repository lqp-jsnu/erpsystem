package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.DbBackUpDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DbBackUpDAOTest {

    @Autowired
    private DbBackUpDAO dbBackUpDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = dbBackUpDAO.count(params);
        assertEquals(result, 377);
    }

    @Test
    void testGetDbBackUp() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("offset", 0);
        params.put("limit", 10);
        List<DbBackUpDO> dbBackUpDOS = dbBackUpDAO.getDbBackUp(params);
        assertNotNull(dbBackUpDOS);
        dbBackUpDOS.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dbName = "ERPSystem" + simpleDateFormat.format(date) + ".sql";

        DbBackUpDO dbBackUpDO = new DbBackUpDO();
        dbBackUpDO.setFileName(dbName);
        dbBackUpDO.setFileUrl("C:/dbBackUp/" + dbName);

        dbBackUpDAO.insert(dbBackUpDO);
    }

    @Test
    void testDeleteByFileName() {
        List<String> fileNames = new ArrayList<>();

        fileNames.add("ERPSystem2020-04-03 08:45:25.sql");

        dbBackUpDAO.deleteByFileNames(fileNames);
    }

}
