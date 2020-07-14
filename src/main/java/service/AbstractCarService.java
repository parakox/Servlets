package service;

import model.entity.Car;

import java.sql.SQLException;
import java.util.List;

public interface AbstractCarService {
    List<Car> getCarsByUserId(Integer id) throws SQLException;
    Car getCarByCarNumber(String carNumber) throws SQLException;
    void setCar(Car car) throws SQLException;
    void createNewCar(String carNumber,String name,Integer userId,Integer parkingPlaceId) throws SQLException;
    void deleteCar(Car car) throws SQLException;
    void dropTable() throws SQLException;
    void createTableIfNotExists() throws SQLException;
}
