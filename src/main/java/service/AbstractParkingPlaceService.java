package service;

import model.entity.ParkingPlace;

import java.sql.SQLException;
import java.util.List;

public interface AbstractParkingPlaceService {
    List<ParkingPlace> getAllParkingPlaces() throws SQLException;
    ParkingPlace getParkingPlaceById(Integer id) throws SQLException;
    void setParkingPlace(ParkingPlace parkingPlace) throws SQLException;
    void createTableIfNotExists() throws SQLException;
    void dropTable() throws SQLException;
    void init() throws SQLException;
    Integer getAmount();
}
