package service;

import dao.ParkingPlaceDao;
import model.entity.ParkingPlace;

import java.sql.SQLException;
import java.util.List;

public class ParkingPlaceService {

    private static ParkingPlaceService parkingPlaceService = new ParkingPlaceService();

    private ParkingPlaceDao parkingPlaceDao = ParkingPlaceDao.getInstance();

    public static ParkingPlaceService getInstance() {
        return parkingPlaceService;
    }

    private ParkingPlaceService(){

    }

    public List<ParkingPlace> getAllParkingPlaces() throws SQLException {
        return parkingPlaceDao.getAllParkingPlaces();
    }
    public ParkingPlace getParkingPlaceById(Integer id) throws SQLException {
        return parkingPlaceDao.getParkingPlaceById(id);
    }
    public void setParkingPlace(ParkingPlace parkingPlace) throws SQLException {
        parkingPlaceDao.setParkingPlace(parkingPlace);
    }
    public Integer getAmount(){
        return parkingPlaceDao.getAmount();
    }
    public void createTableIfNotExists() throws SQLException {
        parkingPlaceDao.createTableIfNotExists();
    }
    public void dropTable() throws SQLException {
        parkingPlaceDao.dropTable();
    }
    public void init() throws SQLException {
        parkingPlaceDao.init();
    }
}
