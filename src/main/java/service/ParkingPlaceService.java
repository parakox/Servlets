package service;

import dao.AbstractParkingPlaceDao;
import dao.ParkingPlaceDao;
import model.entity.ParkingPlace;

import java.sql.SQLException;
import java.util.List;

public class ParkingPlaceService implements AbstractParkingPlaceService {

    private static ParkingPlaceService parkingPlaceService = new ParkingPlaceService(ParkingPlaceDao.getInstance());

    private AbstractParkingPlaceDao parkingPlaceDao;

    public static ParkingPlaceService getInstance() {
        return parkingPlaceService;
    }

    private ParkingPlaceService(AbstractParkingPlaceDao parkingPlaceDao){
        this.parkingPlaceDao = parkingPlaceDao;
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
