package model.service;

import model.dao.ParkingPlaceDao;
import model.entity.entity.ParkingPlace;

import java.sql.SQLException;
import java.util.List;

public class ParkingPlaceService {

    public static List<ParkingPlace> getAllParkingPlaces() throws SQLException{
        return ParkingPlaceDao.getAllParkingPlaces();
    }
    public static ParkingPlace getParkingPlaceById(Integer id) throws SQLException {
        return ParkingPlaceDao.getParkingPlaceById(id);
    }
    public static void setParkingPlace(ParkingPlace parkingPlace) throws SQLException {
        ParkingPlaceDao.setParkingPlace(parkingPlace);
    }
    public static void createTableIfNotExists() throws SQLException {
        ParkingPlaceDao.createTableIfNotExists();
    }
}
