package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.ManualExportDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ManualExportDAOTest {

    @Autowired
    private ManualExportDAO manualExportDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = manualExportDAO.count(params);
        assertEquals(result, 4236);
    }

    @Test
    void testGetManualExport() {
        Map<String, Object> params = new HashMap<>(0);
        List<ManualExportDO> result = manualExportDAO.getManualExport(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        ManualExportDO manualExportDO = new ManualExportDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set( Calendar.YEAR, 2019);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        //获取订单号：时间
        Date dateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String serialnumberString = sdf.format(dateTime);

        manualExportDO.setMaterialInventoryId("3aee8f4a-5981-432c-837c-20ed7c07f12a");
        manualExportDO.setNumber(serialnumberString);
        manualExportDO.setRemark("R");
        manualExportDO.setAmount("1");
        manualExportDO.setUser("0");
        manualExportDO.setDate(date);

        manualExportDAO.insert(manualExportDO);
    }

    @Test
    void testDelete() {
        manualExportDAO.delete("7bcf6d327d7311ea98b954e1ad394a4a");
    }

    @Test
    void testUpdateManualExport() {
        ManualExportDO manualExportDO = new ManualExportDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set( Calendar.YEAR, 2020);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        manualExportDO.setId("7bcf6d327d7311ea98b954e1ad394a4a");
        manualExportDO.setAmount("100");
        manualExportDO.setDate(date);
        manualExportDO.setRemark("RRR");

        manualExportDAO.updateManualExport(manualExportDO);
    }

    @Test
    void testUpdateCheckerById() {
        ManualExportDO manualExportDO = new ManualExportDO();

        manualExportDO.setId("7bcf6d327d7311ea98b954e1ad394a4a");
        manualExportDO.setChecker("0");
        manualExportDO.setCheckFlag("已通过");

        manualExportDAO.updateCheckerById(manualExportDO);
    }

    @Test
    void testGetManualExportById() {
        ManualExportDO result = manualExportDAO.getManualExportById("0005b34c-e093-463d-82b4-cab5bc35e7f1");
        assertNotNull( result );
        System.out.println(result);
    }

    @Test
    void testGetManualExportByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("0005b34c-e093-463d-82b4-cab5bc35e7f1");
        ids.add("00111264-f36a-4971-8467-47341cb538c4");
        List<ManualExportDO> result = manualExportDAO.getManualExportByIds(ids);
        assertNotNull( result );
        result.forEach( System.out::println );
    }

}
