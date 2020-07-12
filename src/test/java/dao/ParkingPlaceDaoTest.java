package dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {

    private ParkingPlaceDao parkingPlaceDao = ParkingPlaceDao.getInstance();
    @Test
    public void testParkingPlaceDao() throws SQLException, ClassNotFoundException {
        assertEquals(parkingPlaceDao.getAmount(),parkingPlaceDao.getAllParkingPlaces().size());
    }
}