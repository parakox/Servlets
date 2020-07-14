package dao;

import model.entity.ParkingPlace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AbstractParkingPlaceDao {
    List<ParkingPlace> getAllParkingPlaces() throws SQLException;
    ParkingPlace getParkingPlaceById(Integer id) throws SQLException;
    void setParkingPlace(ParkingPlace parkingPlace) throws SQLException;
    void createTableIfNotExists() throws SQLException;
    void dropTable() throws SQLException;
    void init() throws SQLException;
    ParkingPlace getParkingPlaceFromResultSet(ResultSet resultSet) throws SQLException;
    void addNewParkingPlace(Integer id) throws SQLException;
    Integer getAmount();
}
