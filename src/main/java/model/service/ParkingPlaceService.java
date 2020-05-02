package model.service;

import model.dao.ParkingPlaceDao;
import model.entity.entity.ParkingPlace;

import java.sql.SQLException;
import java.util.List;

public class ParkingPlaceService {

    private static ParkingPlaceService parkingPlaceService = new ParkingPlaceService();

    public static ParkingPlaceService getParkingPlaceService() {
        return parkingPlaceService;
    }

    private ParkingPlaceService(){

    }

    private ParkingPlaceDao parkingPlaceDao = ParkingPlaceDao.getParkingPlace();

    public List<ParkingPlace> getAllParkingPlaces() throws SQLException{
        return parkingPlaceDao.getAllParkingPlaces();
    }
    public ParkingPlace getParkingPlaceById(Integer id) throws SQLException {
        return parkingPlaceDao.getParkingPlaceById(id);
    }
    public void setParkingPlace(ParkingPlace parkingPlace) throws SQLException {
        parkingPlaceDao.setParkingPlace(parkingPlace);
    }
    public void createTableIfNotExists() throws SQLException {
        parkingPlaceDao.createTableIfNotExists();
    }
}
