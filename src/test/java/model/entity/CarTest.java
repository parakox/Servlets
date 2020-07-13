package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void getCarNumber() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void getParkingPlaceId() {
    }

    @Test
    void setParkingPlaceId() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
        Car car1 = new Car("hahaha","hello",1,0);
        Car car2 = new Car("hahaha","hellsdo",98,99);
        assertEquals(car1,car2);
    }

    @Test
    void testHashCode() {
        Car car1 = new Car("hahaha","hello",1,0);
        Car car2 = new Car("hahaha","hellsdo",98,99);
        assertEquals(car1.hashCode(),car2.hashCode());
    }
}