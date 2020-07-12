package service;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceServiceTest {
    private ParkingPlaceService parkingPlaceService = ParkingPlaceService.getInstance();

    @Test
    public void testParkingPlaceService() throws SQLException, ClassNotFoundException {
        assertEquals(parkingPlaceService.getAmount(),parkingPlaceService.getAllParkingPlaces().size());
    }

}