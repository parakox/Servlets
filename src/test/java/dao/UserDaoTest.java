package dao;

import model.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private static UserDao userDao = UserDao.getInstance();

    @AfterAll
    public static void afterAll() throws SQLException {
        userDao.dropTable();
        userDao.createTableIfNotExists();
    }
    @BeforeEach
    public void beforeEach() throws SQLException {
        userDao.dropTable();
        userDao.createTableIfNotExists();
        userDao.createNewUser("Violetta","password");
    }

    @Test
    void getInstance() {
    }

    @Test
    void getAllUsers() throws SQLException {
        assertEquals(1,userDao.getAllUsers().size());
    }

    @Test
    void getUserById() throws SQLException {
        User user = userDao.getUserByName("Violetta");
        assertEquals("password",userDao.getUserById(user.getId()).getPassword());
    }

    @Test
    void getUserByName() throws SQLException {
        assertNotEquals(null,userDao.getUserByName("Violetta"));
    }

    @Test
    void getUserByNameAndPassword() throws SQLException {
        assertNotEquals(null,userDao.getUserByNameAndPassword("Violetta","password"));
    }

    @Test
    void setUser() throws SQLException {
        User user = userDao.getUserByName("Violetta");
        user.setName("Max");
        userDao.setUser(user);
        assertNull(userDao.getUserByName("Violetta"));
    }

    @Test
    void createNewUser() throws SQLException {
        userDao.createNewUser("Max","pass");
        assertEquals(2,userDao.getAllUsers().size());
    }
}