package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.CustomerDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>( 0 );
        int result = customerDAO.count( params );
        assertEquals( result, 122 );
    }

    @Test
    void testGetCustomer() {
        Map<String, Object> params = new HashMap<>( 2 );
        params.put( "limit", 10 );
        params.put( "offset", 0 );
        List<CustomerDO> result = customerDAO.getCustomer( params );
        assertNotNull( result );
        result.forEach( System.out::println );
    }

    @Test
    void testInsert() {
        CustomerDO customerDO = new CustomerDO();

        customerDO.setCode("0000");
        customerDO.setName("邳州");
        customerDO.setDeliveryTime("无期");
        customerDO.setFixedTelephone("333333");

        customerDAO.insert(customerDO);
    }

    @Test
    void testInsertAll() {
        List<CustomerDO> customerDO = new ArrayList<>();

        for (int i = 0; i < 2; ++i) {
            CustomerDO temp = new CustomerDO();

            temp.setCode("0000");
            temp.setName("邳州");
            temp.setDeliveryTime("无期");
            temp.setFixedTelephone("333333");

            customerDO.add(temp);
        }

        customerDAO.insertAll(customerDO);
    }

    @Test
    void testDelete() {
        customerDAO.delete("369011f0754211eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateCustomer() {
        CustomerDO customerDO = new CustomerDO();

        customerDO.setId("369011f0754211eaaf4254e1ad394a4a");
        customerDO.setCode("1111");
        customerDO.setName("修改");
        customerDO.setContact("许淼");
        customerDO.setFixedTelephone("10032");

        customerDAO.updateCustomer(customerDO);
    }

    @Test
    void testGetCustomerById() {
        CustomerDO result = customerDAO.getCustomerById("00ac4e5e-0abd-4c77-8c89-e2206d21d805");
        assertNotNull( result );
        System.out.println(result);
    }

    @Test
    void testGetCustomerByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("00ac4e5e-0abd-4c77-8c89-e2206d21d805");
        ids.add("0506a1f7-0b73-495c-bde0-5a19894908c9");
        List<CustomerDO> result = customerDAO.getCustomerByIds(ids);
        assertNotNull( result );
        result.forEach( System.out::println );
    }

}
