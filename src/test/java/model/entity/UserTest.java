package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getId() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void getCars() {
    }

    @Test
    void testEquals() {
        User user1 = new User("Vio",150,"password",null);
        User user2 = new User("Max",150,"password",null);
        assertEquals(user1,user2);
    }

    @Test
    void testHashCode() {
        User user1 = new User("Vio",150,"password",null);
        User user2 = new User("Max",150,"password",null);
        assertEquals(user1.hashCode(),user2.hashCode());
    }
}