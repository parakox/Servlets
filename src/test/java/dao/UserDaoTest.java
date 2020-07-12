package dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private UserDao userDao = UserDao.getInstance();

    @Test
    public void testUserDao() throws SQLException, ClassNotFoundException {
        userDao.createNewUser("Violetta","password");
        assertEquals(userDao.getUserByName("Violetta"), userDao.getUserByNameAndPassword("Violetta", "password"));
    }
}