package model.service;

import model.dao.CarDao;
import model.entity.entity.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static Car getCarByCarNumber(String carNumber) throws SQLException {
        return CarDao.getCarByCarNumber(carNumber);
    }
    public static void setCar(Car car) throws SQLException {
        CarDao.setCar(car);
    }
    public static List<Car> getCarsByUserId(Integer id) throws SQLException {
        return CarDao.getCarsByUserId(id);
    }
    public static void createNewCar(String carNumber, String name,Integer userId,Integer parkingPlaceId) throws SQLException {
        CarDao.createNewCar(carNumber,name,userId,parkingPlaceId);
    }
    public static void deleteCar(Car car) throws SQLException{
        CarDao.deleteCar(car);
    }
    public static void createTableIfNotExists() throws SQLException {
        CarDao.createTableIfNotExists();
    }
}
