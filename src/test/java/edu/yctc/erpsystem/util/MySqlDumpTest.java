package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.entity.ResultDO;
import edu.yctc.erpsystem.interceptor.ConfigBeanValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MySqlDumpTest {

    @Autowired
    private ConfigBeanValue configBeanValue;

    @Test
    void testMySqlDump() {
        ResultDO<String> result = MySqlDump.mySqlDump("ERPSystem2020-01-18-19-45-31.sql", configBeanValue.getUsername(), configBeanValue.getPassword());
        assert(result.isSuccess());
    }

}
