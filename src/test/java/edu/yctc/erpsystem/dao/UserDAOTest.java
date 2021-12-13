package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    void testCount() {
        Map<String, Object> params = new HashMap<>(0);
        int result = userDAO.count(params);
        assertEquals(result, 16);
    }

    @Test
    void testGetUser() {
        Map<String, Object> params = new HashMap<>(0);
        List<UserDO> userDO = userDAO.getUser(params);
        userDO.forEach(System.out::println);
    }

    @Test
    void testLogin() {
        UserDO userDO = userDAO.login("superadmin", DigestUtils.md5DigestAsHex("123456".getBytes()));
        assertEquals("0", userDO.getId());
    }

    @Test
    void testGetUserByLoginName() {
        UserDO userDO = userDAO.getUserByLoginName("superadmin");
        assertEquals("0", userDO.getId());
    }

    @Test
    void testGetUserById() {
        UserDO result = userDAO.getUserById("0");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void testGetUserByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("0");
        ids.add("0");
        List<UserDO> result = userDAO.getUserByIds(ids);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        UserDO userDO = new UserDO();

        userDO.setName("xxx");
        userDO.setLoginName("yyy");
        userDO.setPhoto("no");
        userDO.setSex("1");
        userDO.setPwd(DigestUtils.md5DigestAsHex("123456".getBytes()));

        userDAO.insert(userDO);
    }

    @Test
    void testDelete() {
        userDAO.delete("12929265753e11eaaf4254e1ad394a4a");
    }

    @Test
    void testUpdateUserInfo() {
        UserDO userDO = new UserDO();

        userDO.setId("12929265753e11eaaf4254e1ad394a4a");
        userDO.setName("qqq");
        userDO.setLoginName("qqq");
        userDO.setPhoto("qq");
        userDO.setSex("1");

        userDAO.updateUserInfo(userDO);
    }

    @Test
    void testUpdatePhotoById() {
        UserDO userDO = new UserDO();

        userDO.setId("12929265753e11eaaf4254e1ad394a4a");
        userDO.setPhoto("qq");

        userDAO.updatePhotoById(userDO);
    }

    @Test
    void testUpdatePasswordById() {
        UserDO userDO = new UserDO();

        userDO.setId("12929265753e11eaaf4254e1ad394a4a");
        userDO.setPwd(DigestUtils.md5DigestAsHex("qwer".getBytes()));

        userDAO.updatePasswordById(userDO);
    }

}
