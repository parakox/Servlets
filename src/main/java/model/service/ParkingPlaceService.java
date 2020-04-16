package model.service;

import model.dao.CarDao;
import model.dao.ParkingPlaceDao;
import model.entity.Car;
import model.entity.ParkingPlace;

import java.sql.SQLException;
import java.util.List;

public class ParkingPlaceService {

    public static List<ParkingPlace> getAllParkingPlaces() throws SQLException, ClassNotFoundException {
        return ParkingPlaceDao.getAllParkingPlaces();
    }
    public static ParkingPlace getParkingPlaceById(Integer id) throws SQLException, ClassNotFoundException {
        return ParkingPlaceDao.getParkingPlaceById(id);
    }
    public static void setParkingPlace(ParkingPlace parkingPlace) throws SQLException, ClassNotFoundException {
        ParkingPlaceDao.setParkingPlace(parkingPlace);
    }
    public static void createTableIfNotExists() throws SQLException, ClassNotFoundException {
        ParkingPlaceDao.createTableIfNotExists();
    }
}
