package service;

import dao.AbstractCarDao;
import dao.CarDao;
import model.entity.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService implements AbstractCarService{

    private static CarService carService = new CarService(CarDao.getInstance());

    private AbstractCarDao carDao;

    public static CarService getInstance() {
        return carService;
    }

    private CarService(AbstractCarDao carDao){
       this.carDao = carDao;

    }

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
    public void deleteCar(Car car) throws SQLException {
        carDao.deleteCar(car);
    }
    public void createTableIfNotExists() throws SQLException {
        carDao.createTableIfNotExists();
    }
    public void dropTable() throws SQLException {
        carDao.dropTable();
    }
}
