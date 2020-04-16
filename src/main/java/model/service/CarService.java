package model.service;

import model.dao.CarDao;
import model.entity.Car;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static Car getCarByCarNumber(String carNumber) throws SQLException, ClassNotFoundException {
        return CarDao.getCarByCarNumber(carNumber);
    }
    public static void setCar(Car car) throws SQLException, ClassNotFoundException {
        CarDao.setCar(car);
    }
    public static List<Car> getCarsByUserId(Integer id) throws SQLException, ClassNotFoundException {
        return CarDao.getCarsByUserId(id);
    }
    public static void createNewCar(String carNumber, String name,Boolean parked,Integer userId,Integer parkingPlaceId) throws SQLException, ClassNotFoundException {
        CarDao.createNewCar(carNumber,name,parked,userId,parkingPlaceId);
    }
    public static void deleteCar(Car car) throws SQLException, ClassNotFoundException {
        CarDao.deleteCar(car);
    }
    public static void createTableIfNotExists() throws SQLException, ClassNotFoundException {
        CarDao.createTableIfNotExists();
    }
}
