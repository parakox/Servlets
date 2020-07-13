package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceTest {

    @Test
    void getCar() {
    }

    @Test
    void setCar() {
    }

    @Test
    void getId() {
    }

    @Test
    void testEquals() {
        ParkingPlace parkingPlace1 = new ParkingPlace(null,149);
        ParkingPlace parkingPlace2 = new ParkingPlace(null,149);
        assertEquals(parkingPlace1,parkingPlace2);
    }

    @Test
    void testHashCode() {
        ParkingPlace parkingPlace1 = new ParkingPlace(null,149);
        ParkingPlace parkingPlace2 = new ParkingPlace(null,149);
        assertEquals(parkingPlace1.hashCode(),parkingPlace2.hashCode());
    }
}