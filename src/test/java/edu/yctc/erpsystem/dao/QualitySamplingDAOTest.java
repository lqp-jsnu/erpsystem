package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.QualitySamplingDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class QualitySamplingDAOTest {

    @Autowired
    private QualitySamplingDAO qualitySamplingDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = qualitySamplingDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void testGetQualitySampling() {
        Map<String, Object> params = new HashMap<>(0);
        List<QualitySamplingDO> result = qualitySamplingDAO.getQualitySampling(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        QualitySamplingDO qualitySamplingDO = new QualitySamplingDO();

        qualitySamplingDO.setManufactureProcessSlaveId("fae90efc-63d4-4e47-933a-2f2db542b94f");
        qualitySamplingDO.setPackageNumber("15");
        qualitySamplingDO.setPackageOperator("9f3a7530-cbb8-4103-bee4-b392c6537689");
        qualitySamplingDO.setSampleNumber("123");
        qualitySamplingDO.setAql("5");
        qualitySamplingDO.setPassNumber("7");
        qualitySamplingDO.setFailNumber("54");
        qualitySamplingDO.setCheckOperator("a38740a7-0058-4bf7-a3b6-9f69cb791839");
        qualitySamplingDO.setSampleResult("合格");
        qualitySamplingDO.setUser("0");

        qualitySamplingDAO.insert(qualitySamplingDO);
    }

    @Test
    void testDelete() {
        qualitySamplingDAO.delete("a8da33c7756811eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateQualitySampling() {

        QualitySamplingDO qualitySamplingDO = new QualitySamplingDO();

        qualitySamplingDO.setId("a8da33c7756811eaaf4254e1ad394a4a");
        qualitySamplingDO.setManufactureProcessSlaveId("06475eab-e75e-4ca7-8d44-460568c5956b");
        qualitySamplingDO.setPackageOperator("0f5a3b72-5f3e-42ee-b4f8-2eec1fa40b98");
        qualitySamplingDO.setPassNumber("15");
        qualitySamplingDO.setFailNumber("123");
        qualitySamplingDO.setCheckOperator("0f5a3b72-5f3e-42ee-b4f8-2eec1fa40b98");
        qualitySamplingDO.setPackageNumber("100");
        qualitySamplingDO.setSampleNumber("22");
        qualitySamplingDO.setAql("515");
        qualitySamplingDO.setSampleResult("不合格");
        qualitySamplingDO.setRemark("1231231");

        qualitySamplingDAO.updateQualitySampling(qualitySamplingDO);
    }

    @Test
    void testUpdateCheckerById() {
        QualitySamplingDO qualitySamplingDO = new QualitySamplingDO();

        qualitySamplingDO.setId("a8da33c7756811eaaf4254e1ad394a4a");
        qualitySamplingDO.setChecker("0");
        qualitySamplingDO.setCheckFlag("未通过");

        qualitySamplingDAO.updateCheckerById(qualitySamplingDO);
    }

}
