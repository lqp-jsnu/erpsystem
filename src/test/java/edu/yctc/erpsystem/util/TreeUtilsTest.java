package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.dao.ResourceDAO;
import edu.yctc.erpsystem.vo.TreeViewVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static edu.yctc.erpsystem.util.TreeUtils.getTree;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TreeUtilsTest {

    @Autowired
    private ResourceDAO resourceDAO;

    @Test
    void testGetTree() {
        List<TreeViewVO> result = getTree(resourceDAO.getMenuResourcesByUserId("0"));
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
