package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.CustomerOrderDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerOrderDAOTest {

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = customerOrderDAO.count(params);
        assertEquals(result, 57);
    }

    @Test
    void testGetCustomerOrder() {
        Map<String, Object> params = new HashMap<>(0);
        List<CustomerOrderDO> result = customerOrderDAO.getCustomerOrder(params);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        CustomerOrderDO customerOrderDO = new CustomerOrderDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2019);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        customerOrderDO.setCustomerId("faf17dc3-ff4d-4a83-9c0e-4a373f4fb040");
        customerOrderDO.setOriginalProductId("2838c71d-8f22-40c7-a46e-50a07fe55a6a");
        customerOrderDO.setUser("0");
        customerOrderDO.setOrderNumber("LF201709006-1");
        customerOrderDO.setCreateTime(date);
        customerOrderDO.setOrderAmount("100");
        customerOrderDO.setDeliveryDate(date);
        customerOrderDO.setPlanArrivalDate(date);
        customerOrderDO.setOrderDate(date);
        customerOrderDO.setActualArrivalDate(date);
        customerOrderDO.setRemark("RRR");

        customerOrderDAO.insert(customerOrderDO);
    }

    @Test
    void testInsertAll() {
        List<CustomerOrderDO> customerOrderDOList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            CustomerOrderDO customerOrderDO = new CustomerOrderDO();

            GregorianCalendar gc = new GregorianCalendar();
            gc.set(Calendar.YEAR, 2019);  //设置年
            gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
            gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
            Date date = gc.getTime();

            customerOrderDO.setCustomerId("faf17dc3-ff4d-4a83-9c0e-4a373f4fb040");
            customerOrderDO.setOriginalProductId("2838c71d-8f22-40c7-a46e-50a07fe55a6a");
            customerOrderDO.setUser("0");
            customerOrderDO.setOrderNumber("LF201709006-1");
            customerOrderDO.setCreateTime(date);
            customerOrderDO.setOrderAmount("100");
            customerOrderDO.setDeliveryDate(date);
            customerOrderDO.setPlanArrivalDate(date);
            customerOrderDO.setOrderDate(date);
            customerOrderDO.setActualArrivalDate(date);
            customerOrderDO.setRemark("RRR");

            customerOrderDOList.add(customerOrderDO);
        }

        customerOrderDAO.insertAll(customerOrderDOList);
    }

    @Test
    void testDelete() {
        customerOrderDAO.delete("55ca928580dd11ea98b954e1ad394a4a");
    }

    @Test
    void testUpdateFinalCheckFlagById() {
        CustomerOrderDO customerOrderDO = new CustomerOrderDO();

        customerOrderDO.setId("55ca928580dd11ea98b954e1ad394a4a");
        customerOrderDO.setFinalCheckFlag( "已通过" );

        customerOrderDAO.updateFinalCheckFlagById(customerOrderDO);
    }

    @Test
    void testUpdateCustomerOrderDetailsById() {
        CustomerOrderDO customerOrderDO = new CustomerOrderDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2020);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        customerOrderDO.setId("55ca928580dd11ea98b954e1ad394a4a");
        customerOrderDO.setProductAmount( "200" );
        customerOrderDO.setOrderAmount( "200" );
        customerOrderDO.setEveryOrderAmount( "200" );
        customerOrderDO.setEveryProductAmount( "200" );
        customerOrderDO.setDeliveryDate(date);
        customerOrderDO.setPlanArrivalDate(date);
        customerOrderDO.setOrderDate(date);
        customerOrderDO.setActualArrivalDate(date);
        customerOrderDO.setIsArrival("否");

        customerOrderDAO.updateCustomerOrderDetailsById(customerOrderDO);
    }

    @Test
    void testUpdateCustomerOrder() {
        CustomerOrderDO customerOrderDO = new CustomerOrderDO();

        GregorianCalendar gc = new GregorianCalendar();
        gc.set( Calendar.YEAR, 2030);  //设置年
        gc.set(Calendar.MONTH, 1);  //这里0是1月..以此向后推
        gc.set(Calendar.DAY_OF_MONTH, 1);//设置天
        Date date = gc.getTime();

        customerOrderDO.setId("55ca928580dd11ea98b954e1ad394a4a");
        customerOrderDO.setOrderAmount( "1" );
        customerOrderDO.setDeliveryDate(date);
        customerOrderDO.setPlanArrivalDate(date);
        customerOrderDO.setOrderDate(date);
        customerOrderDO.setActualArrivalDate(date);
        customerOrderDO.setIsArrival("是");

        customerOrderDAO.updateCustomerOrder(customerOrderDO);
    }

    @Test
    void testUpdateCheckerById() {
        CustomerOrderDO customerOrderDO = new CustomerOrderDO();

        customerOrderDO.setId("55ca928580dd11ea98b954e1ad394a4a");
        customerOrderDO.setCheckFlag("已通过");
        customerOrderDO.setChecker("0");

        customerOrderDAO.updateCheckerById(customerOrderDO);
    }

    @Test
    void testMake() {
        customerOrderDAO.make("55ca928580dd11ea98b954e1ad394a4a");
    }

    @Test
    void testFinish() {
        customerOrderDAO.finish("55ca928580dd11ea98b954e1ad394a4a");
    }

    @Test
    void testGetCustomerOrderById() {
        CustomerOrderDO result =  customerOrderDAO.getCustomerOrderById("059107bc-de0e-4f5a-a575-3d697e2d5939");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetCustomerOrderByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("059107bc-de0e-4f5a-a575-3d697e2d5939");
        ids.add("06fcb48a-8d83-4229-a072-66e0043d6ebd");

        List<CustomerOrderDO> result =  customerOrderDAO.getCustomerOrderByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

}
