package service;

import model.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static UserService userService = UserService.getInstance();

    @AfterAll
    public static void afterAll() throws SQLException {
        userService.dropTable();
        userService.createTableIfNotExists();
    }
    @BeforeEach
    public void beforeEach() throws SQLException {
        userService.dropTable();
        userService.createTableIfNotExists();
        userService.createNewUser("Violetta","password");
    }

    @Test
    void getInstance() {
    }

    @Test
    void getAllUsers() throws SQLException {
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void getUserById() throws SQLException {
        User user = userService.getUserByName("Violetta");
        assertEquals("password", userService.getUserById(user.getId()).getPassword());
    }

    @Test
    void getUserByName() throws SQLException {
        assertNotEquals(null, userService.getUserByName("Violetta"));
    }

    @Test
    void getUserByNameAndPassword() throws SQLException {
        assertNotEquals(null, userService.getUserByNameAndPassword("Violetta","password"));
    }

    @Test
    void setUser() throws SQLException {
        User user = userService.getUserByName("Violetta");
        user.setName("Max");
        userService.setUser(user);
        assertNull(userService.getUserByName("Violetta"));
    }

    @Test
    void createNewUser() throws SQLException {
        userService.createNewUser("Max","pass");
        assertEquals(2, userService.getAllUsers().size());
    }
}