package dao;

import model.dao.ParkingPlaceDao;
import model.entity.ParkingPlace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {
    @Test
    public void testGetAllParkingPlaces(){
        ParkingPlace parkingPlace = ParkingPlaceDao.getAllParkingPlaces().get(0);
        assertEquals(parkingPlace.isEmpty(),true);
    }
}