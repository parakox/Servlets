package model.service;

import model.dao.CarDao;
import model.entity.Car;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static List<Car> getAllCars() throws SQLException, ClassNotFoundException {
        return CarDao.getAllCars();
    }
    public static Car getCarByCarNumber(String carNumber) throws SQLException, ClassNotFoundException {
        return CarDao.getCarByCarNumber(carNumber);
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
    public static void parkCar(Car car,Integer idOdParkingPlace) throws SQLException, ClassNotFoundException {
        CarDao.parkCar(car,idOdParkingPlace);
    }
    public static void unparkCar(Car car) throws SQLException, ClassNotFoundException {
        CarDao.unparkCar(car);
    }
    public static void createTableIfNotExists() throws SQLException, ClassNotFoundException {
        CarDao.createTableIfNotExists();
    }
}
