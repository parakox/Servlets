package dao;

import model.dao.ParkingPlaceDao;
import model.entity.ParkingPlace;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {
    @Test
    public void testGetAllParkingPlaces() throws SQLException, ClassNotFoundException {
        ParkingPlace parkingPlace = ParkingPlaceDao.getAllParkingPlaces().get(0);
        assertEquals(parkingPlace.isEmpty(),true);
    }
}