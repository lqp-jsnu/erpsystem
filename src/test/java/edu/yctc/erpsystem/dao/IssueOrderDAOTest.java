package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.IssueOrderDO;
import edu.yctc.erpsystem.entity.MaterialDumpDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 生产补单
 *
 * @author smile
 */
@SpringBootTest
class IssueOrderDAOTest {

    @Autowired
    private IssueOrderDAO issueOrderDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = issueOrderDAO.count(params);
        assertEquals(result, 1);
    }

    @Test
    void testGetIssueOrder() {
        Map<String, Object> params = new HashMap<>(0);
        List<IssueOrderDO> result = issueOrderDAO.getIssueOrder(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        IssueOrderDO issueOrderDO = new IssueOrderDO();

        issueOrderDO.setCustomerOrderId("059107bc-de0e-4f5a-a575-3d697e2d5939");
        issueOrderDO.setIssueAmount("100");
        issueOrderDO.setUser("0");
        issueOrderDO.setRemark("bei");

        issueOrderDAO.insert(issueOrderDO);
    }

    @Test
    void testDelete() {
        issueOrderDAO.delete("7a19f852823b11ea98b954e1ad394a4a");
    }

    @Test
    void testUpdateCheckerById() {
        IssueOrderDO issueOrderDO = new IssueOrderDO();

        issueOrderDO.setId("7a19f852823b11ea98b954e1ad394a4a");
        issueOrderDO.setChecker("0");
        issueOrderDO.setCheckFlag("已通过");

        issueOrderDAO.updateCheckerById(issueOrderDO);
    }

    @Test
    void testMake() {
        issueOrderDAO.make("7a19f852823b11ea98b954e1ad394a4a");
    }

}
