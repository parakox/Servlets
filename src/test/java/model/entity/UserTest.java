package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUser(){
        User user1 = new User("Vio",49,"password",null);
        User user2 = new User("Max",150,"password",null);
        assertNotEquals(user1,user2);
    }

}