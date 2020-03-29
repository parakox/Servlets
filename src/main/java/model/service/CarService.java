package model.service;

import model.dao.CarDao;
import model.entity.Car;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static List<Car> getCars() {
        return CarDao.getCars();
    }
    public static void setCars(List<Car> cars) {
        CarDao.setCars(cars);
    }
    public static Car getCarById(Integer id){
        return CarDao.getCarById(id);
    }
    public static void createNewCar(String carNumber, String name, User user) throws SQLException, ClassNotFoundException {
        CarDao.createNewCar(carNumber,name,user);
    }
}
