package service;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService = UserService.getInstance();

    @Test
    public void testUserService() throws SQLException, ClassNotFoundException {
        userService.createNewUser("Mengsk","Raynor");
        assertEquals("Raynor",userService.getUserByName("Mengsk").getPassword());
    }

}