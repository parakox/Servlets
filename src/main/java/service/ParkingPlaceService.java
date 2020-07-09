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


    public List<ParkingPlace> getAllParkingPlaces() throws SQLException, ClassNotFoundException {
        return parkingPlaceDao.getAllParkingPlaces();
    }
    public ParkingPlace getParkingPlaceById(Integer id) throws SQLException, ClassNotFoundException {
        return parkingPlaceDao.getParkingPlaceById(id);
    }
    public void setParkingPlace(ParkingPlace parkingPlace) throws SQLException, ClassNotFoundException {
        parkingPlaceDao.setParkingPlace(parkingPlace);
    }
}
