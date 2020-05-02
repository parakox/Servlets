package model.service;

import model.dao.CarDao;
import model.entity.entity.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService {

    private static CarService carService = new CarService();

    public static CarService getCarService() {
        return carService;
    }

    private CarService(){

    }


    private CarDao carDao = CarDao.getCarDao();

    public Car getCarByCarNumber(String carNumber) throws SQLException {
        return carDao.getCarByCarNumber(carNumber);
    }
    public void setCar(Car car) throws SQLException {
        carDao.setCar(car);
    }
    public List<Car> getCarsByUserId(Integer id) throws SQLException {
        return carDao.getCarsByUserId(id);
    }
    public void createNewCar(String carNumber, String name,Integer userId,Integer parkingPlaceId) throws SQLException {
        carDao.createNewCar(carNumber,name,userId,parkingPlaceId);
    }
    public void deleteCar(Car car) throws SQLException{
        carDao.deleteCar(car);
    }
    public void createTableIfNotExists() throws SQLException {
        carDao.createTableIfNotExists();
    }
}
